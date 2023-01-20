package br.com.clamed.pharmacymanagement.controllers.dto;

import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResponse {
    private Long id;
    private String email;
    private String senha;

    public UsuarioResponse(String email, String senha){
        this.email =  email;
        this.senha = senha;
    }

}
