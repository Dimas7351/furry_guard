    package com.app.furryguard.exceptions;

    import lombok.extern.slf4j.Slf4j;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ProblemDetail;
    import org.springframework.http.ResponseEntity;
    import org.springframework.http.converter.HttpMessageNotReadableException;
    import org.springframework.validation.BindException;
    import org.springframework.web.bind.annotation.ExceptionHandler;
    import org.springframework.web.bind.annotation.RestControllerAdvice;
    import org.springframework.validation.ObjectError;


    import java.time.ZonedDateTime;
    import java.util.Objects;

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
                 return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                     .body("Некорректное значение в теле запроса: " + Objects.requireNonNull(ex.getRootCause()).getMessage());
         }

    }
