package hu.me.fdsz.model;

import hu.me.fdsz.model.enums.Role;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@ToString(of = "userName")
@Table(name = "user")
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class User extends Person {

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "username", nullable = false, unique = true)
    private String userName;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    private long image;

//    @OneToOne
//    @JoinColumn(name = "id", referencedColumnName = "image")
//    private Image image;

    @OneToMany
    @JoinColumn(name = "author", referencedColumnName = "id")
    private List<FeedPost> feedPostList;

}
