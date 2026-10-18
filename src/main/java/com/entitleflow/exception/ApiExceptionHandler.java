package com.entitleflow.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.time.Instant;
import java.util.*;

@RestControllerAdvice
public class ApiExceptionHandler {
    public record ApiError(Instant timestamp,int status,String error,String message,Map<String,String> details) {}
    @ExceptionHandler(NotFoundException.class) ResponseEntity<ApiError> notFound(NotFoundException e){return response(HttpStatus.NOT_FOUND,e.getMessage(),Map.of());}
    @ExceptionHandler({ConflictException.class,DataIntegrityViolationException.class}) ResponseEntity<ApiError> conflict(Exception e){return response(HttpStatus.CONFLICT,e instanceof ConflictException?e.getMessage():"A record with the same unique value already exists",Map.of());}
    @ExceptionHandler(MethodArgumentNotValidException.class) ResponseEntity<ApiError> invalid(MethodArgumentNotValidException e){
        Map<String,String> details=new LinkedHashMap<>();e.getBindingResult().getFieldErrors().forEach(x->details.putIfAbsent(x.getField(),x.getDefaultMessage()));
        return response(HttpStatus.BAD_REQUEST,"Request validation failed",details);
    }
    @ExceptionHandler(IllegalArgumentException.class) ResponseEntity<ApiError> badRequest(IllegalArgumentException e){return response(HttpStatus.BAD_REQUEST,e.getMessage(),Map.of());}
    private ResponseEntity<ApiError> response(HttpStatus status,String message,Map<String,String> details){return ResponseEntity.status(status).body(new ApiError(Instant.now(),status.value(),status.getReasonPhrase(),message,details));}
}
