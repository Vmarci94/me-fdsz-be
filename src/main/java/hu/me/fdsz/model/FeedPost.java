package hu.me.fdsz.model;

import lombok.*;

import javax.persistence.*;

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

    @OneToOne
    @JoinColumn(name = "image", referencedColumnName = "id")
    public Image image;

    @OneToOne
    @JoinColumn(name = "author", referencedColumnName = "id")
    private User author;


}
