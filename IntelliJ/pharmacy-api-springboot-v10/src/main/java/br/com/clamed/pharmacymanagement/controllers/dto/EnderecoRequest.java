package br.com.clamed.pharmacymanagement.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoRequest {

    private Long id;

    private String cep;

    private String logradouro;

    private String numero;

    private String bairro;

    private String localidade;

    private String uf;

    private String complemento;

    private String latitude;

    private String longitude;

    public EnderecoRequest(String cep, String logradouro, String numero, String bairro, String localidade, String uf, String complemento, String latitude, String longitude) {
        this.cep = cep;
        this.logradouro = logradouro;
        this.numero = numero;
        this.bairro = bairro;
        this.localidade = localidade;
        this.uf = uf;
        this.complemento = complemento;
        this.latitude = latitude;
        this.longitude = longitude;
    }
    public EnderecoRequest (String cep, String numero, String logradouro, String complemento, String latitude, String longitude){
        this.cep=cep;
        this.logradouro=logradouro;
        this.numero=numero;
        this.complemento=complemento;
        this.latitude=latitude;
        this.longitude=longitude;
    }

}
