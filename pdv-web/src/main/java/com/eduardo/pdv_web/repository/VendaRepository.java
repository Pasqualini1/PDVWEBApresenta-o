// Define o pacote onde a interface está localizada
package com.eduardo.pdv_web.repository;

// Importa a entidade Venda para que essa interface gerencie seus objetos no banco
import com.eduardo.pdv_web.domain.Venda;

// Importa JpaRepository que fornece métodos padrão para operações CRUD
import org.springframework.data.jpa.repository.JpaRepository;

// Marca essa interface como um componente de repositório para o Spring gerenciar
import org.springframework.stereotype.Repository;

@Repository
// Interface que estende JpaRepository para gerenciar a entidade Venda com ID do tipo Long
public interface VendaRepository extends JpaRepository<Venda, Long> {
    // Aqui não há métodos adicionais porque JpaRepository já oferece:
    // - save (salvar/atualizar)
    // - findById (buscar por ID)
    // - findAll (buscar todos)
    // - delete (excluir)
    // entre outros métodos padrão de persistência
}
