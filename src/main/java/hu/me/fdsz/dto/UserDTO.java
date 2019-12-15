package hu.me.fdsz.dto;

import hu.me.fdsz.model.enums.Role;
import lombok.*;

import javax.persistence.Temporal;
import java.util.Date;

import static javax.persistence.TemporalType.DATE;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class UserDTO {

    private Long id;

    @ToString.Include
    @EqualsAndHashCode.Include
    private String email;

    private String username;

    private String password;

    private Role role;

    private Long imageId;

    private String title;

    private String firstName;

    private String secoundName;

    private String fullName;

    private String phoneNumber;

    private String location;

    @Temporal(DATE)
    private Date birthDay;

    private Boolean admin;

}
