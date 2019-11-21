package hu.me.fdsz.controller;

import hu.me.fdsz.dto.FeedPostDTO;
import hu.me.fdsz.service.api.FeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/feeds")
public class FeedPageEndpoint {

    private final FeedService feedService;

    @Autowired
    public FeedPageEndpoint(FeedService feedService) {
        this.feedService = feedService;
    }

    @GetMapping(value = "/get-top-posts", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<FeedPostDTO> getTopFeedPost(@RequestParam(value = "limit") int limit) {
        return feedService.getPostsWithLimit(limit);
    }

    @GetMapping(value = "/get-all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<FeedPostDTO> getAllPosts() {
        return feedService.getAll();

    }

    @PutMapping(value = "/add", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void addNewFeedPost(@RequestPart(name = "newFeedPost") FeedPostDTO feedPostDTO,
                               @RequestPart(name = "image") MultipartFile image) throws IOException {
        feedService.add(feedPostDTO, image);
    }

    @PostMapping(value = "/edit/{postId}", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public FeedPostDTO edit(@RequestPart(name = "newFeedPost") FeedPostDTO feedPostDTO,
                            @RequestPart(name = "image") MultipartFile image) throws IOException {
        return null;
    }

    @GetMapping(value = "/{postId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public FeedPostDTO getPost(@PathVariable("postId") long postId) {
        return feedService.findById(postId);
    }

}
