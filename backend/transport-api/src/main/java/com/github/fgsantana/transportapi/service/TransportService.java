package com.github.fgsantana.transportapi.service;

import com.github.fgsantana.transportapi.client.RestTemplateClient;
import com.github.fgsantana.transportapi.dto.EnderecoDTO;
import com.github.fgsantana.transportapi.dto.TransportDTO;
import com.github.fgsantana.transportapi.entity.Transport;
import com.github.fgsantana.transportapi.exception.TransportNotFoundException;
import com.github.fgsantana.transportapi.message.ResponseMessage;
import com.github.fgsantana.transportapi.repository.TransportRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransportService {

    ModelMapper mapper = new ModelMapper();

    final
    TransportRepository repo;

    final
    RestTemplateClient client;

    public TransportService(TransportRepository repo, RestTemplateClient client) {
        this.repo = repo;
        this.client = client;
    }

    public List<TransportDTO> getTransports() {
        List<Transport> list = repo.findAll();
        return list.stream().map(t -> mapper.map(t, TransportDTO.class)).collect(Collectors.toList());
    }


    public TransportDTO getTransportById(Long id) {

        Transport transport = repo.findById(id).orElseThrow(() -> new TransportNotFoundException(id));
        return mapper.map(transport, TransportDTO.class);
    }

    public TransportDTO saveTransport(TransportDTO transportDTO) {
        Transport transport = mapper.map(transportDTO, Transport.class);
        return mapper.map(repo.save(transport), TransportDTO.class);
    }


    public TransportDTO updateTransportById(Long id, TransportDTO transportDTO) {

        transportDTO.setId(id);
        Transport transport = repo.findById(id).orElseThrow(() -> new TransportNotFoundException(id));
        mapper.map(transportDTO, transport);

        return mapper.map(repo.save(transport), TransportDTO.class);
    }

    public ResponseMessage deleteTransportById(Long id) {
        if (!repo.existsById(id)) {
            throw new TransportNotFoundException(id);
        }
        repo.deleteById(id);
        return new ResponseMessage("Transportador com id " + id + " excluída");
    }

    public byte[] getLogoByTransportId(Long id) {
        Transport transport = repo.findById(id).orElseThrow(() -> new TransportNotFoundException(id));
        return transport.getLogo();
    }

    public byte[] insertLogoOnTransportById(Long id, byte[] logo) {
        Transport transport = repo.findById(id).orElseThrow(() -> new TransportNotFoundException(id));
        transport.setLogo(logo);
        return repo.save(transport).getLogo();
    }

    public EnderecoDTO getAdressByCep(Long cep) {


        return client.getAdress(cep);
    }
}
