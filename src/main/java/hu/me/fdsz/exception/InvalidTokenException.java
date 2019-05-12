package hu.me.fdsz.exception;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(of = "errorMessage")
public class InvalidTokenException extends RuntimeException {

    private HttpStatus status;
    private String errorMessage;

    public InvalidTokenException(String msg, HttpStatus status, Throwable throwable) {
        super(msg, throwable);
        this.status = status;
    }

    public InvalidTokenException(String message, HttpStatus status) {
        super();
        this.status = status;
        this.errorMessage = message;
    }

    public int getStatusCode() {
        return status.value();
    }

    public ResponseEntity<CustomExceptionDTO> getResponse() {
        HttpHeaders headers = new HttpHeaders();
        CustomExceptionDTO body = new CustomExceptionDTO(this.getMessage());
        return new ResponseEntity<>(body, headers, status);
    }

}
