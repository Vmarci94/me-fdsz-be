package hu.me.fdsz.model.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.*;
import java.util.Optional;

@Entity
@Getter
@Setter
//@Table(name = "post")
@ToString(callSuper = true, onlyExplicitlyIncluded = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class Post extends BaseEntity implements HasImage {

    @Column(name = "title", nullable = false)
    @ToString.Include
    private String title;

    @Column(name = "introduction", length = Integer.MAX_VALUE, nullable = false)
    private String introduction;

    @Column(name = "content_text", length = Integer.MAX_VALUE)
    private String contentText;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image", referencedColumnName = "id")
    public Image image;

    @CreatedBy
    @ManyToOne
    @JoinColumn(name = "author", referencedColumnName = "id")
    private User author;

    @LastModifiedBy
    @ManyToOne
    @JoinColumn(name = "last_modified_by", referencedColumnName = "id")
    private User lastModifiedBy;

    @Override
    public Optional<Image> getImage() {
        return Optional.ofNullable(image);
    }

    @Override
    public void setImage(Image image) {
        this.image = image;
    }


}
