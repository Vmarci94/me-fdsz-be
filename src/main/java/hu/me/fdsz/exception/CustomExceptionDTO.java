package hu.me.fdsz.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString(of = {"httpStatus"})
@EqualsAndHashCode(of = {"httpStatus"})
public class CustomExceptionDTO {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime timestamp;
    private HttpStatus httpStatus;
    private String error;

}
