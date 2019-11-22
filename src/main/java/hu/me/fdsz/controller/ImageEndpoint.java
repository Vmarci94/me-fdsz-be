package hu.me.fdsz.controller;

import hu.me.fdsz.service.api.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/image")
public class ImageEndpoint {

    private final ImageService imageService;

    @Autowired
    public ImageEndpoint(ImageService imageService) {
        this.imageService = imageService;
    }

    @PutMapping(value = "/add-new-image", headers = {"content-type!=application/hal+json"})
    public ResponseEntity<?> addNewImage(@RequestParam("image") MultipartFile image){
        return new ResponseEntity<>(imageService.addNewImage(image).isPresent() ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @GetMapping(value="/{imageId}", headers="accept!=application/hal+json")
    public ResponseEntity<?> getImage(@PathVariable("imageId") long id){
        return imageService.getImage(id);
    }
    
    @DeleteMapping(value = "/delete/{imageId}", headers="accept!=application/hal+json")
    public ResponseEntity<HttpStatus> deleteImage(@PathVariable("imageId") long imageId) {
        return new ResponseEntity<>(imageService.deleteImage(imageId) ? HttpStatus.OK : HttpStatus.NOT_EXTENDED);
    }

}
