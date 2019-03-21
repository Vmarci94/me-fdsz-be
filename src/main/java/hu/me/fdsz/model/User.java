package hu.me.fdsz.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "personal_name")
    private String personalName;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(nullable = false)
    private String password;

}