package hu.me.fdsz.model;

import lombok.*;
import org.springframework.content.commons.annotations.ContentId;
import org.springframework.content.commons.annotations.ContentLength;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "post")
@EqualsAndHashCode(of = {"id"})
public class FeedPost {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content_text")
    private String contentText;

    @Column(name = "iamge_url")
    private String imageUrl;

    @ContentId
    private UUID contentId;
    @ContentLength
    private long contentLength;

    private String mimeType; //= "text/plain";

    @OneToOne
    @JoinColumn(name = "author", referencedColumnName = "id")
    private User author;


}
