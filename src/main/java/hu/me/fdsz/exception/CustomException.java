package hu.me.fdsz.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Getter
@Setter
public class CustomException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String message;
    private final HttpStatus httpStatus;

    public CustomException(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
