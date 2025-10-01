package es.nextdigital.demo.config;

import es.nextdigital.dto.ErrorResponseDto;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotImplementedException.class)
    public ResponseEntity<ErrorResponseDto> handleNotImplemented(NotImplementedException ex) {

        ErrorResponseDto responseDto = ErrorResponseDto.builder()
                .message("Not Implemented")
                .code(String.valueOf(HttpStatus.NOT_IMPLEMENTED.value()))
                .build();

        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
                .body(responseDto);
    }
}

