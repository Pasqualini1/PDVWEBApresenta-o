package com.eduardo.pdv_web.service;

import com.eduardo.pdv_web.domain.*;
import com.eduardo.pdv_web.dto.*;
import com.eduardo.pdv_web.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service  // Marca a classe como componente de serviço, gerencia regras de negócio de Venda
public class VendaService {

    @Autowired  // Injeta o repositório para acessar dados de vendas
    private VendaRepository vendaRepository;

    @Autowired  // Injeta o repositório para acessar dados de clientes
    private ClienteRepository clienteRepository;

    @Autowired  // Injeta o repositório para acessar dados de produtos
    private ProdutoRepository produtoRepository;

    // Insere uma nova venda no banco, recebendo um DTO com dados do cliente, itens e observações
    public Venda inserir(VendaRequestDTO dto) {
        // Busca o cliente pelo ID passado no DTO, se não achar, lança exceção
        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        Venda venda = new Venda();               // Cria uma nova venda
        venda.setCliente(cliente);               // Associa o cliente encontrado
        venda.setDataHora(LocalDateTime.now()); // Seta data/hora atual na venda
        venda.setObservacoes(dto.getObservacoes());  // Seta observações, se houver

        List<ItemVenda> itens = new ArrayList<>();  // Lista para armazenar os itens da venda
        double total = 0;                            // Variável para acumular o total da venda

        // Percorre os itens da venda enviados no DTO
        for (ItemVendaDTO itemDTO : dto.getItens()) {
            // Busca o produto pelo ID informado no item, lança erro se não encontrado
            Produto produto = produtoRepository.findById(itemDTO.getProdutoId())
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

            ItemVenda item = new ItemVenda();            // Cria um novo item de venda
            item.setProduto(produto);                     // Seta o produto do item
            item.setQuantidade(itemDTO.getQuantidade()); // Seta a quantidade comprada

            double subtotal = produto.getPreco() * itemDTO.getQuantidade(); // Calcula subtotal do item
            item.setSubtotal(subtotal);                   // Seta o subtotal no item
            item.setVenda(venda);                         // Associa o item à venda atual

            itens.add(item);          // Adiciona o item na lista de itens
            total += subtotal;        // Soma o subtotal ao total da venda
        }

        venda.setItens(itens);        // Seta a lista de itens na venda
        venda.setTotal(total);        // Seta o total calculado na venda

        return vendaRepository.save(venda);  // Salva a venda (com itens) no banco e retorna o objeto salvo
    }

    // Retorna a lista completa de vendas cadastradas
    public List<Venda> listar() {
        return vendaRepository.findAll();
    }

    // Busca uma venda pelo ID, retorna Optional para tratar ausência
    public Optional<Venda> buscarPorId(Long id) {
        return vendaRepository.findById(id);
    }

    // Remove uma venda pelo ID, lança exceção se não existir
    public void delete(Long id) {
        if (!vendaRepository.existsById(id)) {
            throw new RuntimeException("Venda não encontrada para exclusão");
        }
        vendaRepository.deleteById(id);
    }
}
