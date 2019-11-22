package hu.me.fdsz.service.api;

import hu.me.fdsz.model.Image;
import hu.me.fdsz.model.Util.HasImage;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public interface ImageService {

    Optional<Image> addNewImage(MultipartFile multipartFile);

    Image createImageFromMultipartFile(MultipartFile multipartFile) throws IOException;

    ResponseEntity<?> getImage(long id);

    @Transactional
    boolean deleteImage(long id);

    <T extends HasImage> T updateImage(T entityWithImage, MultipartFile multipartFile);
}
