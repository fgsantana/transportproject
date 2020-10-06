package com.github.fgsantana.transportapi.service;

import com.github.fgsantana.transportapi.client.RestTemplateClient;
import com.github.fgsantana.transportapi.dto.EnderecoDTO;
import com.github.fgsantana.transportapi.dto.TransportDTO;
import com.github.fgsantana.transportapi.entity.Transport;
import com.github.fgsantana.transportapi.exception.CepNotFoundException;
import com.github.fgsantana.transportapi.exception.InvalidCepFormatException;
import com.github.fgsantana.transportapi.exception.TransportNotFoundException;
import com.github.fgsantana.transportapi.message.ResponseMessage;
import com.github.fgsantana.transportapi.repository.TransportRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransportService {


    @Autowired
    private TransportRepository repo;

    @Autowired
    private RestTemplateClient client;

    ModelMapper mapper = new ModelMapper();


    public List<TransportDTO> getTransports() {
        List<Transport> list = repo.findAll();
        return list.stream().map(this::mapDTOSetLogoUrl).collect(Collectors.toList());
    }


    public TransportDTO getTransportById(Long id) {

        Transport transport = repo.findById(id).orElseThrow(() -> new TransportNotFoundException(id));
        return this.mapDTOSetLogoUrl(transport);
    }

    public TransportDTO saveTransport(TransportDTO transportDTO) {

        Transport transport = mapper.map(transportDTO, Transport.class);
        return this.mapDTOSetLogoUrl(repo.save(transport));
    }


    public TransportDTO updateTransportById(Long id, TransportDTO transportDTO) {
        if (!repo.existsById(id)) {
            throw new TransportNotFoundException(id);
        }
        transportDTO.setId(id);
        Transport transport = repo.findById(id).orElseThrow(() -> new TransportNotFoundException(id));
        mapper.map(transportDTO, transport);
        return this.mapDTOSetLogoUrl(repo.save(transport));
    }

    public ResponseMessage deleteTransportById(Long id) {
        if (!repo.existsById(id)) {
            throw new TransportNotFoundException(id);
        }
        repo.deleteById(id);
        return new ResponseMessage("Transportadora com id " + id + " excluída");
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

    public EnderecoDTO getAdressByCep(Long cep) throws InvalidCepFormatException, CepNotFoundException {
        if (cep.toString().length() != 8) {
            throw new InvalidCepFormatException();
        }

        return client.getAdress(cep);
    }


    public TransportDTO mapDTOSetLogoUrl(Transport transport) {
        TransportDTO dto = mapper.map(transport, TransportDTO.class);

        dto.setLogoUrl("http://localhost:8080/api/v1/transports/" + dto.getId() + "/logo");
        return dto;
    }

}
