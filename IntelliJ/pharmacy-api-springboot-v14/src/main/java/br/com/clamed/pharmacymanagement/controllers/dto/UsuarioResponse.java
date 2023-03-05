package br.com.clamed.pharmacymanagement.controllers.dto;

import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResponse {
    private Long id;
    private String login;
    private String senha;

    public UsuarioResponse(String login, String senha){
        this.login =  login;
        this.senha = senha;
    }

}
