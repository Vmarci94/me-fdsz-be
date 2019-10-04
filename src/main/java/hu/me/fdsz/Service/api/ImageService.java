package hu.me.fdsz.Service.api;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface ImageService {

    boolean addNewImage(MultipartFile multipartFile);

    ResponseEntity<?> getImage(long id);
}
