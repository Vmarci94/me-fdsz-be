package hu.me.fdsz.controller;

import hu.me.fdsz.Service.api.FeedService;
import hu.me.fdsz.dto.FeedPostDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
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

    @GetMapping(value = "/test", produces = MediaType.APPLICATION_JSON_VALUE)
    public String test(){
        return "oh no";
    }

    @RequestMapping(value = "/files/{fileId}", method = RequestMethod.PUT)
    public ResponseEntity<HttpStatus> addImage(@PathVariable("fileId") Long id, @RequestParam("file") MultipartFile file) throws IOException, SQLException {
        return feedService.setContent(id, file);
    }

    @RequestMapping(value = "/files/{fileId}", method = RequestMethod.GET, produces = "image/*")
    public ResponseEntity getImage(@PathVariable("fileId") Long id) {
        return feedService.getContent(id);
    }


}
