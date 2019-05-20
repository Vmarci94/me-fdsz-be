package hu.me.fdsz.Service.api;

import hu.me.fdsz.dto.FeedPostDTO;
import hu.me.fdsz.model.FeedPost;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FeedService {

    List<FeedPostDTO> getAll();

    FeedPost add(FeedPostDTO feedPostDTO);

    ResponseEntity<HttpStatus> setContent(Long feedPostId, MultipartFile file);

    FeedPostDTO getContent(Long feedPostId);

    boolean delete(Long feedPostId);

    FeedPostDTO update(FeedPostDTO feedPostDTO);

}
