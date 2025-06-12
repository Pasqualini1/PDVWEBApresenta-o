// Define o pacote onde essa classe está localizada
package com.eduardo.pdv_web.dto;

// Importa anotações de validação
import jakarta.validation.constraints.*;

// Importa anotações do Lombok
import lombok.*;

// Lombok: Gera automaticamente os métodos getters, setters, equals, hashCode e toString
@Data

// Lombok: Gera um construtor com todos os atributos como parâmetros
@AllArgsConstructor

// Lombok: Gera um construtor sem parâmetros (vazio)
@NoArgsConstructor
public class ItemVendaDTO {

    // Campo obrigatório (não pode ser nulo)
    // Representa o ID do produto que está sendo vendido
    @NotNull(message = "O ID do produto é obrigatório")
    private Long produtoId;

    // Campo obrigatório (não pode ser nulo)
    // Valor mínimo: 1 (não é permitido vender 0 ou quantidade negativa)
    @NotNull(message = "A quantidade é obrigatória")
    @Min(value = 1, message = "A quantidade mínima deve ser 1")
    private Integer quantidade;
}
