package hu.me.fdsz.exception;

import hu.me.fdsz.model.dto.CustomExceptionDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.naming.AuthenticationException;
import java.nio.file.AccessDeniedException;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(CustomGlobalExceptionHandler.class.getName());

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<CustomExceptionDTO> handleInvalidTokenException(InvalidTokenException ex, WebRequest request) {
        logger.error(ex.getErrorMessage());
        return ex.getResponse();
    }

    @ExceptionHandler(value = {AccessDeniedException.class, AuthenticationException.class})
    public ResponseEntity<HttpStatus> authenticationError(Exception e) {
        logger.error("Nincs megfelelő jogosultság a funkció eléréséhez", e);
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }


}
