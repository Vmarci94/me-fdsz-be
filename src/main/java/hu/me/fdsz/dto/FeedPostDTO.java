package hu.me.fdsz.dto;

import hu.me.fdsz.model.Image;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString(of = {"title"})
@EqualsAndHashCode
public class FeedPostDTO {

    private String title;

    private String introduction;

    private String contentText;

    private Image image;

}
