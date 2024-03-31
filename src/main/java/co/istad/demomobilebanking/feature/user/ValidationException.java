package co.istad.demomobilebanking.feature.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@RestControllerAdvice
public class ValidationException {

    @ExceptionHandler(ResponseStatusException.class)
    ResponseEntity<?>handlerServiceError(ResponseStatusException ex){
        return ResponseEntity.status(ex.getStatusCode())
                .body(Map.of("error", Objects.requireNonNull(ex.getReason())));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String,Object> handleValidationError(MethodArgumentNotValidException ex){
        List<Map<String,Object>> errors = new ArrayList<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(fieldError -> {
                    Map<String,Object> error =new HashMap<>();
                    error.put("field",fieldError.getField());
                    error.put("message",fieldError.getDefaultMessage());
                    errors.add(error);
                });
        return Map.of("errors",errors);
    }
}
