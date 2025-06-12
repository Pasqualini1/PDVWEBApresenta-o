package com.eduardo.pdv_web.service;

import com.eduardo.pdv_web.domain.*;
import com.eduardo.pdv_web.dto.*;
import com.eduardo.pdv_web.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public Venda inserir(VendaRequestDTO dto) {
        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        Venda venda = new Venda();
        venda.setCliente(cliente);
        venda.setDataHora(LocalDateTime.now());
        venda.setObservacoes(dto.getObservacoes());

        List<ItemVenda> itens = new ArrayList<>();
        double total = 0;

        for (ItemVendaDTO itemDTO : dto.getItens()) {
            Produto produto = produtoRepository.findById(itemDTO.getProdutoId())
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

            ItemVenda item = new ItemVenda();
            item.setProduto(produto);
            item.setQuantidade(itemDTO.getQuantidade());

            double subtotal = produto.getPreco() * itemDTO.getQuantidade();
            item.setSubtotal(subtotal);
            item.setVenda(venda);

            itens.add(item);
            total += subtotal;
        }

        venda.setItens(itens);
        venda.setTotal(total);

        return vendaRepository.save(venda);
    }

    public List<Venda> listar() {
        return vendaRepository.findAll();
    }

    public Optional<Venda> buscarPorId(Long id) {
        return vendaRepository.findById(id);
    }

    public void delete(Long id) {
        if (!vendaRepository.existsById(id)) {
            throw new RuntimeException("Venda não encontrada para exclusão");
        }
        vendaRepository.deleteById(id);
    }
}
