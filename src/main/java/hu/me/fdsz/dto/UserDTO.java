package hu.me.fdsz.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "email")
public class UserDTO {

    private String email;

    private String personalName;

    private String userName;

    private String password;

    private String phoneNumber;

    private String location;

}
