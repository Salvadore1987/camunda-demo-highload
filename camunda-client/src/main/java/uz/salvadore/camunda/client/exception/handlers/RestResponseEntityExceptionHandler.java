package uz.salvadore.camunda.client.exception.handlers;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.TransactionException;
import org.hibernate.exception.LockAcquisitionException;
import org.modelmapper.MappingException;
import org.springframework.dao.CannotAcquireLockException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import uz.salvadore.camunda.client.dto.ErrorMessageDTO;
import uz.salvadore.camunda.client.exception.DomainException;

import javax.validation.ConstraintViolationException;

@Slf4j
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(value = {
    MappingException.class,
    DomainException.class,
    ConstraintViolationException.class
  })
  protected ResponseEntity<ErrorMessageDTO> handleConflict(
    final RuntimeException ex, final WebRequest request) {
    log.error(ex.getMessage());
    final String errorMessage = ex.getMessage();
    final int code = 1000000;
    log.info("ErrorMessage: {} - {}", code, errorMessage);
    return new ResponseEntity<>(new ErrorMessageDTO(code, errorMessage), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(value = {
    TransactionException.class,
    LockAcquisitionException.class
  })
  protected ResponseEntity<ErrorMessageDTO> transactionalException(final RuntimeException ex,
                                                                   final WebRequest request) {
    log.error(ex.getMessage());
    final String errorMessage = "Transactional exception";
    final int code = 1000000;
    log.info("ErrorMessage: {} - {}", code, errorMessage);
    return new ResponseEntity<>(new ErrorMessageDTO(code, errorMessage), HttpStatus.BAD_REQUEST);
  }
}
