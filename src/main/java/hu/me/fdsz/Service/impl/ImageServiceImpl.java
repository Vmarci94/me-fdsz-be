package hu.me.fdsz.Service.impl;

import hu.me.fdsz.Service.api.ImageService;
import hu.me.fdsz.model.Image;
import hu.me.fdsz.repository.ImageContentStore;
import hu.me.fdsz.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    private final ImageContentStore imageContentStore;

    @Autowired
    public ImageServiceImpl(ImageRepository imageRepository, ImageContentStore imageContentStore) {
        this.imageRepository = imageRepository;
        this.imageContentStore = imageContentStore;
    }

    @Override
    public boolean addNewImage(MultipartFile multipartFile) {
        Image image = new Image();
        image.setContentLength(multipartFile.getSize());
        image.setMimeType(multipartFile.getContentType());
        image.setImageName(multipartFile.getOriginalFilename());
        try {
            //add neki contentId-t
            imageContentStore.setContent(image, multipartFile.getInputStream());
            imageRepository.save(image); //contentId-val együtt kell perzisztálni.
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
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

}
