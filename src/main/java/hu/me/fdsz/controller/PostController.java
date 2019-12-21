package hu.me.fdsz.controller;

import hu.me.fdsz.model.dto.FeedPostDTO;
import hu.me.fdsz.model.entity.Post;
import hu.me.fdsz.service.api.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.naming.AuthenticationException;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping(value = "/post")
public class PostController {

    private final PostService postService;

    private final ModelMapper modelMapper;

    @Autowired
    public PostController(PostService postService, ModelMapper modelMapper) {
        this.postService = postService;
        this.modelMapper = modelMapper;
    }

    @GetMapping(value = "/get-top-posts", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<FeedPostDTO> getTopFeedPost(@RequestParam(value = "limit") int limit) {
        return postService.getPostsWithLimit(limit);
    }

    @GetMapping(value = "/get-all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<FeedPostDTO> getAllPosts() {
        return postService.getAllOrderedByCreatedDate();

    }

    @PutMapping(value = "/add", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void addNewFeedPost(@RequestPart(name = "newFeedPost") FeedPostDTO feedPostDTO,
                               @RequestPart(name = "image", required = false) MultipartFile image) throws AuthenticationException {
        postService.add(feedPostDTO, image);
    }

    @PostMapping(value = "/update", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<FeedPostDTO> update(@RequestPart(name = "newFeedPost") FeedPostDTO feedPostDTO,
                                              @RequestPart(name = "image", required = false) MultipartFile image) throws RuntimeException, AuthenticationException {
        return new ResponseEntity<>(postService.update(feedPostDTO, image), HttpStatus.OK);
    }

    @DeleteMapping(value = "delete/{postId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> deletePost(@PathVariable("postId") long postId) {
        return new ResponseEntity<>(postService.delete(postId) ? HttpStatus.OK : HttpStatus.NOT_EXTENDED);
    }

    @GetMapping(value = "/{postId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public FeedPostDTO getPost(@PathVariable("postId") long postId) {
        return postService.findById(postId)
                .map(post -> modelMapper.getTypeMap(Post.class, FeedPostDTO.class).map(post))
                .orElseThrow(EntityNotFoundException::new);
    }

}
