package hu.me.fdsz.dto;

import hu.me.fdsz.model.User;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString(of = {"title"})
@EqualsAndHashCode
public class FeedPostDTO {

    private long id;

    private String title;

    private String contentText;

    private String imageSrc;

    private User author;

}
