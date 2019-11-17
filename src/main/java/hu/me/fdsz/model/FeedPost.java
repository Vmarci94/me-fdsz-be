package hu.me.fdsz.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString(of = {"id", "title"})
@Table(name = "post")
@EqualsAndHashCode(of = {"id"})
public class FeedPost {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "introduction", nullable = false)
    private String introduction;

    @Column(name = "content_text")
    private String contentText;

    @OneToOne
    @JoinColumn(name = "image", referencedColumnName = "id")
    public Image image;

    @OneToOne
    @JoinColumn(name = "author", referencedColumnName = "id")
    private User author;

    @Column(name = "last_modification", nullable = false)
    private LocalDate lastModification = LocalDate.now();


}
