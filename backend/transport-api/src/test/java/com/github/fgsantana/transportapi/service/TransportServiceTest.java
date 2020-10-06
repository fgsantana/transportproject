package com.github.fgsantana.transportapi.service;

import com.github.fgsantana.transportapi.dto.TransportDTO;
import com.github.fgsantana.transportapi.entity.Transport;
import com.github.fgsantana.transportapi.message.ResponseMessage;
import com.github.fgsantana.transportapi.repository.TransportRepository;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static com.github.fgsantana.transportapi.util.TransportUtil.createFakeDTO;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TransportServiceTest {


    @Autowired
    TransportService service;

    @Autowired
    TransportRepository repo;

    ModelMapper mapper = new ModelMapper();

    @Test
    public void testIfIsSaved() {
        TransportDTO dtoToSave = createFakeDTO();
        TransportDTO savedDTO = service.saveTransport(dtoToSave);
        dtoToSave.setId(savedDTO.getId());
        dtoToSave.setLogoUrl(savedDTO.getLogoUrl());
        assertTrue(repo.existsById(savedDTO.getId()));
        Transport transport = repo.findById(savedDTO.getId()).get();
        assert (mapper.map(transport, TransportDTO.class).equals(dtoToSave));

    }

    @Test
    public void testIfIsOnTheDtoList() {
        TransportDTO dtoToSave = createFakeDTO();
        service.saveTransport(dtoToSave);
        List<TransportDTO> list = service.getTransports();
        assert (list.contains(dtoToSave));
    }

    @Test
    public void testIfIsDeleted() {
        TransportDTO transportDTO = createFakeDTO();
        Transport savedTransport = repo.save(mapper.map(transportDTO, Transport.class));
        ResponseMessage msg = service.deleteTransportById(savedTransport.getId());
        assertEquals("Transportadora com id " + savedTransport.getId() + " excluída", msg.getMessage());
        assertFalse(repo.existsById(savedTransport.getId()));
        assertFalse(repo.findAll().contains(savedTransport));

    }




    private TransportDTO toDTO(Transport transport) {
        return mapper.map(transport, TransportDTO.class);
    }
}
