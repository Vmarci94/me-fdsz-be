package hu.me.fdsz.service.impl;

import hu.me.fdsz.model.dto.FeedPostDTO;
import hu.me.fdsz.model.entities.Image;
import hu.me.fdsz.model.entities.Post;
import hu.me.fdsz.repository.ImageContentStore;
import hu.me.fdsz.repository.ImageRepository;
import hu.me.fdsz.repository.PostRepository;
import hu.me.fdsz.service.api.ImageService;
import hu.me.fdsz.service.api.PostService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.naming.AuthenticationException;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private static final Logger logger = LoggerFactory.getLogger(PostService.class.getName());

    private final PostRepository postRepository;

    private final ModelMapper modelMapper;

    private final ImageRepository imageRepository;

    private final ImageContentStore imageContentStore;

    private final ImageService imageService;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, ModelMapper modelMapper, ImageRepository imageRepository,
                           ImageContentStore imageContentStore, ImageService imageService) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
        this.imageRepository = imageRepository;
        this.imageContentStore = imageContentStore;
        this.imageService = imageService;
    }

    @Override
    public List<FeedPostDTO> getAll() {
        return postRepository.findAll().stream()
                .map(post -> modelMapper.getTypeMap(Post.class, FeedPostDTO.class).map(post))
                .collect(Collectors.toList());
    }

    @Override
    public FeedPostDTO add(FeedPostDTO feedPostDTO, MultipartFile multipartFile) throws AuthenticationException {
        Post newPost = modelMapper.map(feedPostDTO, Post.class);
        if (multipartFile != null) {
            // Azért kell így haszánlni a modelMappert-t, hogy biztosan a megfelelő convertert használja.
            // A MultipartFile Interfacet realizáló osztályok nem találnak rá autómatikusan a megfelelő TypeMap-re.
            Image image = modelMapper.getTypeMap(MultipartFile.class, Image.class).map(multipartFile);
            //add neki contentId-t
            //majd mentjük, mert contentId-val együtt kell perzisztálni.
            image = imageRepository.save(image); //Visszaadja a már perzisztált Entitást
            newPost.setImage(image);
        }
        newPost = postRepository.save(newPost);
        return modelMapper.map(newPost, FeedPostDTO.class);
    }

    @Override
    public List<FeedPostDTO> getPostsWithLimit(int limit) {
        return postRepository.findByOrderByLastModifiedDate(PageRequest.of(0, 5))
                .stream().map(post -> modelMapper.map(post, FeedPostDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public FeedPostDTO findById(long postId) {
        return postRepository.findById(postId)
                .map(post -> modelMapper.getTypeMap(Post.class, FeedPostDTO.class).map(post))
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public FeedPostDTO update(FeedPostDTO feedPostDTO, MultipartFile multipartFile) throws AuthenticationException {
        if (feedPostDTO == null || feedPostDTO.getId() == null) {
            throw new NullPointerException("A frissítendő post nem lehet null");
        }

        if (postRepository.findById(feedPostDTO.getId()).isPresent()) {
            //létezik a frissítendő post
            Post newPost = modelMapper.map(feedPostDTO, Post.class);

            if (newPost.getImage().isEmpty()) {
                //ha új kép jön, akkor annak még nincs ID-ja, így ez üres lesz, és a kép tartalma a multipart file-ban lesz.
                imageService.updateImage(newPost, multipartFile);
            } //ha  jött imageId és Image a DTO-ba akkor a régi képet tartja meg.

            //majd mentjük a változásokat
            newPost = postRepository.save(newPost);
            return modelMapper.map(newPost, FeedPostDTO.class);
        } else {
            // nem létezik a frissítendő post, nem baj ezt loggoljuk és csinálunk egyett
            return this.add(feedPostDTO, multipartFile);
        }
    }

    @Transactional
    @Override
    public boolean delete(long postId) {
        try {
            postRepository.findById(postId).ifPresent(post -> {
                post.getImage().ifPresent(imageContentStore::unsetContent);
                postRepository.delete(post);
            });
            return true;
        } catch (Exception e) {
            return false;
        }
    }


}
