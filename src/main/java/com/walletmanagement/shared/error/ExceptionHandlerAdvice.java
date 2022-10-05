package com.walletmanagement.shared.error;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

  @ExceptionHandler({ MethodArgumentNotValidException.class })
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  private ApiError handlerValidationException(
      MethodArgumentNotValidException exception,
      HttpServletRequest request) {
    BindingResult result = exception.getBindingResult();
    Map<String, String> validationErrors = new HashMap<>();

    for (FieldError fieldError : result.getFieldErrors()) {
      validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
    }

    return new ApiError(400, "validation error", request.getContextPath() + request.getServletPath(), validationErrors);
  }

}
