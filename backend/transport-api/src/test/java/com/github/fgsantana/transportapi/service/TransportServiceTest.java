package com.github.fgsantana.transportapi.service;

import com.github.fgsantana.transportapi.dto.TransportDTO;
import com.github.fgsantana.transportapi.entity.Transport;
import com.github.fgsantana.transportapi.exception.InvalidCepFormatException;
import com.github.fgsantana.transportapi.exception.TransportNotFoundException;
import com.github.fgsantana.transportapi.message.ResponseMessage;
import com.github.fgsantana.transportapi.repository.TransportRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static com.github.fgsantana.transportapi.util.TransportUtil.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TransportServiceTest {


    @Autowired
    TransportService service;

    @Autowired
    TransportRepository repo;


    @Test
    public void testIfIsSaved() {
        TransportDTO dtoToSave = createTestDTO();
        TransportDTO savedDTO = service.saveTransport(dtoToSave);
        setAsReturnedDTO(dtoToSave, savedDTO.getId());
        assertTrue(repo.existsById(savedDTO.getId()));
        Transport transport = repo.findById(savedDTO.getId()).orElseThrow(() -> new TransportNotFoundException(savedDTO.getId()));
        assert (toDTO(transport).equals(dtoToSave));

    }

    @Test
    public void testIfIsOnTheDtoList() {
        TransportDTO dtoToSave = createTestDTO();
        TransportDTO savedDTO = service.saveTransport(dtoToSave);
        setAsReturnedDTO(dtoToSave, savedDTO.getId());
        List<TransportDTO> list = service.getTransports();
        assert (list.contains(dtoToSave));
    }

    @Test
    public void testIfIsReturnedProperly() {
        TransportDTO dtoToSave = createTestDTO();
        Long savedId = repo.save(mapper.map(dtoToSave, Transport.class)).getId();
        setAsReturnedDTO(dtoToSave, savedId);
        TransportDTO returnedDTO = service.getTransportById(savedId);
        assert (returnedDTO.equals(dtoToSave));
    }

    @Test
    public void testIfIsDeleted() {
        TransportDTO dtoToDelete = createTestDTO();
        Transport savedTransport = repo.save(mapper.map(dtoToDelete, Transport.class));
        TransportDTO savedDTO = toDTO(savedTransport);
        setAsReturnedDTO(dtoToDelete, savedDTO.getId());
        ResponseMessage msg = service.deleteTransportById(savedTransport.getId());
        assertEquals("Transportadora com id " + savedTransport.getId() + " excluída!", msg.getMessage());
        assertFalse(repo.existsById(savedTransport.getId()));

    }

    @Test
    public void testIfUpdated() {
        TransportDTO initialDTO = createTestDTO();
        Transport savedTransport = repo.save(mapper.map(initialDTO, Transport.class));
        TransportDTO updateDTO = toDTO(savedTransport);
        updateDTO.setEmail("updatedEmail@gmail.com");
        updateDTO.setNome("updatedName");
        service.updateTransportById(savedTransport.getId(), updateDTO);
        Transport transport = repo.findById(savedTransport.getId()).get();

        assert (toDTO(transport).equals(updateDTO));
    }

    @Test
    public void testIfLogoContentIsProperlySaved() throws IOException {
        byte[] content = createTestImg();
        Transport entity = createTestEntity();
        Long id = repo.save(entity).getId();
        service.insertLogoOnTransportById(id, content);
        assert (Arrays.equals(repo.getLogoByid(id), content));
    }

    @Test
    public void testIfLogoContentIsProperlyReturned() throws IOException {
        byte[] content = createTestImg();
        Transport entity = createTestEntity();
        entity.setLogo(content);
        Long id = repo.save(entity).getId();
        byte[] returnedFromService = service.getLogoByTransportId(id);
        assert (Arrays.equals(content, returnedFromService));
    }

    @Test
    public void testIfReturnedNotFoundOnNotFoundCEP() {
        Long invalidCep = createInvalidCep();
        assertThrows(InvalidCepFormatException.class, () -> service.getAdressByCep(invalidCep));


    }
}

