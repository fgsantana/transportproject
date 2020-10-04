package com.github.fgsantana.transportapi.resource;

import com.github.fgsantana.transportapi.dto.TransportDTO;
import com.github.fgsantana.transportapi.message.ResponseMessage;
import com.github.fgsantana.transportapi.service.TransportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
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

    @GetMapping(value = "/{id}/logo", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getLogoByTransportId(@PathVariable("id") Long id) {
        return service.getLogoByTransportId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TransportDTO saveTransport(@Valid @RequestBody TransportDTO transportDTO) {
        return service.saveTransport(transportDTO);
    }

    @PutMapping(value = "/{id}/logo", produces = MediaType.IMAGE_JPEG_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public byte[] insertLogoOnTransportById(@PathVariable("id") Long id, MultipartFile logoImg) throws IOException {
        return service.insertLogoOnTransportById(id, logoImg.getBytes());
    }

    @PutMapping("/{id}")
    public TransportDTO updateTransportById(@PathVariable("id") Long id, @Valid @RequestBody TransportDTO transportDTO) {
        return service.updateTransportById(id, transportDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseMessage deleteTransportById(@PathVariable("id") Long id) {
        return service.deleteTransportById(id);
    }


}
