package hu.me.fdsz.Service.impl;

import hu.me.fdsz.Service.api.FeedService;
import hu.me.fdsz.Service.api.JwtTokenProvider;
import hu.me.fdsz.dto.FeedPostDTO;
import hu.me.fdsz.model.FeedPost;
import hu.me.fdsz.model.User;
import hu.me.fdsz.repository.FeedPostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class FeedServiceImpl implements FeedService {

    private final FeedPostRepository feedPostRepository;

    private final ModelMapper modelMapper;

    private final JwtTokenProvider jwtTokenProvider;

    public FeedServiceImpl(FeedPostRepository feedPostRepository, ModelMapper modelMapper, JwtTokenProvider jwtTokenProvider) {
        this.feedPostRepository = feedPostRepository;
        this.modelMapper = modelMapper;
        this.jwtTokenProvider = jwtTokenProvider;
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
    public ResponseEntity<HttpStatus> setContent(Long feedPostId, MultipartFile file) throws IOException, SQLException {
        Blob image = new SerialBlob(file.getBytes());
        feedPostRepository.findById(feedPostId)
                .ifPresent(feedPost -> {
                    feedPost.setImage(image);
                    feedPostRepository.save(feedPost);
                });
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<InputStreamResource> getContent(Long feedPostId) {
        return feedPostRepository.findById(feedPostId).map(feedPost -> {
            try {
                InputStreamResource inputStreamResource = new InputStreamResource(feedPost.getImage().getBinaryStream());
                HttpHeaders headers = new HttpHeaders();
                headers.setContentLength(feedPost.getImage().length());
                return new ResponseEntity<>(inputStreamResource, headers, HttpStatus.OK);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }).orElseThrow(() -> new IllegalArgumentException("Hiba"));
    }

    public byte[] getContentByteArr(Long feedPostId) {
        return feedPostRepository.findById(feedPostId).map(feedPost -> {
            try {
                return feedPost.getImage().getBytes(1, Long.valueOf(feedPost.getImage().length()).intValue());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

}
