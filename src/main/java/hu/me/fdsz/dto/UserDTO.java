package hu.me.fdsz.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = {"email", "userName"})
public class UserDTO {

    private String email;

    private String title;

    private String firstName;

    private String secoundName;

    private String userName;

    private String password;

    private String phoneNumber;

    private String location;

    private LocalDate birthDay;

}
