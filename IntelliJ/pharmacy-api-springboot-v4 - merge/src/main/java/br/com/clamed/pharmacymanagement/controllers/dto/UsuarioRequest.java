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
    private String email;
    private String senha;
    private Long id;

    public UsuarioRequest(String email, String senha){
        this.email=email;
        this.senha=senha;
    }
}
