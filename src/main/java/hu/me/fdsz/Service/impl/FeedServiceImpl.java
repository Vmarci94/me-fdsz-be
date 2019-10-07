package hu.me.fdsz.Service.impl;

import hu.me.fdsz.Service.api.FeedService;
import hu.me.fdsz.Service.api.JwtTokenProvider;
import hu.me.fdsz.dto.FeedPostDTO;
import hu.me.fdsz.model.FeedPost;
import hu.me.fdsz.model.Image;
import hu.me.fdsz.model.User;
import hu.me.fdsz.repository.FeedPostRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Service
public class FeedServiceImpl implements FeedService {

    private static final Logger logger = LoggerFactory.getLogger(FeedService.class.getName());

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

        feedPostRepository.findAll().iterator().forEachRemaining(feedPost -> {
            FeedPostDTO feedPostDTO = modelMapper
                    .map(feedPost, FeedPostDTO.class);
            result.add(feedPostDTO);

        });
        return result;
    }

    @Override
    public void add(FeedPostDTO feedPostDTO) {
        FeedPost newFeedPost = modelMapper.map(feedPostDTO, FeedPost.class);
        User currentUser = jwtTokenProvider.getAuthenticatedUser();
        newFeedPost.setAuthor(currentUser);
        feedPostRepository.save(newFeedPost);
    }

    @Override
    public ResponseEntity<HttpStatus> setContent(Long feedPostId, MultipartFile file) {
        return null;
    }

    @Override
    public FeedPostDTO getContent(Long feedPostId) {
        return feedPostRepository.findById(feedPostId).map(feedPost -> {
            FeedPostDTO result = modelMapper.map(feedPost, FeedPostDTO.class);
            return result;
        }).orElseThrow(EntityNotFoundException::new);
    }

    private String convertImageToString(Image image) {
//        return "data:image/jpeg;base64," + new String(Base64.getEncoder().encode(image.getData()));
        return null;
    }

}
