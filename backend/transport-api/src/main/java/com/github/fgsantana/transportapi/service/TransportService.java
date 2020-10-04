package com.github.fgsantana.transportapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fgsantana.transportapi.dto.TransportDTO;
import com.github.fgsantana.transportapi.entity.Transport;
import com.github.fgsantana.transportapi.repository.TransportRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransportService {

    ModelMapper mapper = new ModelMapper();

    @Autowired
    TransportRepository repo;

    public List<TransportDTO> getTransports() {
        List<Transport> list= repo.findAll();
        return list.stream().map(t-> mapper.map(t,TransportDTO.class)).collect(Collectors.toList());
    }


    public TransportDTO getTransportById(Long id) {

        Transport transport = repo.findById(id).orElseThrow();
        TransportDTO transportDTO = mapper.map(transport,TransportDTO.class);
        return transportDTO;
    }

    public TransportDTO saveTransport(TransportDTO transportDTO) {
        Transport transport = mapper.map(transportDTO, Transport.class);
        return mapper.map(repo.save(transport),TransportDTO.class);
    }

}
