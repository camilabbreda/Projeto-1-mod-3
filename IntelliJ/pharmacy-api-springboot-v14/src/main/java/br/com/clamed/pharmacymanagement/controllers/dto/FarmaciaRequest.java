package br.com.clamed.pharmacymanagement.controllers.dto;

import br.com.clamed.pharmacymanagement.model.entity.EnderecoEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FarmaciaRequest {
    private Long id;
    private String razaoSocial;
    private String cnpj;
    private String nomeFantasia;
    private String email;
    private String telefone;
    private String celular;
    private Long enderecoId;
    private EnderecoEntity enderecoEntity;

    public FarmaciaRequest(String razaoSocial, String cnpj, String nomeFantasia, String email, String telefone, String celular, EnderecoEntity enderecoEntity) {
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.nomeFantasia = nomeFantasia;
        this.email = email;
        this.telefone = telefone;
        this.celular = celular;
        this.enderecoEntity = enderecoEntity;
    }
    public FarmaciaRequest(String razaoSocial, String cnpj, String nomeFantasia, String email, String telefone, String celular, Long enderecoId) {
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.nomeFantasia = nomeFantasia;
        this.email = email;
        this.telefone = telefone;
        this.celular = celular;
        this.enderecoId = enderecoId;
    }
//    {
//
//        "razaoSocial" : "razaoSocial",
//        "cnpj" : "cnpj",
//        "nomeFantasia" : "nomeFantasia",
//        "email" : "email",
//        "telefone" : "telefone",
//        "celular" : "celular",
//        "enderecoId" : "enderecoId"
//    }

}
