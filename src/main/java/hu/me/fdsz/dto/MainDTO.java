package hu.me.fdsz.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class MainDTO {

    private String introduction;

    private List<FeedPostDTO> highlightList;

}
