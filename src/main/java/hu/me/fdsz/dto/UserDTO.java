package hu.me.fdsz.dto;

import hu.me.fdsz.model.enums.Role;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@ToString(onlyExplicitlyIncluded = true, callSuper = false)
public class UserDTO extends GuestDTO {

    @ToString.Include
    @EqualsAndHashCode.Include
    private String email;

    private String username;

    private String password;

    private Role role;

    private Long imageId;

}
