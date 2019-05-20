package hu.me.fdsz.model;

import hu.me.fdsz.model.enums.Role;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@ToString(of = "userName")
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

    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "image")
    private Image image;

    @OneToMany
    @JoinColumn(name = "room_owner", referencedColumnName = "id")
    private List<Reservation> reservationList;

    @OneToMany
    @JoinColumn(name = "author", referencedColumnName = "id")
    private List<FeedPost> feedPostList;

}
