package hu.me.fdsz.exception;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {

    private final CustomExceptionDTO customExceptionDTO;

    public CustomException(String message, Throwable err, CustomExceptionDTO customExceptionDTO) {
        super(message, err);
        this.customExceptionDTO = customExceptionDTO;
    }

}
