package hu.me.fdsz.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "user")
@EqualsAndHashCode(of = {"id"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "personal_name")
    private String personalName;

    @Column(name = "username", nullable = false)
    private String userName;

    @Column(nullable = false)
    private String password;

    private String phoneNumber;

    private String location;


    @Column(name = "roles", nullable = false)
    @ElementCollection(fetch = FetchType.EAGER)
    List<Role> roles;

}
