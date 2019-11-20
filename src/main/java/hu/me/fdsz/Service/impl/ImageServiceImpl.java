package hu.me.fdsz.Service.impl;

import hu.me.fdsz.Service.api.ImageService;
import hu.me.fdsz.model.Image;
import hu.me.fdsz.repository.ImageContentStore;
import hu.me.fdsz.repository.ImageRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.imgscalr.Scalr;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.persistence.EntityNotFoundException;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

@Service
public class ImageServiceImpl implements ImageService {

    private static final Logger logger = LogManager.getLogger(ImageServiceImpl.class);

    private final ImageRepository imageRepository;

    private final ImageContentStore imageContentStore;

    private final ModelMapper modelMapper;

    @Autowired
    public ImageServiceImpl(ImageRepository imageRepository, ImageContentStore imageContentStore, ModelMapper modelMapper) {
        this.imageRepository = imageRepository;
        this.imageContentStore = imageContentStore;
        this.modelMapper = modelMapper;
    }

    @Override
    public Optional<Image> addNewImage(MultipartFile multipartFile) {
        Optional<Image> result = Optional.of(modelMapper.getTypeMap(MultipartFile.class, Image.class).map(multipartFile));
        result.ifPresent(imageRepository::save);
        return result;
    }

    @Override
    public Image createImageFromMultipartFile(MultipartFile multipartFile) throws IOException {
        Image image = new Image();
        image.setContentLength(multipartFile.getSize());
        image.setMimeType(multipartFile.getContentType());
        image.setImageName(multipartFile.getOriginalFilename());
        InputStream resizedImage = resizeImage(multipartFile, 730, 410);
        imageContentStore.setContent(image, resizedImage); //add neki contentId-t
        return image;
    }

    @Override
    public ResponseEntity<?> getImage(long id) {
        return imageRepository.findById(id)
                .map(image -> {
                    HttpHeaders headers = new HttpHeaders();
                    headers.setContentLength(image.getContentLength());
                    headers.set("Content-Type", image.getMimeType());
                    return new ResponseEntity<>(new InputStreamResource(imageContentStore.getContent(image)),
                            headers, HttpStatus.OK);
                }).orElse(new ResponseEntity<>(HttpStatus.OK));
    }

    @Override
    public boolean deleteImage(long id) throws EntityNotFoundException {
        Image image = imageRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        imageContentStore.unsetContent(image);
        imageRepository.deleteById(id);
        return false;
    }

    private InputStream resizeImage(final MultipartFile multipartFile, int width, int height) throws IOException, IllegalStateException {
        BufferedImage imageToScale = ImageIO.read(multipartFile.getInputStream());
        IllegalStateException e = new IllegalStateException("ismeretlen a kép típusa");
        String imageType = Optional.ofNullable(multipartFile.getContentType())
                .map(contentType -> {
                    String[] types = contentType.split("/");
                    if(types.length >= 2) {
                        return types[1];
                    } else {
                        throw e;
                    }
                }).orElseThrow(() -> e);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(Scalr.resize(imageToScale, Scalr.Method.AUTOMATIC, Scalr.Mode.AUTOMATIC, width, height), imageType, baos);
        return new ByteArrayInputStream(baos.toByteArray());
    }

}
