package hu.me.fdsz.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString(of = {"httpStatus"})
@EqualsAndHashCode(of = {"httpStatus"})
public class CustomExceptionDTO {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime timestamp = LocalDateTime.now();
    private HttpStatus httpStatus;
    private String errorMessage;

    public CustomExceptionDTO(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public CustomExceptionDTO(HttpStatus httpStatus, String errorMessage) {
        this.httpStatus = httpStatus;
        this.errorMessage = errorMessage;
    }
}
