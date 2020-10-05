package com.github.fgsantana.transportapi.client;


import com.github.fgsantana.transportapi.dto.EnderecoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class RestTemplateClient {

    @Autowired
    RestTemplate restTemplate;


    public EnderecoDTO getAdress(Long cep) {
        EnderecoDTO enderecoDTO = restTemplate.getForObject("https://viacep.com.br/ws/" + cep + "/json", EnderecoDTO.class);

        return enderecoDTO;


    }


}
