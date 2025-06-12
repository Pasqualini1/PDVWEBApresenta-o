package com.eduardo.pdv_web.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "produto")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 60)
    private String nome;

    @Column(nullable = false)
    private Double preco;

    @Column(nullable = false, length = 60)
    private String categoria;

    @Column(length = 255)
    private String descricao;
}
