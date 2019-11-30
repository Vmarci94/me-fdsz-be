package hu.me.fdsz.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.TemporalType.DATE;

@Entity
@Getter
@Setter
@ToString(callSuper = true, onlyExplicitlyIncluded = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NoArgsConstructor
public abstract class Person extends BaseEntity {

    @ToString.Include
    private String title;

    @Column(nullable = false)
    @ToString.Include
    private String firstName;

    @Column(nullable = false)
    @ToString.Include
    private String secoundName;

    private String fullName = "";

    @Temporal(DATE)
    private Date birthDay;

    private String phoneNumber;

    private String location;

}
