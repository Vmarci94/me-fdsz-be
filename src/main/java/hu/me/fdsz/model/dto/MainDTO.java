package hu.me.fdsz.model.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class MainDTO {

    private String introduction;

    private String title;

    private String contentText;

    private List<FeedPostDTO> highlightList;


}
