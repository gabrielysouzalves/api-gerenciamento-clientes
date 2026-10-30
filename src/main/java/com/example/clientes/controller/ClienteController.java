
package com.example.clientes.controller;

import com.example.clientes.model.Cliente;
import com.example.clientes.service.ClienteService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    // Injeção de dependência pelo construtor
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    // GET: listar todos os clientes
    @GetMapping
    public ResponseEntity<List<Cliente>> listar() {
        return ResponseEntity.ok(clienteService.listar());
    }

    // POST: cadastrar um cliente
    @PostMapping
    public ResponseEntity<Cliente> criar(
            @RequestBody Cliente cliente) {

        Cliente criado = clienteService.criar(cliente);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(criado);
    }

    // DELETE: remover um cliente pelo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {

        boolean removido = clienteService.remover(id);

        if (!removido) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }

    // PUT: atualizar um cliente pelo ID
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizar(
            @PathVariable Long id,
            @RequestBody Cliente novo) {

        Cliente atualizado =
                clienteService.atualizar(id, novo);

        if (atualizado == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(atualizado);
    }
}

