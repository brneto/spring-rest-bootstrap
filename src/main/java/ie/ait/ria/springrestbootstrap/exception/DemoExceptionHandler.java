package ie.ait.ria.springrestbootstrap.exception;

import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class DemoExceptionHandler {

  private final Logger log;

  DemoExceptionHandler(Logger log) { this.log = log; }

  private Exception logAndReturnException(Exception ex) {
    log.error(ex.getLocalizedMessage(), ex);
    return ex;
  }

  @ExceptionHandler(IllegalArgumentException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  Exception handleIllegalArgument(IllegalArgumentException ex) { return logAndReturnException(ex); }

  @ExceptionHandler(IllegalStateException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  Exception handleIllegalArgument(IllegalStateException ex) { return logAndReturnException(ex); }

}
