package br.com.clamed.pharmacymanagement.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRequest {
    private String login;
    private String senha;
    private Long id;

    public UsuarioRequest(String email, String senha){
        this.login=email;
        this.senha=senha;
    }
}
