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
    Long id;

    @Column(name = "title", nullable = false)
    String Title;

    @Column(name = "content_text")
    String contentText;

    @OneToOne
    @JoinColumn(name = "author", referencedColumnName = "id")
    private User author;


}
