// Define o pacote onde esta interface está localizada
package com.eduardo.pdv_web.repository;

// Importa a classe Cliente do pacote domain para usar como tipo da entidade
import com.eduardo.pdv_web.domain.Cliente;

// Importa o JpaRepository do Spring Data JPA, que oferece operações CRUD prontas
import org.springframework.data.jpa.repository.JpaRepository;

// Importa anotação para marcar esta interface como componente de repositório do Spring
import org.springframework.stereotype.Repository;

// Importa Optional para representar um possível resultado vazio ao buscar um Cliente pelo CPF
import java.util.Optional;

// Indica que esta interface é um repositório do Spring (para injeção e gerenciamento)
@Repository

// Declara uma interface que estende JpaRepository para fornecer métodos de acesso a dados
// JpaRepository<Cliente, Long> indica que ela gerencia a entidade Cliente e que a chave primária é do tipo Long
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    // Método personalizado que busca um Cliente pelo CPF
    // Retorna um Optional, que pode conter o Cliente ou estar vazio se não encontrado
    Optional<Cliente> findByCpf(String cpf);
}
