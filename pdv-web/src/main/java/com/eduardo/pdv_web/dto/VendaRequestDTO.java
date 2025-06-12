// Define o pacote onde esta classe está localizada
package com.eduardo.pdv_web.dto;

// Importa anotações de validação
import jakarta.validation.constraints.NotNull;

// Importa anotações do Lombok para gerar código automaticamente
import lombok.*;

// Importa a classe List para representar a lista de itens da venda
import java.util.List;

// Lombok: Gera automaticamente os métodos getters, setters, toString, equals e hashCode
@Data

// Lombok: Gera um construtor com todos os campos da classe
@AllArgsConstructor

// Lombok: Gera um construtor vazio (sem parâmetros)
@NoArgsConstructor
public class VendaRequestDTO {

    // Campo obrigatório que representa o ID do cliente que está realizando a venda
    // Se não for informado, a validação falha com a mensagem definida
    @NotNull(message = "O ID do cliente é obrigatório")
    private Long clienteId;

    // Campo opcional para observações adicionais da venda (ex: entrega, desconto, etc)
    private String observacoes;

    // Campo obrigatório que representa a lista de itens da venda
    // Cada item é representado pela classe ItemVendaDTO
    // Se for nulo, a validação falha (pode-se adicionar mais validações se quiser, como lista vazia)
    @NotNull
    private List<ItemVendaDTO> itens;
}
