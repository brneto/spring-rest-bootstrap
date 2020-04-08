package ie.ait.ria.springrestbootstrap.exception;

import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DemoExceptionHandler {

  private final Logger log;

  DemoExceptionHandler(Logger log) { this.log = log; }

  private Exception logAndRespond(Exception ex) {
    log.error(ex.getLocalizedMessage(), ex);
    return ex;
  }

  @ExceptionHandler(IllegalArgumentException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  Exception handleIllegalArgument(IllegalArgumentException ex) { return logAndRespond(ex); }

  @ExceptionHandler(IllegalStateException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  Exception handleIllegalArgument(IllegalStateException ex) { return logAndRespond(ex); }

}
