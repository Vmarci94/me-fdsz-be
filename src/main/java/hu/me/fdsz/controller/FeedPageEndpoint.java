package hu.me.fdsz.controller;

import hu.me.fdsz.model.FeedPost;
import hu.me.fdsz.repository.FeedPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(value = "/feeds")
public class FeedPageEndpoint {

    private final FeedPostRepository feedPostRepository;

    @Autowired
    public FeedPageEndpoint(FeedPostRepository feedPostRepository) {
        this.feedPostRepository = feedPostRepository;
    }

    @GetMapping(value = "/get-all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<FeedPost> getAllPosts(){
        List<FeedPost> result = new ArrayList<>();
        feedPostRepository.findAll().iterator().forEachRemaining(result::add);
        return result;
    }

}
