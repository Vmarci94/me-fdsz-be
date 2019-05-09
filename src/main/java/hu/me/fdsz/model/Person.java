package hu.me.fdsz.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
@Getter
@Setter
@ToString(of = {"title", "firstName", "secoundName"})
@EqualsAndHashCode(of = "id")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Person {

    @Id
    @GeneratedValue
    private long id;

    private String title;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String secoundName;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date birthDay;

    private String phoneNumber;

    private String location;

    @Transient
    @Setter(AccessLevel.NONE)
    private String fullName;

    public Person() {
        this.fullName = Stream.of(title, firstName, secoundName)
                .filter(Objects::nonNull)
                .collect(Collectors.joining());
    }

}
