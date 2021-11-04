package com.manolitsas.david.handler;

import com.manolitsas.david.exception.CustomApiException;
import com.manolitsas.david.model.Error;
import java.time.OffsetDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@RequiredArgsConstructor
public class ExceptionHandlerAdvice {

  /**
   * This method is used to handle custom API exceptions.
   *
   * @param exception the not found exception
   * @return custom error message and http status
   */
  @ExceptionHandler(CustomApiException.class)
  public ResponseEntity<Error> handleCustomApiExceptions(CustomApiException exception) {
    final Error error =
        new Error()
            .timestamp(OffsetDateTime.now())
            .status(exception.getHttpStatus().value())
            .error(exception.getHttpReasonPhrase())
            .message(exception.getMessage());
    return new ResponseEntity<>(error, exception.getHttpStatus());
  }
}
