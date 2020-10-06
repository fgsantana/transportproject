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
        setAsReturnedDTO(dtoToSave, savedDTO);
        assertTrue(repo.existsById(savedDTO.getId()));
        Transport transport = repo.findById(savedDTO.getId()).get();
        assert (toDTO(transport).equals(dtoToSave));

    }

    @Test
    public void testIfIsOnTheDtoList() {
        TransportDTO dtoToSave = createFakeDTO();
        TransportDTO savedDTO = service.saveTransport(dtoToSave);
        setAsReturnedDTO(dtoToSave, savedDTO);
        List<TransportDTO> list = service.getTransports();
        assert (list.contains(dtoToSave));
    }

    @Test
    public void testIfIsDeleted() {
        TransportDTO dtoToDelete = createFakeDTO();
        Transport savedTransport = repo.save(mapper.map(dtoToDelete, Transport.class));
        TransportDTO savedDTO = toDTO(savedTransport);
        setAsReturnedDTO(dtoToDelete, savedDTO);
        ResponseMessage msg = service.deleteTransportById(savedTransport.getId());
        assertEquals("Transportadora com id " + savedTransport.getId() + " excluída", msg.getMessage());
        assertFalse(repo.existsById(savedTransport.getId()));
        assertFalse(repo.findAll().contains(dtoToDelete));

    }

    @Test
    public void testIfUpdated() {
        TransportDTO initialDTO = createFakeDTO();
        Transport savedTransport = repo.save(mapper.map(initialDTO, Transport.class));
        TransportDTO updateDTO = toDTO(savedTransport);
        updateDTO.setEmail("updatedEmail@gmail.com");
        updateDTO.setNome("updatedName");
        service.updateTransportById(savedTransport.getId(), updateDTO);
        Transport transport = repo.findById(savedTransport.getId()).get();
        assert (toDTO(transport).equals(updateDTO));
    }


    private void setAsReturnedDTO(TransportDTO targetDTO, TransportDTO sourceDTO) {
        targetDTO.setId(sourceDTO.getId());
        targetDTO.setLogoUrl(sourceDTO.getLogoUrl());
    }

    private TransportDTO toDTO(Transport transport) {

        TransportDTO dto = mapper.map(transport, TransportDTO.class);

        dto.setLogoUrl("http://localhost:8080/api/v1/transports/" + dto.getId() + "/logo");
        return dto;
    }

}

