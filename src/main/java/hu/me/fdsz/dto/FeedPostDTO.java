package hu.me.fdsz.dto;

import lombok.*;
import org.springframework.core.io.InputStreamResource;

@NoArgsConstructor
@Getter
@Setter
@ToString(of = {"title"})
@EqualsAndHashCode
public class FeedPostDTO {

    private String title;

    private String contentText;

    private String imageUrl;

    private InputStreamResource image;

}
