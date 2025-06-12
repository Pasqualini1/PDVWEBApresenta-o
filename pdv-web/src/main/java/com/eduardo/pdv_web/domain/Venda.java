// Pacote onde a classe está localizada
package com.eduardo.pdv_web.domain;

// Importações necessárias do JPA, Lombok e classes Java
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

// Define que essa classe é uma entidade do JPA (será mapeada para uma tabela no banco)
@Entity

// Define o nome da tabela como "venda"
@Table(name = "venda")

// Lombok: Gera automaticamente getters, setters, toString, equals e hashCode
@Data

// Lombok: Gera um construtor com todos os atributos
@AllArgsConstructor

// Lombok: Gera um construtor sem argumentos (requisito do JPA)
@NoArgsConstructor
public class Venda {

    // Define o campo "id" como chave primária
    @Id

    // Geração automática do ID (auto incremento)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relacionamento muitos-para-um com Cliente
    // Uma venda está associada a um único cliente
    @ManyToOne

    // Define a coluna no banco que será usada como chave estrangeira para o cliente
    // Também define que esse campo é obrigatório (nullable = false)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    // Relacionamento um-para-muitos com ItemVenda
    // Uma venda pode ter vários itens
    @OneToMany(
        mappedBy = "venda", // Nome do atributo na classe ItemVenda que referencia Venda
        cascade = CascadeType.ALL, // Toda operação em Venda será propagada para os itens
        orphanRemoval = true // Se um item for removido da lista, será excluído do banco também
    )
    private List<ItemVenda> itens;

    // Campo para observações sobre a venda (até 255 caracteres)
    @Column(length = 255)
    private String observacoes;

    // Data e hora em que a venda foi realizada
    private LocalDateTime dataHora;

    // Valor total da venda (soma de todos os subtotais dos itens)
    private Double total;
}
