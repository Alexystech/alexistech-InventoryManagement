package com.itsx.alexis.config;

import com.google.common.collect.ImmutableMap;
import com.itsx.alexis.service.exception.SupportedExceptions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@ControllerAdvice(annotations = RestController.class)
public class RestExceptionInterceptor {

    @ExceptionHandler
    public ResponseEntity<Map<Object, Object>> processSupportedExceptions(Throwable throwable) {
        Optional<ResponseEntity<Map<Object, Object>>> supportedException = Arrays.stream(SupportedExceptions.values())
                .filter(exception -> hasSameClass(exception, throwable))
                .map(this::createResponseFromException)
                .findFirst();

        return supportedException.orElseGet(() -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    private Boolean hasSameClass(SupportedExceptions exception, Throwable throwable) {
        return throwable.getClass().equals(exception.getExceptionClass());
    }

    private ResponseEntity<Map<Object, Object>> createResponseFromException(SupportedExceptions exception) {
        Objects.requireNonNull(exception);
        return new ResponseEntity<>(ImmutableMap.builder()
                .put("status", exception.getHttpStatus().value())
                .put("error", exception.getHttpStatus().getReasonPhrase())
                .build(), exception.getHttpStatus());
    }

}
