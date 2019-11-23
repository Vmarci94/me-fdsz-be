package hu.me.fdsz.dto;

import hu.me.fdsz.model.enums.Role;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Temporal;
import java.util.Date;

import static javax.persistence.TemporalType.DATE;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class UserDTO {

    @ToString.Include
    @EqualsAndHashCode.Include
    private String email;

    private String title;

    private String firstName;

    private String secoundName;

    private String username;

    private String password;

    private String phoneNumber;

    private String location;

    @Temporal(DATE)
    private Date birthDay;

    private Role role;

    private Long imageId;

}
