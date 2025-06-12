package com.eduardo.pdv_web.service;

import com.eduardo.pdv_web.domain.Produto;
import com.eduardo.pdv_web.dto.ProdutoRequestDTO;
import com.eduardo.pdv_web.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service  // Marca essa classe como serviço gerenciado pelo Spring, camada de lógica de negócio para Produto
public class ProdutoService {

    @Autowired  // Injeta automaticamente o repositório de Produto para acesso ao banco
    private ProdutoRepository produtoRepository;

    // Insere um novo produto no banco, recebendo os dados via DTO
    public Produto insert(ProdutoRequestDTO dto) {
        Produto produto = new Produto();          // Cria uma nova instância de Produto
        produto.setNome(dto.getNome());           // Seta o nome recebido no DTO
        produto.setPreco(dto.getPreco());         // Seta o preço recebido no DTO
        produto.setCategoria(dto.getCategoria()); // Seta a categoria recebida no DTO
        produto.setDescricao(dto.getDescricao()); // Seta a descrição recebida no DTO
        return produtoRepository.save(produto);   // Salva no banco e retorna o produto salvo com ID gerado
    }

    // Busca todos os produtos, opcionalmente filtrando pelo nome (case-insensitive)
    public List<Produto> findAll(String nome) {
        if (nome != null && !nome.isEmpty()) {
            // Se o nome foi informado, busca produtos que contenham esse nome ignorando maiúsculas/minúsculas
            return produtoRepository.findByNomeContainingIgnoreCase(nome);
        }
        // Se não, retorna todos os produtos
        return produtoRepository.findAll();
    }

    // Busca um produto pelo ID, lança exceção se não encontrar
    public Produto findById(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com o ID: " + id));
    }

    // Atualiza um produto, recebe ID e dados novos via DTO
    public Produto update(Long id, ProdutoRequestDTO dto) {
        Produto produto = new Produto();          // Cria uma nova instância de Produto
        produto.setId(id);                        // Define o ID do produto que será atualizado
        produto.setNome(dto.getNome());           // Atualiza o nome
        produto.setPreco(dto.getPreco());         // Atualiza o preço
        produto.setCategoria(dto.getCategoria()); // Atualiza a categoria
        produto.setDescricao(dto.getDescricao()); // Atualiza a descrição
        return produtoRepository.save(produto);   // Salva as alterações no banco (update pelo ID)
    }

    // Deleta um produto pelo ID
    public void delete(Long id) {
        produtoRepository.deleteById(id);         // Remove o produto do banco pelo ID
    }
}
