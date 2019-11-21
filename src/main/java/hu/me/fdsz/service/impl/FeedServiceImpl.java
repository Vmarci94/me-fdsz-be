package hu.me.fdsz.service.impl;

import hu.me.fdsz.dto.FeedPostDTO;
import hu.me.fdsz.model.FeedPost;
import hu.me.fdsz.model.Image;
import hu.me.fdsz.model.User;
import hu.me.fdsz.repository.FeedPostRepository;
import hu.me.fdsz.repository.ImageContentStore;
import hu.me.fdsz.repository.ImageRepository;
import hu.me.fdsz.service.api.FeedService;
import hu.me.fdsz.service.api.JwtTokenProvider;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeedServiceImpl implements FeedService {

    private static final Logger logger = LoggerFactory.getLogger(FeedService.class.getName());

    private final FeedPostRepository feedPostRepository;

    private final ModelMapper modelMapper;

    private final JwtTokenProvider jwtTokenProvider;

    private final ImageRepository imageRepository;

    private final ImageContentStore imageContentStore;

    @Autowired
    public FeedServiceImpl(FeedPostRepository feedPostRepository, ModelMapper modelMapper, JwtTokenProvider jwtTokenProvider, ImageRepository imageRepository, ImageContentStore imageContentStore) {
        this.feedPostRepository = feedPostRepository;
        this.modelMapper = modelMapper;
        this.jwtTokenProvider = jwtTokenProvider;
        this.imageRepository = imageRepository;
        this.imageContentStore = imageContentStore;
    }

    @Override
    public List<FeedPostDTO> getAll() {
        return feedPostRepository.findAll().stream()
                .map(feedPost -> modelMapper.getTypeMap(FeedPost.class, FeedPostDTO.class).map(feedPost))
                .collect(Collectors.toList());
    }

    @Override
    public void add(FeedPostDTO feedPostDTO, MultipartFile multipartFile) throws IOException {
        FeedPost newFeedPost = modelMapper.map(feedPostDTO, FeedPost.class);
        // Azért kell így haszánlni a modelMappert-t, hogy biztosan a megfelelő convertert használja.
        // A MultipartFile Interfacet realizáló osztályok nem találnak rá autómatikusan a megfelelő TypeMap-re.
        Image image = modelMapper.getTypeMap(MultipartFile.class, Image.class).map(multipartFile);
        //add neki contentId-t
        //majd mentjük, mert contentId-val együtt kell perzisztálni.
        image = imageRepository.save(image); //Visszaadja a már perzisztált Entitást
        newFeedPost.setImage(image);
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

    @Override
    public List<FeedPostDTO> getPostsWithLimit(int limit) {
        return feedPostRepository.findByOrderByLastModifiedDate(PageRequest.of(0, 5))
                .map(feedPost -> modelMapper.map(feedPost, FeedPostDTO.class))
                .collect(Collectors.toList());
    }

}
