package hu.me.fdsz.service.api;

import hu.me.fdsz.dto.FeedPostDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.naming.AuthenticationException;
import java.util.List;

@Service
public interface FeedService {

    List<FeedPostDTO> getAll();

    FeedPostDTO add(FeedPostDTO feedPostDTO, MultipartFile image) throws AuthenticationException;

    ResponseEntity<HttpStatus> setContent(Long feedPostId, MultipartFile file);

    FeedPostDTO getContent(Long feedPostId);

    @Transactional(readOnly = true)
    List<FeedPostDTO> getPostsWithLimit(int limit);

    FeedPostDTO findById(long postId);

    FeedPostDTO update(FeedPostDTO feedPostDTO, MultipartFile multipartFile) throws AuthenticationException;

    @Transactional
    boolean delete(long postId);
}
