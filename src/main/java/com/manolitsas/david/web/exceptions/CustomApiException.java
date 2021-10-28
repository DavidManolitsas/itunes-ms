package com.manolitsas.david.web.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

/**
 * Custom exception class to handle errors.
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CustomApiException extends RuntimeException {

  private static final String TECHNICAL_ERROR_CODE = "500";
  private static final String TECHNICAL_ERROR_MESSAGE = "A Technical Exception has occurred";

  private final String code;
  private final HttpStatus httpStatus;
  private final String httpReasonPhrase;

  /**
   * Custom API exception constructor.
   *
   * @param message error message
   * @param cause exception that was thrown
   * @param code error code
   * @param httpStatus http status
   * @param httpReasonPhrase http reason phrase
   */
  public CustomApiException(
      String message,
      Throwable cause,
      String code,
      HttpStatus httpStatus,
      String httpReasonPhrase) {
    super(message, cause);
    this.code = code;
    this.httpStatus = httpStatus;
    this.httpReasonPhrase = httpReasonPhrase;
  }

  /**
   * Custom API exception constructor.
   *
   * @param message error message
   * @param code error code
   * @param httpStatus http status
   * @param httpReasonPhrase http reason phrase
   */
  public CustomApiException(
      String message, String code, HttpStatus httpStatus, String httpReasonPhrase) {
    super(message);
    this.code = code;
    this.httpStatus = httpStatus;
    this.httpReasonPhrase = httpReasonPhrase;
  }

  /**
   * General technical exception.
   *
   * @param message error message
   * @param cause exception that was thrown
   * @return Custom API exception
   */
  public static CustomApiException generalTechnicalException(String message, Throwable cause) {
    return new CustomApiException(
        message,
        cause,
        TECHNICAL_ERROR_CODE,
        HttpStatus.INTERNAL_SERVER_ERROR,
        HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
  }

  /**
   * General technical exception.
   *
   * @param cause exception that was thrown
   * @return Custom API exception
   */
  public static CustomApiException generalTechnicalException(Throwable cause) {
    return new CustomApiException(
        TECHNICAL_ERROR_MESSAGE,
        cause,
        TECHNICAL_ERROR_CODE,
        HttpStatus.INTERNAL_SERVER_ERROR,
        HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
  }
}
