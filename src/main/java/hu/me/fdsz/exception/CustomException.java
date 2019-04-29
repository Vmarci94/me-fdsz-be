package hu.me.fdsz.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomException extends RuntimeException {

    private final HttpStatus httpStatus;

    public CustomException(String message, Throwable err, HttpStatus httpStatus) {
        super(message, err);
        this.httpStatus = httpStatus;
    }

}
