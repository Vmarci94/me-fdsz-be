package hu.me.fdsz.service.impl;

import hu.me.fdsz.model.dto.MainDTO;
import hu.me.fdsz.service.api.MainPageService;
import hu.me.fdsz.service.api.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class MainPageServiceImpl implements MainPageService {

    private final PostService postService;

    private final static int MAIN_POST_ID = -1;

    @Autowired
    public MainPageServiceImpl(PostService postService) {
        this.postService = postService;
    }

    @Override
    public MainDTO getMainPageInfo() {
        final MainDTO.MainDTOBuilder resultBuilder = MainDTO.builder();
        postService.findById(MAIN_POST_ID).ifPresentOrElse(post -> {
            resultBuilder.highlightList(postService.getAllOrderedByCreatedDate())
                    .introduction(post.getIntroduction())
                    .contentText(post.getContentText())
                    .title(post.getTitle());
        }, () -> {
            throw new EntityNotFoundException();
        });
        return resultBuilder.build();
    }

}
