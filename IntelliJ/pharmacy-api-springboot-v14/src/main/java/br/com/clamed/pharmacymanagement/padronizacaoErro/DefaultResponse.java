package br.com.clamed.pharmacymanagement.padronizacaoErro;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DefaultResponse <T> {
    private int status;
    private String mensagem;
    private T data;

    public DefaultResponse(int status, String mensagem) {
        this.status = status;
        this.mensagem = mensagem;
    }

    public DefaultResponse(int status, T data) {
        this.status = status;
        this.data = data;
    }
}
