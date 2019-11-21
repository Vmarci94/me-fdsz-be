package hu.me.fdsz.service.api;

import hu.me.fdsz.model.Image;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.Optional;

@Service
public interface ImageService {

    Optional<Image> addNewImage(MultipartFile multipartFile);

    Image createImageFromMultipartFile(MultipartFile multipartFile) throws IOException;

    ResponseEntity<?> getImage(long id);

    boolean deleteImage(long id) throws EntityNotFoundException;

}
