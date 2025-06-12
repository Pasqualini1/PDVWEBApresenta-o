// Pacote onde a classe está localizada
package com.eduardo.pdv_web.dto;

// Importa anotação para formatação de datas no JSON
import com.fasterxml.jackson.annotation.JsonFormat;

// Lombok: importa anotações para gerar código repetitivo automaticamente
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

// Importa classes Java padrão
import java.time.LocalDateTime;
import java.util.List;

// Lombok: Gera automaticamente getters, setters, equals, hashCode, toString
@Data

// Lombok: Permite usar o padrão de projeto Builder para criar instâncias dessa classe
@Builder

// Lombok: Gera um construtor com todos os atributos como parâmetros
@AllArgsConstructor
public class ApiExceptionDTO {

    // Data e hora em que o erro aconteceu
    // Essa anotação formata o campo para aparecer nesse formato no JSON de resposta: "2025-06-12T14:30:00"
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime timestamp;

    // Código HTTP do erro (ex: 404, 500, 400, etc.)
    private int status;

    // Caminho da requisição onde o erro aconteceu (ex: "/api/clientes")
    private String path;

    // Lista de mensagens de erro associadas à requisição
    private List<String> errors;

    // Construtor alternativo que recebe apenas um erro (String)
    public ApiExceptionDTO(int status, String path, String error) {
        this.timestamp = LocalDateTime.now(); // Define a data/hora atual automaticamente
        this.status = status;
        this.path = path;
        this.errors = List.of(error); // Converte o erro único para uma lista com um único elemento
    }

    // Construtor alternativo que recebe uma lista de erros
    public ApiExceptionDTO(int status, String path, List<String> errors) {
        this.timestamp = LocalDateTime.now(); // Define a data/hora atual automaticamente
        this.status = status;
        this.path = path;
        this.errors = errors;
    }
}
