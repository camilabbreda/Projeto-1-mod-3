package br.com.clamed.pharmacymanagement.client;

import br.com.clamed.pharmacymanagement.controllers.dto.EnderecoResponse;
import br.com.clamed.pharmacymanagement.model.entity.EnderecoEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@org.springframework.cloud.openfeign.FeignClient(url="viacep.com.br/ws/", name = "viacep-api")
public interface FeignClient {
    @GetMapping(value = "{cep}/json")
    EnderecoEntity buscaCep(@PathVariable("cep") String cep);

    @PostMapping("{cep}/json")
    EnderecoEntity buscaCep(@RequestBody() EnderecoResponse cep);
}
