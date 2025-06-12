// Pacote onde a classe está localizada
package com.eduardo.pdv_web.domain;

// Importações necessárias para JPA e Lombok
import jakarta.persistence.*;
import lombok.*;

// Indica que esta classe será mapeada para uma tabela no banco de dados
@Entity

// Define o nome da tabela como "item_venda"
@Table(name = "item_venda")

// Lombok: gera getters, setters, toString, equals e hashCode automaticamente
@Data

// Lombok: gera construtor com todos os atributos
@AllArgsConstructor

// Lombok: gera construtor sem argumentos (obrigatório para JPA)
@NoArgsConstructor
public class ItemVenda {

    // Define o campo "id" como chave primária
    @Id

    // Geração automática do ID (auto incremento no banco)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relação muitos-para-um com a entidade Venda
    // Muitos itens de venda podem estar associados a uma única venda
    @ManyToOne

    // Define a coluna "venda_id" como chave estrangeira referenciando a tabela de vendas
    @JoinColumn(name = "venda_id")
    private Venda venda;

    // Relação muitos-para-um com a entidade Produto
    // Muitos itens de venda podem conter o mesmo produto
    @ManyToOne

    // Define a coluna "produto_id" como chave estrangeira referenciando a tabela de produtos
    @JoinColumn(name = "produto_id")
    private Produto produto;

    // Quantidade de produtos vendidos nesse item
    private Integer quantidade;

    // Valor total desse item (produto * quantidade)
    private Double subtotal;
}
