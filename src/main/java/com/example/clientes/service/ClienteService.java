
package com.example.clientes.service;

import com.example.clientes.model.Cliente;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteService {

    private final List<Cliente> clientes = new ArrayList<>();

    private Long nextId = 1L;

    // Listar todos os clientes
    public List<Cliente> listar() {
        return clientes;
    }

    // Cadastrar um cliente
    public Cliente criar(Cliente c) {
        c.setId(nextId++);
        clientes.add(c);
        return c;
    }

    // Buscar cliente pelo ID
    public Cliente buscarPorId(Long id) {
        for (Cliente cliente : clientes) {
            if (cliente.getId().equals(id)) {
                return cliente;
            }
        }

        return null;
    }

    // Remover cliente pelo ID
    public boolean remover(Long id) {
        return clientes.removeIf(
                cliente -> cliente.getId().equals(id)
        );
    }

    // Atualizar os dados de um cliente
    public Cliente atualizar(Long id, Cliente novo) {
        Cliente existente = buscarPorId(id);

        if (existente == null) {
            return null;
        }

        existente.setNome(novo.getNome());
        existente.setEmail(novo.getEmail());
        existente.setIdade(novo.getIdade());

        return existente;
    }
}
