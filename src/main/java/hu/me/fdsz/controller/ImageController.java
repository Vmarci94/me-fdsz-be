package hu.me.fdsz.controller;

import hu.me.fdsz.service.api.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/image")
public class ImageController {

    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping(value="/{imageId}", headers="accept!=application/hal+json")
    public ResponseEntity<InputStreamResource> getImage(@PathVariable("imageId") long id) {
        return imageService.getImage(id);
    }
    
    @DeleteMapping(value = "/delete/{imageId}", headers="accept!=application/hal+json")
    public ResponseEntity<HttpStatus> deleteImage(@PathVariable("imageId") long imageId) {
        return new ResponseEntity<>(imageService.deleteImage(imageId) ? HttpStatus.OK : HttpStatus.NOT_EXTENDED);
    }

}
