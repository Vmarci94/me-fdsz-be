package hu.me.fdsz.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "email")
public class UserDTO {

    private String email;

    private String title;

    private String firstName;

    private String secoundName;

    private String userName;

    private String password;

    private String phoneNumber;

    private String location;

    @Temporal(TemporalType.DATE)
    private Date birthDay;

}
