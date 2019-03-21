package hu.me.fdsz.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Getter
@NoArgsConstructor
public class UserFormDTO {

    private String email;

    private String personalName;

    private String userName;

    private String password;

}
