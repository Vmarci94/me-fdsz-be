package hu.me.fdsz.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString(callSuper = true, onlyExplicitlyIncluded = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NoArgsConstructor
public abstract class Person extends BaseEntity {

    @ToString.Include
    private String title = "";

    @Column(nullable = false)
    @ToString.Include
    private String firstName;

    @Column(nullable = false)
    @ToString.Include
    private String secoundName;

    private String fullName = "";

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthDay;

    private String phoneNumber;

    private String location;

}
