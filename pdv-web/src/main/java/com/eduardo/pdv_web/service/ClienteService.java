package com.eduardo.pdv_web.service;

// Importa a entidade Cliente
import com.eduardo.pdv_web.domain.Cliente;
// Importa o DTO usado para receber dados de cliente
import com.eduardo.pdv_web.dto.ClienteRequestDTO;
// Importa o repositório para acesso ao banco
import com.eduardo.pdv_web.repository.ClienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
// Marca essa classe como um serviço do Spring, camada de lógica de negócio
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    // Injeta automaticamente o repositório para usar seus métodos
    @Autowired
    private ClienteRepository clienteRepository;

    // Método para inserir um novo cliente no banco, recebendo dados via DTO
    public Cliente insert(ClienteRequestDTO dto) {
        Cliente cliente = new Cliente();          // Cria um novo objeto Cliente
        cliente.setNome(dto.getNome());           // Seta o nome recebido no DTO
        cliente.setCpf(dto.getCpf());             // Seta o CPF recebido no DTO
        cliente.setEmail(dto.getEmail());         // Seta o email recebido no DTO
        cliente.setTelefone(dto.getTelefone());   // Seta o telefone recebido no DTO
        return clienteRepository.save(cliente);   // Salva no banco e retorna o cliente salvo (com ID gerado)
    }

    // Método para buscar todos os clientes cadastrados no banco
    public List<Cliente> findAll() {
        return clienteRepository.findAll();       // Retorna lista com todos os clientes do banco
    }

    // Método para buscar um cliente pelo CPF
    public Cliente buscarPorCpf(String cpf) {
        // Busca no banco pelo CPF, retorna o cliente ou lança exceção se não achar
        return clienteRepository.findByCpf(cpf)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    }

    // Método para deletar um cliente pelo ID
    public void delete(Long id) {
        clienteRepository.deleteById(id);          // Deleta cliente no banco pelo ID
    }

    // Método para atualizar um cliente, recebe ID e dados novos via DTO
    public Cliente update(Long id, ClienteRequestDTO dto) {
        Cliente cliente = new Cliente();           // Cria um novo objeto Cliente (pode ser otimizado para buscar antes)
        cliente.setId(id);                         // Define o ID do cliente a ser atualizado
        cliente.setNome(dto.getNome());            // Atualiza o nome
        cliente.setCpf(dto.getCpf());              // Atualiza o CPF
        cliente.setEmail(dto.getEmail());          // Atualiza o email
        cliente.setTelefone(dto.getTelefone());    // Atualiza o telefone
        return clienteRepository.save(cliente);    // Salva as alterações no banco (faz update pelo ID)
    }
}
