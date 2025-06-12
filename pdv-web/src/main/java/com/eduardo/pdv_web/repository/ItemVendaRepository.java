// Define o pacote onde esta interface está localizada
package com.eduardo.pdv_web.repository;

// Importa a entidade ItemVenda para usar como tipo da entidade gerenciada
import com.eduardo.pdv_web.domain.ItemVenda;

// Importa JpaRepository do Spring Data para fornecer operações padrão de banco de dados
import org.springframework.data.jpa.repository.JpaRepository;

// Marca essa interface como um componente repositório do Spring para gerenciamento automático
import org.springframework.stereotype.Repository;

@Repository
// Interface que estende JpaRepository para fornecer operações CRUD para a entidade ItemVenda
// JpaRepository<ItemVenda, Long> significa que esta interface gerencia objetos ItemVenda cujo ID é do tipo Long
public interface ItemVendaRepository extends JpaRepository<ItemVenda, Long> {
    // Não há métodos adicionais definidos aqui, pois JpaRepository já fornece:
    // - save, findById, findAll, delete, etc.
}
