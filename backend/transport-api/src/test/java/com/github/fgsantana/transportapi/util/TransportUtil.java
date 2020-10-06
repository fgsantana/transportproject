package com.github.fgsantana.transportapi.util;

import com.github.fgsantana.transportapi.dto.TransportDTO;
import com.github.fgsantana.transportapi.entity.Modal;
import com.github.fgsantana.transportapi.entity.Transport;

import java.util.Arrays;
import java.util.List;

import static com.github.fgsantana.transportapi.entity.Modal.Aeroviario;
import static com.github.fgsantana.transportapi.entity.Modal.Ferroviario;

public class TransportUtil {


    public static TransportDTO createFakeDTO() {
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

    public static Transport createFakeEntity() {
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
    public static boolean equalsEntityContent(Transport t1, Transport t2) {

        return t1.getNumero()==t2.getNumero() &&
                t1.getEmail().equals(t2.getEmail()) &&
               t1.getNome().equals(t2.getNome()) &&
                t1.getEmpresa().equals(t2.getEmpresa()) &&
                t1.getTelefone().equals(t2.getTelefone()) &&
                t1.getCelular().equals(t2.getCelular()) &&
                t1.getWhatsapp().equals(t2.getWhatsapp()) &&
                t1.getModais().containsAll(t2.getModais()) &&
                t1.getCep().equals( t2.getCep()) &&
                t1.getUf().equals(t2.getUf()) &&
                t1.getCidade().equals(t2.getCidade()) &&
                t1.getBairro().equals(t2.getBairro()) &&
                t1.getLogradouro().equals(t2.getLogradouro());
    }


}
