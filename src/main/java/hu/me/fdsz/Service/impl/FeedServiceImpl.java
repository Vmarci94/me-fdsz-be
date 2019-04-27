package hu.me.fdsz.Service.impl;

import hu.me.fdsz.Service.api.FeedService;
import hu.me.fdsz.Service.api.JwtTokenProvider;
import hu.me.fdsz.dto.FeedPostDTO;
import hu.me.fdsz.model.FeedPost;
import hu.me.fdsz.model.User;
import hu.me.fdsz.repository.FeedPostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FeedServiceImpl implements FeedService {

    private final FeedPostRepository feedPostRepository;

    private final ModelMapper modelMapper;

    private final JwtTokenProvider jwtTokenProvider;

    public FeedServiceImpl(FeedPostRepository feedPostRepository, ModelMapper modelMapper, JwtTokenProvider jwtTokenProvider) {
        this.feedPostRepository = feedPostRepository;
        this.modelMapper = modelMapper;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public List<FeedPostDTO> getAll() {
        List<FeedPostDTO> result = new ArrayList<>();
        feedPostRepository.findAll().iterator().forEachRemaining(feedPost -> result.add(modelMapper.map(feedPost, FeedPostDTO.class)));
        return result;
    }

    @Override
    public void add(FeedPostDTO feedPostDTO) {
        FeedPost newFeedPost = modelMapper.map(feedPostDTO, FeedPost.class);
        User currentUser = jwtTokenProvider.getUser();
        newFeedPost.setAuthor(currentUser);
        feedPostRepository.save(newFeedPost);
    }

}
