package hu.me.fdsz.controller;

import hu.me.fdsz.Service.api.FeedService;
import hu.me.fdsz.dto.FeedPostDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(value = "/feeds")
public class FeedPageEndpoint {

    private final FeedService feedService;

    @Autowired
    public FeedPageEndpoint(FeedService feedService) {
        this.feedService = feedService;
    }

    @GetMapping(value = "/get-all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<FeedPostDTO> getAllPosts(){
        return feedService.getAll();

    }

    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public void addNewFeedPost(@RequestBody FeedPostDTO feedPostDTO){
        feedService.add(feedPostDTO);
    }


    @RequestMapping(value = "/upload-image/{feedPostId}", method = RequestMethod.PUT)
    public ResponseEntity<HttpStatus> addImage(@PathVariable("feedPostId") Long id, @RequestParam("image") MultipartFile file) {
        return feedService.setContent(id, file);
    }

    @RequestMapping(value = "/files/{fileId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public FeedPostDTO getImage(@PathVariable("fileId") Long id) {
        return feedService.getContent(id);
    }
}
