package hu.me.fdsz.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@Getter
@Setter
@ToString(of = {"title"})
@EqualsAndHashCode
public class FeedPostDTO {

    private String title;

    private String introductionText;

    private String contentText;

    private byte[] image;

}
