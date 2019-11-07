package hu.me.fdsz.dto;

import lombok.*;

import java.net.URI;

@NoArgsConstructor
@Getter
@Setter
@ToString(of = {"title"})
@EqualsAndHashCode
public class FeedPostDTO {

    private String title;

    private String introductionText;

    private String contentText;

    private ImageDTO image;

}
