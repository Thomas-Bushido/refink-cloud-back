package org.example.backbuyerv1.exception;

import org.example.backbuyerv1.dto.buyer.LoginResponseDto;
import org.example.backbuyerv1.dto.buyer.RegisterResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.UUID;

@RestControllerAdvice
public class GeneralAuthExceptionHandler {
    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<RegisterResponseDto> UserAlreadyExistHandler (UserAlreadyExistException ex){
        RegisterResponseDto registerResponseDto = new RegisterResponseDto(UUID.randomUUID(),"firstName","lastName","emailAdress");
        return new ResponseEntity<>(registerResponseDto, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<LoginResponseDto> notFoundExceptionHandler (NotFoundException ex){
        LoginResponseDto loginResponseDto = LoginResponseDto.builder()
                .token("NotFound")
                .id(null)
                .build();
        return new ResponseEntity<>(loginResponseDto,HttpStatus.NOT_FOUND);
    }
}