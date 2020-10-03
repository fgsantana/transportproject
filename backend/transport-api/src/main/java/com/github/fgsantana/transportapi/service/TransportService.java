package com.github.fgsantana.transportapi.service;

import com.github.fgsantana.transportapi.entity.Transport;
import com.github.fgsantana.transportapi.repository.TransportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransportService {
    private TransportRepository repo;

    @Autowired
    public TransportService(TransportRepository transportRepository){
        this.repo = transportRepository;
    }

    public Transport saveTransport(Transport transport) {
        return repo.save(transport);
    }

    public List<Transport> getTransports() {
        return repo.findAll();
    }
}
