package com.eduardo.pdv_web.controller;

import com.eduardo.pdv_web.dto.ApiExceptionDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
// Classe que intercepta exceções lançadas pelos controllers REST
// para tratar erros de forma centralizada e retornar respostas JSON padronizadas
public class ExceptionHandlerController {

    // Trata qualquer exceção genérica (Exception)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiExceptionDTO> handleGenericException(Exception e,
                                                                  HttpServletRequest request) {
        // Cria um DTO com status 500, caminho da requisição e mensagem da exceção
        ApiExceptionDTO dto = new ApiExceptionDTO(
                HttpStatus.INTERNAL_SERVER_ERROR.value(), // código 500
                request.getRequestURI(),                   // URL da requisição que gerou o erro
                e.getMessage()                            // mensagem da exceção lançada
        );
        // Retorna resposta HTTP 500 com o DTO no corpo
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(dto);
    }

    // Trata exceções específicas de validação de argumentos inválidos (@Valid)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiExceptionDTO> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException e, HttpServletRequest request) {

        // Lista para guardar mensagens detalhadas dos erros de validação
        List<String> errors = new ArrayList<>();

        // Itera sobre os erros dos campos inválidos, pegando nome do campo + mensagem
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            errors.add(fieldError.getField() + ": " + fieldError.getDefaultMessage());
        }

        // Cria um DTO com código 400, URL da requisição e lista de erros detalhados
        ApiExceptionDTO apiException = new ApiExceptionDTO(
                HttpStatus.BAD_REQUEST.value(),  // código 400
                request.getRequestURI(),          // URL que gerou o erro
                errors                          // lista de mensagens de erro
        );

        // Retorna resposta HTTP 400 com o DTO no corpo
        return ResponseEntity.badRequest().body(apiException);
    }
}
