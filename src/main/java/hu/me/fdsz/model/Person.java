package hu.me.fdsz.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString(of = {"title", "firstName", "secoundName"})
@EqualsAndHashCode(of = "id")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NoArgsConstructor
public abstract class Person implements Serializable {

    @Id
    @GeneratedValue
    private long id;

    private String title;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String secoundName;

    private String fullName;

    @Column(nullable = false)
    private LocalDate birthDay;

    private String phoneNumber;

    private String location;

}
