package hu.me.fdsz.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "email")
public class UserDTO {

    private String email;

    private String personalName;

    private String username;

    private String password;

}
