package com.example.transfermoney.exception;

import com.example.transfermoney.errors.InvalidData;
import com.example.transfermoney.model.ResponseTransfer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionHandlerTransfer {

    @ResponseBody
    @ExceptionHandler(InvalidData.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseError credentialsHandler(InvalidData e) {
        return new ResponseError(null,e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseError argumentValidationException(
            MethodArgumentNotValidException e) {
        return new ResponseError(null,e.getMessage());
    }
}
