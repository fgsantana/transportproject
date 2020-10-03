package com.github.fgsantana.transportapi.controller;

import com.github.fgsantana.transportapi.entity.Transport;
import com.github.fgsantana.transportapi.repository.TransportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/transports")
public class TransportController {
    private TransportRepository repo;

    @Autowired
    public TransportController(TransportRepository transportRepository){
        this.repo = transportRepository;
    }

    @GetMapping
    public List<Transport> getTransports(){
        return repo.findAll();

    }

    @PostMapping
    public Transport saveTransport(@RequestBody Transport transport){
        return repo.save(transport);
    }








}
