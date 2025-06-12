package com.eduardo.pdv_web.controller;

import com.eduardo.pdv_web.domain.Cliente;
import com.eduardo.pdv_web.dto.ClienteRequestDTO;
import com.eduardo.pdv_web.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController  // Define que essa classe é um controller REST, que responde requisições HTTP e retorna dados JSON
@RequestMapping("/clientes") // Define o caminho base das rotas desse controller, que será /clientes
public class ClienteController {

    @Autowired  // Injeta o serviço ClienteService para usar a lógica de negócio de cliente
    private ClienteService clienteService;

    // Método para criar um novo cliente via POST /clientes
    @PostMapping
    public ResponseEntity<Cliente> insert(@RequestBody @Valid
                                              ClienteRequestDTO dto,  // Recebe o cliente em formato JSON, validado (@Valid)
                                          UriComponentsBuilder builder) { // Ajuda a construir a URI do recurso criado
        Cliente cliente = clienteService.insert(dto); // Chama o serviço para salvar o cliente

        // Cria a URI do recurso criado (ex: /clientes/123)
        URI location = builder.path("/clientes/{id}").buildAndExpand(cliente.getId()).toUri();

        // Retorna status 201 Created, com o cliente criado no corpo e a URI no header Location
        return ResponseEntity.created(location).body(cliente);
    }

    // Método para listar todos os clientes via GET /clientes
    @GetMapping
    public ResponseEntity<List<Cliente>> findAll() {
        // Retorna status 200 OK com a lista de clientes no corpo
        return ResponseEntity.ok(clienteService.findAll());
    }

    // Método para buscar cliente pelo CPF via GET /clientes/cpf/{cpf}
    @GetMapping("/cpf/{cpf}")
    public Cliente buscarPorCpf(@PathVariable String cpf) {
        // Busca o cliente pelo CPF usando o serviço e retorna direto
        return clienteService.buscarPorCpf(cpf);
    }

    // Método para atualizar um cliente pelo ID via PUT /clientes/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> update(@PathVariable Long id,
                                          @RequestBody @Valid
                                          ClienteRequestDTO dto) {
        // Atualiza o cliente com os dados recebidos e retorna o cliente atualizado
        Cliente cliente = clienteService.update(id, dto);
        return ResponseEntity.ok(cliente);
    }

    // Método para deletar um cliente pelo ID via DELETE /clientes/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        // Chama o serviço para deletar o cliente
        clienteService.delete(id);
        // Retorna status 204 No Content, sem corpo
        return ResponseEntity.noContent().build();
    }
}
