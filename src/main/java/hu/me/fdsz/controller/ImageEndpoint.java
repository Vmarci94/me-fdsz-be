package hu.me.fdsz.controller;

import hu.me.fdsz.service.api.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;

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
    public ResponseEntity<?> deleteImage(@PathVariable("imageId") long id){
        HttpStatus result;
        try{
            imageService.deleteImage(id);
            result = HttpStatus.OK;
        }catch (EntityNotFoundException e){
            result = HttpStatus.NO_CONTENT;
        } catch (Exception e){
            result = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(result);
    }

}
