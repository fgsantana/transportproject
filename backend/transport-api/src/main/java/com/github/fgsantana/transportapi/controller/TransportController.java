package com.github.fgsantana.transportapi.controller;

import com.github.fgsantana.transportapi.entity.Transport;
import com.github.fgsantana.transportapi.service.TransportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/transports")
public class TransportController {
    private TransportService service;

    @Autowired
    public TransportController(TransportService transportService){
        this.service = transportService;
    }

    @GetMapping
    public List<Transport> getTransports(){
        return service.getTransports();

    }

    @PostMapping
    public Transport saveTransport(@RequestBody Transport transport){
        return service.saveTransport(transport);
    }








}
