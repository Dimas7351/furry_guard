package com.app.furryguard.exceptions;

import lombok.extern.slf4j.Slf4j;
    import org.springframework.dao.InvalidDataAccessResourceUsageException;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.HttpStatusCode;
    import org.springframework.http.ProblemDetail;
    import org.springframework.http.ResponseEntity;
    import org.springframework.http.converter.HttpMessageNotReadableException;
    import org.springframework.validation.BindException;
    import org.springframework.validation.ObjectError;
    import org.springframework.web.bind.MethodArgumentNotValidException;
    import org.springframework.web.bind.annotation.ExceptionHandler;
    import org.springframework.web.bind.annotation.RestControllerAdvice;

    import java.time.ZonedDateTime;
    import java.util.*;
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<Object> handleValidationException(InvalidCredentialsException e) {
        log.warn("Validation error: {}", e.getMessage());

        CustomizedException customizeException = new CustomizedException(
                e.getMessage(),
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now()
        );
        return new ResponseEntity<>(customizeException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<Object> handleVEmailException(EmailAlreadyExistsException e) {
        log.warn("Email already exists: {}", e.getMessage());

            CustomizedException customizeException = new CustomizedException(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST,
                    ZonedDateTime.now()
            );
            return new ResponseEntity<>(customizeException, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(BindException.class)
    public ResponseEntity<ProblemDetail> handleBindException(BindException exception) {
        ProblemDetail problemDetail = ProblemDetail
                .forStatusAndDetail(HttpStatus.BAD_REQUEST, exception.getMessage());
        problemDetail.setProperty("errors",
                exception.getAllErrors().stream()
                        .map(ObjectError::getDefaultMessage)
                        .toList());
        return ResponseEntity.badRequest()
                .body(problemDetail);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleDeserializationError(HttpMessageNotReadableException ex) {
        log.error("Deserialization error: {}", Objects.requireNonNull(ex.getRootCause()).getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Некорректное значение в теле запроса: " + ex.getMessage());
    }

    @ExceptionHandler(InvalidDataAccessResourceUsageException.class)
    public ResponseEntity<String> handleInvalidDataAccessResourceUsageException(InvalidDataAccessResourceUsageException ex) {
        log.error("InvalidDataAccessResourceUsageException error: {}", ex.getMessage());
        return ResponseEntity
                .status(HttpStatusCode.valueOf(500))
                .body(HttpStatus.valueOf(500).getReasonPhrase() + " : " + ex.getMessage());
    }

//        @ExceptionHandler(MethodArgumentNotValidException.class)
//        public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
//            log.error(ex.getMessage(), ex.getCause());
//            return ResponseEntity
//                    .status(HttpStatus.BAD_REQUEST)
//                    .body(HttpStatus.BAD_REQUEST.getReasonPhrase() + " : " + ex.getMessage());
//        }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, List<String>> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.computeIfAbsent(error.getField(), key -> new ArrayList<>())
                    .add(error.getDefaultMessage());
        });

        return ResponseEntity.badRequest().body(Map.of(
                "status", "BAD REQUEST",
                "message", "Ошибка валидации",
                "errors", errors
        ));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleOtherExceptions(Exception ex) {
        log.error(ex.getMessage(), ex.getCause());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase() + " : " + ex.getMessage());
    }
}
