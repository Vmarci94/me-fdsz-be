package hu.me.fdsz.model.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class JWTTokenDTO {

    private String token;

    public JWTTokenDTO(String token) {
        this.token = token;
    }
}
