package com.eduardo.pdv_web.controller;

import com.eduardo.pdv_web.domain.Produto;
import com.eduardo.pdv_web.dto.ProdutoRequestDTO;
import com.eduardo.pdv_web.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController  // Indica que essa classe é um controller REST, que responde requisições HTTP com JSON
@RequestMapping("/produtos")  // Define o caminho base para as rotas dessa classe: /produtos
public class ProdutoController {

    @Autowired  // Injeta o serviço ProdutoService para usar a lógica de negócio de produto
    private ProdutoService produtoService;

    // Método para criar um novo produto via POST /produtos
    @PostMapping
    public ResponseEntity<Produto> insert(@RequestBody @Valid ProdutoRequestDTO dto,  // Recebe produto no corpo da requisição e valida
                                          UriComponentsBuilder builder) {  // Ajuda a construir URI do recurso criado
        Produto produto = produtoService.insert(dto); // Salva o produto usando o serviço

        // Cria a URI do produto criado, ex: /produtos/123
        URI uri = builder.path("/produtos/{id}").buildAndExpand(produto.getId()).toUri();

        // Retorna status 201 Created com o produto criado no corpo e URI no header Location
        return ResponseEntity.created(uri).body(produto);
    }

    // Método para listar todos os produtos ou filtrar pelo nome via GET /produtos?nome=xyz
    @GetMapping
    public ResponseEntity<List<Produto>> findAll(@RequestParam(value = "nome",
            required = false) String nome) {  // Parâmetro opcional para buscar por nome
        // Retorna 200 OK com a lista de produtos, filtrada ou completa
        return ResponseEntity.ok(produtoService.findAll(nome));
    }

    // Método para buscar um produto pelo ID via GET /produtos/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Produto> findById(@PathVariable Long id) {  // Pega o ID da URL
        Produto produto = produtoService.findById(id);  // Busca produto pelo serviço, lança erro se não achar
        return ResponseEntity.ok(produto);  // Retorna 200 OK com o produto encontrado
    }

    // Método para atualizar um produto via PUT /produtos/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Produto> update(@PathVariable Long id,
                                          @RequestBody @Valid ProdutoRequestDTO dto) {  // Recebe dados atualizados e valida
        Produto produto = produtoService.update(id, dto);  // Atualiza o produto e retorna o atualizado
        return ResponseEntity.ok(produto);  // Retorna 200 OK com o produto atualizado
    }

    // Método para deletar um produto via DELETE /produtos/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {  // Pega ID da URL
        produtoService.delete(id);  // Deleta o produto pelo serviço
        return ResponseEntity.noContent().build();  // Retorna 204 No Content, sem corpo
    }
}
