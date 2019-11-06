package hu.me.fdsz.Service.api;

import hu.me.fdsz.model.Image;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;

@Service
public interface ImageService {

    boolean addNewImage(MultipartFile multipartFile);

    ResponseEntity<?> getImage(long id);

    boolean deleteImage(long id) throws EntityNotFoundException;

}
