package br.com.clamed.pharmacymanagement.controllers.dto;

import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MedicamentoResponse {
    private Long id;
    private String nome;
    private String laboratorio;
    private String dosagem;
    private String descricao;
    private String preco;
    private String tipo;

    public MedicamentoResponse(String nome, String laboratorio, String dosagem, String descricao, String preco, String tipo) {
        this.nome= nome;
        this.laboratorio=laboratorio;
        this.dosagem=dosagem;
        this.descricao=descricao;
        this.preco=preco;
        this.tipo= tipo;
    }
}
