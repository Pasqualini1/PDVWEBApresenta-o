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

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<Produto> insert(@RequestBody @Valid ProdutoRequestDTO dto,
                                          UriComponentsBuilder builder) {
        Produto produto = produtoService.insert(dto);
        URI uri = builder.path("/produtos/{id}").buildAndExpand(produto.getId()).toUri();
        return ResponseEntity.created(uri).body(produto);
    }

    @GetMapping
    public ResponseEntity<List<Produto>> findAll(@RequestParam(value = "nome",
            required = false) String nome) {
        return ResponseEntity.ok(produtoService.findAll(nome));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> findById(@PathVariable Long id) {
        Produto produto = produtoService.findById(id);
        return ResponseEntity.ok(produto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> update(@PathVariable Long id,
                                          @RequestBody @Valid ProdutoRequestDTO dto) {
        Produto produto = produtoService.update(id, dto);
        return ResponseEntity.ok(produto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        produtoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
