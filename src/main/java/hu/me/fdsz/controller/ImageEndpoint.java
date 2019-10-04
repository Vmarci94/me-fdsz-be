package hu.me.fdsz.controller;

import hu.me.fdsz.Service.api.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
    public ResponseEntity<?> addNewImage(@RequestParam("file") MultipartFile image){
        return new ResponseEntity<>(imageService.addNewImage(image) ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @GetMapping(value="/files/{fileId}", headers="accept!=application/hal+json")
    public ResponseEntity<?> getImage(@PathVariable("fileId") long id){
        return imageService.getImage(id);
    }

}
