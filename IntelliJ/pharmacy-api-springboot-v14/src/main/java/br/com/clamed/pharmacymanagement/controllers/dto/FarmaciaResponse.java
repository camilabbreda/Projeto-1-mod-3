package br.com.clamed.pharmacymanagement.controllers.dto;

import br.com.clamed.pharmacymanagement.model.entity.EnderecoEntity;
import lombok.*;


@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FarmaciaResponse {

    private Long id;
    private String razaoSocial;
    private String cnpj;
    private String nomeFantasia;
    private String email;
    private String telefone;
    private String celular;
    private EnderecoEntity enderecoEntity;

    public FarmaciaResponse(String razaoSocial, String cnpj, String nomeFantasia, String email, String telefone, String celular, EnderecoEntity enderecoEntity) {
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.nomeFantasia = nomeFantasia;
        this.email = email;
        this.telefone = telefone;
        this.celular = celular;
        this.enderecoEntity = enderecoEntity;
    }

}
