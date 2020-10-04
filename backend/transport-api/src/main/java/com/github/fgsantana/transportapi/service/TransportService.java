package com.github.fgsantana.transportapi.service;

import com.github.fgsantana.transportapi.dto.TransportDTO;
import com.github.fgsantana.transportapi.entity.Transport;
import com.github.fgsantana.transportapi.message.ResponseMessage;
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
        List<Transport> list = repo.findAll();
        return list.stream().map(t -> mapper.map(t, TransportDTO.class)).collect(Collectors.toList());
    }


    public TransportDTO getTransportById(Long id) {

        Transport savedTransport = repo.findById(id).orElseThrow();
        return mapper.map(savedTransport, TransportDTO.class);
    }

    public TransportDTO saveTransport(TransportDTO transportDTO) {
        Transport transport = mapper.map(transportDTO, Transport.class);
        return mapper.map(repo.save(transport), TransportDTO.class);
    }


    public TransportDTO updateTransportById(Long id, TransportDTO transportDTO) {

        transportDTO.setId(id);
        Transport transport = repo.findById(id).orElseThrow();
        mapper.map(transportDTO, transport);

        return mapper.map(repo.save(transport), TransportDTO.class);
    }

    public ResponseMessage deleteTransportById(Long id) {
        repo.deleteById(id);
        return new ResponseMessage("Transportador com id " + id + " excluída");
    }

    public byte[] getLogoByTransportId(Long id) {
        Transport transport = repo.findById(id).orElseThrow();
        return transport.getLogo();
    }

    public byte[] insertLogoOnTransportById(Long id, byte[] logo) {
        Transport transport = repo.findById(id).orElseThrow();
        transport.setLogo(logo);
        return repo.save(transport).getLogo();
    }
}
