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

    private String cidade;

    private String estado;

    private String complemento;

    private String latitude;

    private String longitude;

    public EnderecoRequest(String cep, String logradouro, String numero, String bairro, String cidade, String estado, String complemento, String latitude, String longitude) {
        this.cep = cep;
        this.logradouro = logradouro;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.complemento = complemento;
        this.latitude = latitude;
        this.longitude = longitude;
    }
//    {
//        "cep" : "cep",
//        "logradouro" : "logradouro",
//        "numero" : "numero",
//        "bairro" : "bairro",
//        "cidade" : "cidade",
//        "estado" : "estado",
//        "complemento" : "complemento",
//        "latitude" : "latitude",
//        "longitude" : "longitude"
//    }
}
