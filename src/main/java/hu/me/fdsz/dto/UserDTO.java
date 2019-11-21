package hu.me.fdsz.dto;

import hu.me.fdsz.model.enums.Role;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

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

    private LocalDate birthDay;

    private Role role;

    private Long imageId;

}
