package ie.ait.ria.springrestbootstrap.exception;

import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  private final Logger log;

  GlobalExceptionHandler(Logger log) { this.log = log; }

  private CustomErrorMessage logAndRespond(Exception ex) {
    log.error(ex.getLocalizedMessage(), ex);
    return new CustomErrorMessage(ex.getLocalizedMessage(), ex.getClass().getTypeName());
  }

  @ExceptionHandler(IllegalStateException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  CustomErrorMessage handleIllegalArgument(IllegalStateException ex) { return logAndRespond(ex); }

  private static class CustomErrorMessage {
    private final String customMessage;
    private final String exception;

    CustomErrorMessage(String customMessage, String exception) {
      this.customMessage = customMessage;
      this.exception = exception;
    }

    public String getCustomMessage() { return customMessage; }

    public String getException() { return exception; }
  }

}
