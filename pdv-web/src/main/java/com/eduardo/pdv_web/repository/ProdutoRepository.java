// Pacote onde a interface está localizada
package com.eduardo.pdv_web.repository;

// Importa a entidade Produto para indicar que essa interface vai gerenciar essa entidade
import com.eduardo.pdv_web.domain.Produto;

// Importa JpaRepository que fornece métodos básicos de acesso a dados (CRUD)
import org.springframework.data.jpa.repository.JpaRepository;

// Marca essa interface como componente de repositório gerenciado pelo Spring
import org.springframework.stereotype.Repository;

// Importa List para uso no método personalizado
import java.util.List;

@Repository
// Interface que estende JpaRepository para gerenciar a entidade Produto com chave do tipo Long
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    // Método personalizado que busca uma lista de produtos cujo nome contém o texto passado
    // 'Containing' faz busca por substring e 'IgnoreCase' ignora maiúsculas/minúsculas
    List<Produto> findByNomeContainingIgnoreCase(String nome);
}
