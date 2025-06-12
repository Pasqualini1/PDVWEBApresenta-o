package com.eduardo.pdv_web.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoRequestDTO {

    @NotBlank(message = "O nome do produto é obrigatório")
    @Size(min = 3, max = 60, message = "O nome deve ter entre 3 e 60 caracteres")
    private String nome;

    @NotNull(message = "O preço é obrigatório")
    @PositiveOrZero(message = "O preço deve ser um valor positivo")
    private Double preco;

    @NotBlank(message = "A categoria é obrigatória")
    @Size(min = 3, max = 60, message = "A categoria deve ter entre 3 e 60 caracteres")
    private String categoria;

    private String descricao;
}
