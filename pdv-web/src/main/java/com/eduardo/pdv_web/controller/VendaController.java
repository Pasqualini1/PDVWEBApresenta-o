package com.eduardo.pdv_web.controller;

import com.eduardo.pdv_web.domain.Venda;
import com.eduardo.pdv_web.dto.VendaRequestDTO;
import com.eduardo.pdv_web.service.VendaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController  // Define que essa classe é um controller REST, que manipula requisições HTTP com JSON
@RequestMapping("/vendas")  // Caminho base para todas as rotas da classe: /vendas
public class VendaController {

    @Autowired  // Injeta o serviço VendaService para delegar a lógica de negócio
    private VendaService vendaService;

    // Método para criar uma nova venda via POST /vendas
    @PostMapping
    public ResponseEntity<Venda> criarVenda(@RequestBody @Valid VendaRequestDTO dto, // Recebe dados da venda no corpo e valida
                                            UriComponentsBuilder builder) {  // Auxilia na criação da URI do recurso criado
        Venda venda = vendaService.inserir(dto); // Insere a venda no banco através do serviço

        // Cria a URI do recurso recém-criado, ex: /vendas/123
        URI uri = builder.path("/vendas/{id}").buildAndExpand(venda.getId()).toUri();

        // Retorna status 201 Created, com o objeto criado no corpo e a URI no header Location
        return ResponseEntity.created(uri).body(venda);
    }

    // Método para listar todas as vendas via GET /vendas
    @GetMapping
    public ResponseEntity<List<Venda>> listar() {
        // Busca todas as vendas via serviço e retorna 200 OK com a lista
        return ResponseEntity.ok(vendaService.listar());
    }

    // Método para buscar uma venda pelo ID via GET /vendas/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Venda> buscarPorId(@PathVariable Long id) { // Pega o ID da URL
        // Busca venda pelo serviço, retorna 200 OK se encontrada ou 404 Not Found se não
        return vendaService.buscarPorId(id)
                .map(ResponseEntity::ok) // Se existir, retorna OK com a venda
                .orElse(ResponseEntity.notFound().build()); // Se não, retorna 404
    }

    // Método para deletar uma venda via DELETE /vendas/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) { // Pega o ID da URL
        vendaService.delete(id); // Deleta a venda pelo serviço, lança erro se não encontrar
        return ResponseEntity.noContent().build(); // Retorna 204 No Content, indicando sucesso sem corpo
    }
}
