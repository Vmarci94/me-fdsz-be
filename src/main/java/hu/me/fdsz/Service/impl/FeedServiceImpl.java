package hu.me.fdsz.Service.impl;

import hu.me.fdsz.Service.api.FeedService;
import hu.me.fdsz.Service.api.ImageService;
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
import java.util.Base64;
import java.util.List;


@Service
public class FeedServiceImpl implements FeedService {

    private static final Logger logger = LoggerFactory.getLogger(FeedService.class.getName());

    private final FeedPostRepository feedPostRepository;

    private final ModelMapper modelMapper;

    private final JwtTokenProvider jwtTokenProvider;

    private final ImageService imageService;

    public FeedServiceImpl(FeedPostRepository feedPostRepository, ModelMapper modelMapper, JwtTokenProvider jwtTokenProvider, ImageService imageService) {
        this.feedPostRepository = feedPostRepository;
        this.modelMapper = modelMapper;
        this.jwtTokenProvider = jwtTokenProvider;
        this.imageService = imageService;
    }

    @Override
    public List<FeedPostDTO> getAll() {
        List<FeedPostDTO> result = new ArrayList<>();

        feedPostRepository.findAll().iterator().forEachRemaining(feedPost -> {
            FeedPostDTO feedPostDTO = modelMapper
                    .map(feedPost, FeedPostDTO.class);
            feedPostDTO.setImageSrc(convertImageToString(feedPost.getImage()));
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
        return feedPostRepository.findById(feedPostId).map(feedPost -> {
            try {
                Image newImage = imageService.addNewImage(file);
                feedPost.setImage(newImage);
                return feedPostRepository.save(feedPost);
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
                throw new IllegalArgumentException();
            }
        }).isPresent() ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public FeedPostDTO getContent(Long feedPostId) {
        return feedPostRepository.findById(feedPostId).map(feedPost -> {
            FeedPostDTO result = modelMapper.map(feedPost, FeedPostDTO.class);
            result.setImageSrc(convertImageToString(feedPost.getImage()));
            return result;
        }).orElseThrow(EntityNotFoundException::new);
    }

    private String convertImageToString(Image image) {
        return "data:image/jpeg;base64," + new String(Base64.getEncoder().encode(image.getData()));
    }

}
