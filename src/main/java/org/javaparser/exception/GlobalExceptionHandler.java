package org.javaparser.exception;

import com.github.javaparser.ParseProblemException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleBadRequest(
            MethodArgumentNotValidException exception
    ){
        return ResponseEntity.badRequest()
                .body(
                        new ErrorResponse("Java code caanot be blank",
                                LocalDateTime.now())
                );
    }

    @ExceptionHandler(ParseProblemException.class)
    public ResponseEntity<ErrorResponse> handleParserException(
            ParseProblemException exception
    ){
        return ResponseEntity.badRequest()
                .body(
                        new ErrorResponse("Invalid Java code.",
                                LocalDateTime.now())
                );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(
            Exception exception
    ){
        return ResponseEntity.badRequest()
                .body(
                        new ErrorResponse("Unknown Error",
                                LocalDateTime.now())
                );
    }
}
