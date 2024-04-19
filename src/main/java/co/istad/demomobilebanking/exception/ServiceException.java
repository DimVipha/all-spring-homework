package co.istad.demomobilebanking.exception;

import co.istad.demomobilebanking.base.BaseError;
import co.istad.demomobilebanking.base.BaseErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;
import java.util.Objects;

@RestControllerAdvice
public class ServiceException {
    @ExceptionHandler(ResponseStatusException.class)
    ResponseEntity<?> handlerServiceError(ResponseStatusException ex){
        BaseError<String> baseError = new BaseError<>();
        baseError.setCode(ex.getStatusCode().toString());
        baseError.setDescription(ex.getReason());
        BaseErrorResponse baseErrorResponse = new BaseErrorResponse();
        baseErrorResponse.setError(baseError);
        return ResponseEntity.ok(baseErrorResponse);
    }

}