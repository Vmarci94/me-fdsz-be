package hu.me.fdsz.dto;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString(of = {"title"})
@EqualsAndHashCode
public class FeedPostDTO {

    private String title;

    private String contentText;

    private String imageUrl;

}
