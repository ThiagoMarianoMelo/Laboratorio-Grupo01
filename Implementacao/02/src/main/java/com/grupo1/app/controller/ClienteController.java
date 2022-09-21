package com.grupo1.app.controller;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.grupo1.app.entidades.Cliente;
import com.grupo1.app.persistencia.clienteRepository;

public class ClienteController {
    private final clienteRepository clienteRepository;
    
    public ClienteController(clienteRepository clienteRepository){
        this.clienteRepository = clienteRepository;
    }
    
    @GetMapping("/{cpf}")
    public Cliente getClient(@PathVariable String cpf) {
        return clienteRepository.findById(cpf).orElseThrow(RuntimeException::new);
    }

    @PostMapping
    public ResponseEntity createClient(@RequestBody Cliente cliente) throws URISyntaxException {
        Cliente clienteSalvo = clienteRepository.save(cliente);
        return ResponseEntity.created(new URI("/clients/" + clienteSalvo.getCpf())).body(clienteSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateClient(@PathVariable String cpf, @RequestBody Cliente client) {
        Cliente currentClient = clienteRepository.findById(cpf).orElseThrow(RuntimeException::new);
        currentClient.setNome(client.getNome());
        currentClient = clienteRepository.save(client);

        return ResponseEntity.ok(currentClient);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteClient(@PathVariable String cpf) {
        clienteRepository.deleteById(cpf);
        return ResponseEntity.ok().build();
    }
}