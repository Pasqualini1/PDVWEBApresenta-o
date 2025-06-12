// Define o pacote onde esta classe está localizada
package com.eduardo.pdv_web.dto;

// Importa as anotações de validação (como @NotNull, @NotBlank, etc)
import jakarta.validation.constraints.*;

// Importa as anotações do Lombok para gerar código automaticamente
import lombok.*;

// Lombok: Gera os métodos getters, setters, toString, equals e hashCode automaticamente
@Data

// Lombok: Gera um construtor com todos os campos da classe
@AllArgsConstructor

// Lombok: Gera um construtor vazio (sem parâmetros)
@NoArgsConstructor
public class ProdutoRequestDTO {

    // Valida que o campo não pode ser nulo nem vazio
    // Define mensagem personalizada caso a validação falhe
    @NotBlank(message = "O nome do produto é obrigatório")
    // Define o tamanho mínimo e máximo permitido para o nome do produto
    @Size(min = 3, max = 60, message = "O nome deve ter entre 3 e 60 caracteres")
    private String nome;

    // Valida que o preço não pode ser nulo
    @NotNull(message = "O preço é obrigatório")
    // Valida que o preço precisa ser positivo ou zero (não aceita valor negativo)
    @PositiveOrZero(message = "O preço deve ser um valor positivo")
    private Double preco;

    // Valida que a categoria não pode ser nula nem vazia
    @NotBlank(message = "A categoria é obrigatória")
    // Define o tamanho mínimo e máximo permitido para o nome da categoria
    @Size(min = 3, max = 60, message = "A categoria deve ter entre 3 e 60 caracteres")
    private String categoria;

    // Campo opcional para uma descrição do produto (sem validação)
    private String descricao;
}
