package com.github.fgsantana.transportapi.controller;

import com.github.fgsantana.transportapi.dto.TransportDTO;
import com.github.fgsantana.transportapi.service.TransportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/transports")
public class TransportResource {

    @Autowired
    TransportService service;

    @GetMapping
    public List<TransportDTO> getTransports() {
        return service.getTransports();

    }

    @GetMapping("/{id}")
    public TransportDTO getTransportById(@PathVariable("id") Long id) {
        return service.getTransportById(id);
    }


    @PostMapping
    public TransportDTO saveTransport(@Valid @RequestBody TransportDTO transportDTO) {
        return service.saveTransport(transportDTO);
    }


}
