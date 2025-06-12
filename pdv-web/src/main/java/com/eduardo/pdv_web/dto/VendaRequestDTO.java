package com.eduardo.pdv_web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendaRequestDTO {

    @NotNull(message = "O ID do cliente é obrigatório")
    private Long clienteId;

    private String observacoes;

    @NotNull
    private List<ItemVendaDTO> itens;
}
