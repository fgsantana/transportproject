package com.github.fgsantana.transportapi.util;

import com.github.fgsantana.transportapi.dto.TransportDTO;
import com.github.fgsantana.transportapi.entity.Modal;
import com.github.fgsantana.transportapi.entity.Transport;
import org.modelmapper.ModelMapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static com.github.fgsantana.transportapi.entity.Modal.Aeroviario;
import static com.github.fgsantana.transportapi.entity.Modal.Ferroviario;

public class TransportUtil {
    public static ModelMapper mapper = new ModelMapper();


    public static void setAsReturnedDTO(TransportDTO targetDTO, Long id) {
        targetDTO.setId(id);
        targetDTO.setLogoUrl("http://localhost:8080/api/v1/transports/" + id + "/logo");
    }


    public static TransportDTO toDTO(Transport transport) {

        TransportDTO dto = mapper.map(transport, TransportDTO.class);

        dto.setLogoUrl("http://localhost:8080/api/v1/transports/" + dto.getId() + "/logo");
        return dto;
    }

    public static byte[] createTestImg() throws IOException {
        FileInputStream fis = new FileInputStream(new File("testImg.jpg"));
        return fis.readAllBytes();
    }

    public static Long createInvalidCep() {
        Random random = new Random();
        int randomSize;

        do {
            randomSize = random.nextInt(15);
        }
        while (randomSize == 8 || randomSize == 0);
        String[] arr = new String[randomSize];

        for (int i = 0; i < randomSize; i++) {
            arr[i] = String.valueOf(random.nextInt(9));
        }
        String s = String.join("", arr);


        return Long.valueOf(s);


    }


    public static TransportDTO createTestDTO() {
        Modal[] modais = {Aeroviario, Ferroviario};
        List<Modal> lista = Arrays.asList(modais);
        TransportDTO dto = new TransportDTO();
        dto.setEmail("emailteste@gmail.com");
        dto.setNome("Teste Teste");
        dto.setEmpresa("Transportadora Teste");
        dto.setTelefone("8112345678");
        dto.setCelular("81987654321");
        dto.setWhatsapp("81987654321");
        dto.setModais(List.of(Aeroviario, Ferroviario));
        dto.setCep("12345678");
        dto.setUf("SC");
        dto.setCidade("TesteC");
        dto.setBairro("TesteB");
        dto.setLogradouro("Avenida Teste");
        dto.setNumero(99);
        return dto;
    }

    public static Transport createTestEntity() {
        Modal[] modais = {Aeroviario, Ferroviario};
        List<Modal> lista = Arrays.asList(modais);
        Transport entity = new Transport();
        entity.setId(1L);
        entity.setEmail("emailteste@gmail.com");
        entity.setNome("Teste Teste");
        entity.setEmpresa("Transportadora Teste");
        entity.setTelefone("8112345678");
        entity.setWhatsapp("81987654321");
        entity.setCelular("81987654321");
        entity.setModais(List.of(Aeroviario, Ferroviario));
        entity.setCep("12345678");
        entity.setUf("SC");
        entity.setCidade("TesteC");
        entity.setBairro("TesteB");
        entity.setLogradouro("Avenida Teste");
        entity.setNumero(30);
        return entity;

    }


}
