package hu.me.fdsz.dto;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString(of = {"title"})
@EqualsAndHashCode
public class FeedPostDTO {

    private Long id;

    private String title;

    private String introduction;

    private String contentText;

    private Long imageId;

}
