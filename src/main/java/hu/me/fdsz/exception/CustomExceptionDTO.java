package hu.me.fdsz.exception;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CustomExceptionDTO {

    private LocalDateTime timestamp = LocalDateTime.now();
    private String errorMessage;

    public CustomExceptionDTO(InvalidTokenException ite) {
        errorMessage = ite.getMessage();
    }

    public CustomExceptionDTO(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
