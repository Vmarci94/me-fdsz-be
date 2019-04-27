package hu.me.fdsz.Service.api;

import hu.me.fdsz.dto.FeedPostDTO;

import java.util.List;

public interface FeedService {

    List<FeedPostDTO> getAll();

    void add(FeedPostDTO feedPostDTO);

}
