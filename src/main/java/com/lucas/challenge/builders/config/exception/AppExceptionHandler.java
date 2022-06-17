package com.lucas.challenge.builders.config.exception;

import com.lucas.challenge.builders.shared.dto.ResponseErrorDTO;
import com.lucas.challenge.builders.shared.messages.MessagesApiProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class AppExceptionHandler {

    @ExceptionHandler(value = { MethodArgumentNotValidException.class })
    protected ResponseEntity<ResponseErrorDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest httpRequest) {
        log.error(httpRequest.getRequestURI(), ex);
        List<String> errors = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add("[" + error.getField() + "] : " + error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add("[" + error.getObjectName() + "] : " + error.getDefaultMessage());
        }
        ResponseErrorDTO errorDTO = new ResponseErrorDTO();
        errorDTO.setMessagesApiProperties(MessagesApiProperties.E001);
        errorDTO.setErrorMessages(errors);
        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }
}
