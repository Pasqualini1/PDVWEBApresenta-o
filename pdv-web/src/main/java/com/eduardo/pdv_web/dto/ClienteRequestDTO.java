// Pacote onde a classe está localizada
package com.eduardo.pdv_web.dto;

// Importa anotações de validação
import jakarta.validation.constraints.*;

// Lombok: anotações para gerar código repetitivo automaticamente
import lombok.*;

// Lombok: Gera automaticamente getters, setters, toString, equals e hashCode
@Data

// Lombok: Gera um construtor com todos os atributos como parâmetros
@AllArgsConstructor

// Lombok: Gera um construtor vazio (sem parâmetros)
@NoArgsConstructor
public class ClienteRequestDTO {

    // Campo obrigatório: não pode ser nulo ou vazio
    // Também exige que tenha entre 3 e 100 caracteres
    @NotBlank(message = "O nome é obrigatório")
    @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
    private String nome;

    // Campo obrigatório: não pode ser nulo ou vazio
    // Deve ter entre 11 e 14 caracteres (para aceitar CPF com ou sem pontos)
    @NotBlank(message = "O CPF é obrigatório")
    @Size(min = 11, max = 14, message = "Formato de CPF inválido")
    private String cpf;

    // Campo obrigatório e deve ser um e-mail válido
    @NotBlank(message = "O e-mail é obrigatório")
    @Email(message = "Formato de e-mail inválido")
    private String email;

    // Campo obrigatório: não pode ser nulo ou vazio
    @NotBlank(message = "O telefone é obrigatório")
    private String telefone;
}
