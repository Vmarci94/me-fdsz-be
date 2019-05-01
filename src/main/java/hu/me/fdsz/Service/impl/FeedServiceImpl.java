package hu.me.fdsz.Service.impl;

import hu.me.fdsz.Service.api.FeedService;
import hu.me.fdsz.Service.api.JwtTokenProvider;
import hu.me.fdsz.dto.FeedPostDTO;
import hu.me.fdsz.model.FeedPost;
import hu.me.fdsz.model.User;
import hu.me.fdsz.repository.FeedPostImageContentStore;
import hu.me.fdsz.repository.FeedPostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class FeedServiceImpl implements FeedService {

    private final FeedPostRepository feedPostRepository;

    private final ModelMapper modelMapper;

    private final JwtTokenProvider jwtTokenProvider;

    private final FeedPostImageContentStore feedPostImageContentStore;

    public FeedServiceImpl(FeedPostRepository feedPostRepository, ModelMapper modelMapper, JwtTokenProvider jwtTokenProvider,
                           FeedPostImageContentStore feedPostImageContentStore) {
        this.feedPostRepository = feedPostRepository;
        this.modelMapper = modelMapper;
        this.jwtTokenProvider = jwtTokenProvider;
        this.feedPostImageContentStore = feedPostImageContentStore;
    }

    @Override
    public List<FeedPostDTO> getAll() {
        List<FeedPostDTO> result = new ArrayList<>();
        feedPostRepository.findAll().iterator().forEachRemaining(feedPost -> result.add(modelMapper.map(feedPost, FeedPostDTO.class)));
        return result;
    }

    @Override
    public void add(FeedPostDTO feedPostDTO) {
        FeedPost newFeedPost = modelMapper.map(feedPostDTO, FeedPost.class);
        User currentUser = jwtTokenProvider.getUser();
        newFeedPost.setAuthor(currentUser);
        feedPostRepository.save(newFeedPost);
    }

    @Override
    public ResponseEntity<HttpStatus> setContent(Long feedPostId, MultipartFile file) {
        return feedPostRepository.findById(feedPostId).map(feedPost -> {
            feedPost.setMimeType(file.getContentType());
            try {
                feedPostImageContentStore.setContent(feedPost, file.getInputStream());
                feedPostRepository.save(feedPost);
                return new ResponseEntity<HttpStatus>(HttpStatus.OK);
            } catch (IOException e) {
                e.printStackTrace();
                return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @Override
    public ResponseEntity<InputStreamResource> getContent(Long feedPostId) {
        return feedPostRepository.findById(feedPostId).map(feedPost -> {
            InputStreamResource inputStreamResource = new InputStreamResource(feedPostImageContentStore.getContent(feedPost));
            HttpHeaders headers = new HttpHeaders();
            headers.setContentLength(feedPost.getContentLength());
            headers.set("Content-Type", feedPost.getMimeType());
            return new ResponseEntity<>(inputStreamResource, headers, HttpStatus.OK);
        }).orElseThrow(() -> new IllegalArgumentException("Hiba"));
    }

}
