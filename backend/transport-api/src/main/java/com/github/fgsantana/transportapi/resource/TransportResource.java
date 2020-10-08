package com.github.fgsantana.transportapi.resource;

import com.github.fgsantana.transportapi.dto.EnderecoDTO;
import com.github.fgsantana.transportapi.dto.TransportDTO;
import com.github.fgsantana.transportapi.exception.CepNotFoundException;
import com.github.fgsantana.transportapi.exception.InvalidCepFormatException;
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

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/transports")
public class TransportResource {

    @Autowired
    private TransportService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TransportDTO saveTransport(@Valid @RequestBody TransportDTO transportDTO) {
        return service.saveTransport(transportDTO);
    }

    @GetMapping
    public List<TransportDTO> getTransports() {
        return service.getTransports();

    }

    @GetMapping("/{id}")
    public TransportDTO getTransportById(@PathVariable("id") Long id) {
        return service.getTransportById(id);
    }

    @PutMapping("/{id}")
    public TransportDTO updateTransportById(@PathVariable("id") Long id, @Valid @RequestBody TransportDTO transportDTO) {
        return service.updateTransportById(id, transportDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseMessage deleteTransportById(@PathVariable("id") Long id) {
        return service.deleteTransportById(id);
    }

    @GetMapping("/adress/{cep}")
    public EnderecoDTO getAdressByCep(@PathVariable("cep") Long cep) throws CepNotFoundException, InvalidCepFormatException {

        return service.getAdressByCep(cep);
    }

    @GetMapping(value = "/{id}/logo", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getLogoByTransportId(@PathVariable("id") Long id) {
        return service.getLogoByTransportId(id);
    }

    @PutMapping(value = "/{id}/logo", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseMessage insertLogoOnTransportById(@PathVariable("id") Long id, @RequestBody MultipartFile logoImg) throws IOException {
        return service.insertLogoOnTransportById(id, logoImg.getBytes());
    }

    @DeleteMapping("/{id}/logo")
    public ResponseMessage deleteLogoByTransportId(@PathVariable("id") Long id) {
        return service.deleteLogoByTransportId(id);
    }


}
