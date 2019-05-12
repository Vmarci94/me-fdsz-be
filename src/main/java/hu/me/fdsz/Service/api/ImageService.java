package hu.me.fdsz.Service.api;

import hu.me.fdsz.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {

    Image addNewImage(MultipartFile rawImage) throws IOException;

}
