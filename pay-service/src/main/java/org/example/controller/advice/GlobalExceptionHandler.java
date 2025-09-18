package org.example.controller.advice;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.example.model.dto.ErrorDto;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.OffsetDateTime;

@RestControllerAdvice
@Getter
@Setter
@RequiredArgsConstructor
@Slf4j
public class GlobalExceptionHandler {
    @Value("${server.name}")
    private String MICROSERVICE_NAME;


    @ExceptionHandler({ResourceAccessException.class, HttpServerErrorException.class})
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    public ErrorDto handleExternalServiceErrors(RestClientException e, HttpServletRequest request) {
        log.error("Ошибка при обращении к внешнему сервису: {}", e.getMessage());
        return new ErrorDto(
                OffsetDateTime.now(),
                HttpStatus.BAD_GATEWAY.value(),
                HttpStatus.BAD_GATEWAY.getReasonPhrase(),
                "Внешний сервис временно недоступен. Повторите попытку позже.",
                request.getRequestURI(),
                MICROSERVICE_NAME,
                null,
                null
        );
    }

    @ExceptionHandler({MethodArgumentNotValidException.class, MissingServletRequestParameterException.class, MethodArgumentTypeMismatchException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto handleMethodArgumentNotValidException(Exception e, HttpServletRequest request) {
        log.error("Неверный параметр или не валидное значение параметра: {}", e.getMessage());
        return new ErrorDto(
                OffsetDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                "Неверный параметр или не валидное значение параметра",
                request.getRequestURI(),
                MICROSERVICE_NAME,
                null,
                null
        );
    }


    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    public ErrorDto handleMethodNotFoundException(HttpRequestMethodNotSupportedException e, HttpServletRequest request) {
        log.error("Метод запроса не поддерживается: {}", e.getMessage());
        return new ErrorDto(
                OffsetDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                "Метод запроса не поддерживается",
                request.getRequestURI(),
                MICROSERVICE_NAME,
                null,
                null
        );
    }



    @ExceptionHandler(HttpStatusCodeException.class)
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    public ErrorDto handleExternalServiceErrors(HttpStatusCodeException e, HttpServletRequest request) {
        log.error("Ошибка при обработке запроса на стороне внешнего сервиса: {}", e.getMessage());

        String body = e.getResponseBodyAsString();
        ErrorDto parsedExternalError = null;

        try {
            // Пытаемся распарсить как ErrorDto
            parsedExternalError = new ObjectMapper().readValue(body, ErrorDto.class);
        } catch (Exception ex) {
            log.warn("Не удалось распарсить ответ внешнего сервиса в ErrorDto, сохраняем как rawErrorMessage");
        }

        return new ErrorDto(
                OffsetDateTime.now(),
                HttpStatus.BAD_GATEWAY.value(),
                HttpStatus.BAD_GATEWAY.getReasonPhrase(),
                "Ошибка на стороне МС-2: не удалось выполнить запрос к внешнему сервису",
                request.getRequestURI(),
                MICROSERVICE_NAME, // имя текущего МС
                parsedExternalError, // может быть null
                parsedExternalError == null ? body : null // если не распарсили — кладём в rawErrorMessage
        );
    }
}
