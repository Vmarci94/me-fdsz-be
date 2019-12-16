package hu.me.fdsz.service.api;

import hu.me.fdsz.model.dto.FeedPostDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.naming.AuthenticationException;
import java.util.List;

@Service
public interface PostService {

    List<FeedPostDTO> getAll();

    FeedPostDTO add(FeedPostDTO feedPostDTO, MultipartFile image) throws AuthenticationException;

    @Transactional(readOnly = true)
    List<FeedPostDTO> getPostsWithLimit(int limit);

    FeedPostDTO findById(long postId);

    FeedPostDTO update(FeedPostDTO feedPostDTO, MultipartFile multipartFile) throws AuthenticationException;

    @Transactional
    boolean delete(long postId);
}
