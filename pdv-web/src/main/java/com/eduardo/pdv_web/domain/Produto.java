// Define o pacote onde esta classe está localizada
package com.eduardo.pdv_web.domain;

// Importações das anotações do JPA e do Lombok
import jakarta.persistence.*;
import lombok.*;

// Indica que essa classe será mapeada como uma entidade no banco de dados
@Entity

// Define que o nome da tabela no banco será "produto"
@Table(name = "produto")

// Lombok: Gera automaticamente getters, setters, toString, equals e hashCode
@Data

// Lombok: Gera um construtor com todos os atributos como parâmetros
@AllArgsConstructor

// Lombok: Gera um construtor sem parâmetros (obrigatório para JPA funcionar)
@NoArgsConstructor
public class Produto {

    // Define o campo "id" como chave primária
    @Id

    // Geração automática do ID (auto incremento no banco)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nome do produto: campo obrigatório, com limite de 60 caracteres
    @Column(nullable = false, length = 60)
    private String nome;

    // Preço do produto: campo obrigatório
    @Column(nullable = false)
    private Double preco;

    // Categoria do produto: campo obrigatório, com limite de 60 caracteres
    @Column(nullable = false, length = 60)
    private String categoria;

    // Descrição do produto: campo opcional, com limite de 255 caracteres
    @Column(length = 255)
    private String descricao;
}
