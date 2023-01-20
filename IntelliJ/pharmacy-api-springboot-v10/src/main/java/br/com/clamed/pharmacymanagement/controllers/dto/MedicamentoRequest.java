package br.com.clamed.pharmacymanagement.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MedicamentoRequest {
    private Long id;
    private String nome;
    private String laboratorio;
    private String dosagem;
    private String descricao;
    private String preco;
    private String tipo;

    public MedicamentoRequest(String nome, String laboratorio, String dosagem, String descricao, String preco, String tipo) {
        this.nome = nome;
        this.laboratorio = laboratorio;
        this.dosagem = dosagem;
        this.descricao = descricao;
        this.preco = preco;
        this.tipo = tipo;
    }
}
