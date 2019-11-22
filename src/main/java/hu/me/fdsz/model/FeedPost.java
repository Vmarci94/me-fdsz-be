package hu.me.fdsz.model;

import hu.me.fdsz.model.Util.HasImage;
import lombok.*;

import javax.persistence.*;
import java.util.Optional;

@Entity
@Getter
@Setter
@ToString(callSuper = true, onlyExplicitlyIncluded = true)
@Table(name = "post")
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class FeedPost extends BaseEntity implements HasImage {

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

    @Override
    public Optional<Image> getImage() {
        return Optional.ofNullable(image);
    }

    @Override
    public void setImage(Image image) {
        this.image = image;
    }


}
