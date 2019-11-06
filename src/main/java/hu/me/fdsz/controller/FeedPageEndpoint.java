package hu.me.fdsz.controller;

import hu.me.fdsz.Service.api.FeedService;
import hu.me.fdsz.dto.FeedPostDTO;
import hu.me.fdsz.model.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/feeds")
public class FeedPageEndpoint {

    private final FeedService feedService;

    @Autowired
    public FeedPageEndpoint(FeedService feedService) {
        this.feedService = feedService;
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

    @GetMapping(value = "/test", produces = MediaType.APPLICATION_JSON_VALUE)
    public String test() {
        return "oh no";
    }

    @RequestMapping(value = "/files/{fileId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public FeedPostDTO getImage(@PathVariable("fileId") Long id) {
        return feedService.getContent(id);
    }
}
