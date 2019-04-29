package hu.me.fdsz.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class InvalidTokenException extends RuntimeException {

    private final CustomExceptionDTO customExceptionDTO;

    public InvalidTokenException(CustomExceptionDTO customExceptionDTO) {
        super(customExceptionDTO.getErrorMessage());
        customExceptionDTO.setHttpStatus(HttpStatus.UNAUTHORIZED);
        this.customExceptionDTO = customExceptionDTO;
    }

}
