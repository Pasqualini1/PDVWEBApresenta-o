package com.eduardo.pdv_web.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "item_venda")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemVenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "venda_id")
    private Venda venda;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    private Integer quantidade;

    private Double subtotal;
}
