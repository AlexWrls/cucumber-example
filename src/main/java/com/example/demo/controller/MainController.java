package com.example.demo.controller;

import com.example.demo.entity.Client;
import com.example.demo.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MainController {
    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("client")
    public ResponseEntity<List<Client>> findAll(){
        return ResponseEntity.ok(clientRepository.findAll());
    }
}
