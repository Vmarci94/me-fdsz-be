package hu.me.fdsz.service.api;

import hu.me.fdsz.model.dto.FeedPostDTO;
import hu.me.fdsz.model.entity.Post;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.naming.AuthenticationException;
import java.util.List;
import java.util.Optional;

@Service
public interface PostService {

    List<FeedPostDTO> getAllOrderedByCreatedDate();

    FeedPostDTO add(FeedPostDTO feedPostDTO, MultipartFile image) throws AuthenticationException;

    @Transactional(readOnly = true)
    List<FeedPostDTO> getPostsWithLimit(int limit);

    Optional<Post> findById(long postId);

    FeedPostDTO update(FeedPostDTO feedPostDTO, MultipartFile multipartFile) throws AuthenticationException;

    @Transactional
    boolean delete(long postId);
}
