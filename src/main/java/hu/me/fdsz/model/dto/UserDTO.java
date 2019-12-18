package hu.me.fdsz.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import hu.me.fdsz.model.enums.Role;
import lombok.*;

import java.util.Date;

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

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthDay;

    private Boolean admin;

}
