package br.com.clamed.pharmacymanagement.controllers.dto;

import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoResponse {
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

    public EnderecoResponse( String cep, String logradouro, String numero, String bairro, String cidade, String estado, String complemento, String latitude, String longitude) {
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
}
