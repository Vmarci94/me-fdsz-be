package hu.me.fdsz.Service.api;

import hu.me.fdsz.dto.FeedPostDTO;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface FeedService {

    List<FeedPostDTO> getAll();

    void add(FeedPostDTO feedPostDTO);

    ResponseEntity<HttpStatus> setContent(Long feedPostId, MultipartFile file) throws IOException, SQLException;

    ResponseEntity<InputStreamResource> getContent(Long feedPostId);

}
