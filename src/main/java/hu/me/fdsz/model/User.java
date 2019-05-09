package hu.me.fdsz.model;

import hu.me.fdsz.model.enums.Role;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(of = "userName")
@Table(name = "user")
@EqualsAndHashCode(callSuper = true)
public class User extends Person {

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "username", nullable = false)
    private String userName;

    @Column(nullable = false)
    private String password;

    @Column(name = "roles", nullable = false)
    @ElementCollection(fetch = FetchType.EAGER)
    List<Role> roles;

}
