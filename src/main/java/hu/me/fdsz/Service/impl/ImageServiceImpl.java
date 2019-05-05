package hu.me.fdsz.Service.impl;

import hu.me.fdsz.Service.api.ImageService;
import hu.me.fdsz.model.Image;
import hu.me.fdsz.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    @Autowired
    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public Image addNewImage(MultipartFile rawImage) throws IOException {
        Image newImage = new Image();
        newImage.setImageType("image/*");
        newImage.setData(rawImage.getBytes());
        return imageRepository.save(newImage);
    }
}
