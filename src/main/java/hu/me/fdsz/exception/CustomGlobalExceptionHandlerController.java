package hu.me.fdsz.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

//@EnableWebMvc
@ControllerAdvice
public class CustomGlobalExceptionHandlerController extends ResponseEntityExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(CustomGlobalExceptionHandlerController.class.getName());

    @ExceptionHandler({InvalidTokenException.class})
    public final ResponseEntity<CustomExceptionDTO> handleInvalidTokenException(InvalidTokenException ex, WebRequest request) {


        return new ResponseEntity<>(errors, HttpStatus.UNAUTHORIZED);
    }


}
