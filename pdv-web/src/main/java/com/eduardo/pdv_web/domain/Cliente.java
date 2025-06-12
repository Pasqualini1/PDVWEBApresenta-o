// Pacote onde a classe está localizada
package com.eduardo.pdv_web.domain;

// Importações necessárias para as anotações JPA e Lombok
import jakarta.persistence.*;
import lombok.*;

// Essa anotação indica que essa classe é uma entidade JPA, ou seja, será mapeada para uma tabela no banco de dados
@Entity

// Define o nome da tabela no banco como "cliente"
@Table(name = "cliente")

// Lombok: gera automaticamente os métodos getters, setters, toString, equals e hashCode
@Data

// Lombok: gera um construtor com todos os atributos como parâmetros
@AllArgsConstructor

// Lombok: gera um construtor vazio (sem parâmetros)
@NoArgsConstructor
public class Cliente {

    // Define o campo "id" como chave primária da tabela
    @Id

    // Indica que o valor do ID será gerado automaticamente pelo banco (auto increment)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Campo obrigatório (não pode ser nulo) no banco de dados
    @Column(nullable = false)
    private String nome;

    // Campo obrigatório e único (não pode se repetir) no banco de dados
    @Column(nullable = false, unique = true)
    private String cpf;

    // Campo opcional, pode ser nulo
    private String email;

    // Campo opcional, pode ser nulo
    private String telefone;
}
