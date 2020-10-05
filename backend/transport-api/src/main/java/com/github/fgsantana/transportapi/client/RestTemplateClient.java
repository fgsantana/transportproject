package com.github.fgsantana.transportapi.client;


import com.github.fgsantana.transportapi.dto.EnderecoDTO;
import com.github.fgsantana.transportapi.exception.CepNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;


@Service
public class RestTemplateClient {

    @Autowired
    RestTemplate restTemplate;



    public EnderecoDTO getAdress(Long cep) throws CepNotFoundException {
        EnderecoDTO enderecoDTO = restTemplate.getForObject("https://viacep.com.br/ws/" + cep + "/json", EnderecoDTO.class);
        if (enderecoDTO.getCep() == null) {
            throw new CepNotFoundException(cep);
        }

        return enderecoDTO;


    }


}
