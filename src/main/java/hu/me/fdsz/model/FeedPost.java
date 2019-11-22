package hu.me.fdsz.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString(callSuper = true, onlyExplicitlyIncluded = true)
@Table(name = "post")
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class FeedPost extends BaseEntity {

    @Column(name = "title", nullable = false)
    @ToString.Include
    private String title;

    @Column(name = "introduction", nullable = false)
    private String introduction;

    @Column(name = "content_text")
    private String contentText;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image", referencedColumnName = "id")
    public Image image;

    @OneToOne
    @JoinColumn(name = "author", referencedColumnName = "id")
    private User author;


}
