package hu.me.fdsz.Service.impl;

import hu.me.fdsz.Service.api.FeedService;
import hu.me.fdsz.Service.api.JwtTokenProvider;
import hu.me.fdsz.dto.FeedPostDTO;
import hu.me.fdsz.model.FeedPost;
import hu.me.fdsz.model.Image;
import hu.me.fdsz.model.User;
import hu.me.fdsz.repository.FeedPostRepository;
import hu.me.fdsz.repository.ImageContentStore;
import hu.me.fdsz.repository.ImageRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.persistence.EntityNotFoundException;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class FeedServiceImpl implements FeedService {

    private static final Logger logger = LoggerFactory.getLogger(FeedService.class.getName());

    private final FeedPostRepository feedPostRepository;

    private final ModelMapper modelMapper;

    private final JwtTokenProvider jwtTokenProvider;

    private final ImageRepository imageRepository;

    private final ImageContentStore imageContentStore;


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
        Image image = getImageFromMultipartFile(multipartFile);
        //add neki contentId-t
        imageContentStore.setContent(image, image.getInputStream());
        //majd mentjük, mert contentId-val együtt kell perzisztálni.
        image = imageRepository.save(image); //Visszaadja a már perzisztált Entitást
        newFeedPost.setImage(image);
        User currentUser = jwtTokenProvider.getAuthenticatedUser();
        newFeedPost.setAuthor(currentUser);
        feedPostRepository.save(newFeedPost);
    }

    private Image getImageFromMultipartFile(MultipartFile multipartFile){
        Image resultImage = new Image();
        resultImage.setContentLength(multipartFile.getSize());
        resultImage.setMimeType(multipartFile.getContentType());
        resultImage.setImageName(multipartFile.getOriginalFilename());
        try {
            int width = 300;
            int height = 300;
            BufferedImage bufferedImage = ImageIO.read(multipartFile.getInputStream());
            java.awt.Image image = bufferedImage.getScaledInstance(width, height, java.awt.Image.SCALE_DEFAULT);
            BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = resized.createGraphics();
            g2d.drawImage(image, 0, 0, null);
            g2d.dispose();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            String imageType = Optional.ofNullable(multipartFile.getContentType())
                    .map(contentType -> contentType.split("/"))
                    .filter(strArr -> strArr.length ==2)
                    .orElseThrow(() -> new IllegalArgumentException("Ismeretlen képtípus!"))[1];
            ImageIO.write(resized, imageType, byteArrayOutputStream);
            resultImage.setInputStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
        } catch (IOException | IllegalArgumentException e) {
            e.printStackTrace(); //FIXME logger kellene
        } //logot pls.

        return resultImage;
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

}
