package br.com.clamed.pharmacymanagement.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Table(name="medicamento")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MedicamentoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;

    @NotNull
    @Column
    private String nome;

    @NotNull
    @Column
    private String laboratorio;

    @NotNull
    @Column
    private String dosagem;

    @Column
    private String descricao;

    @NotNull
    @Column
    private String preco;

    @NotNull
    @Column
    private String tipo;

    public MedicamentoEntity(String nome, String laboratorio, String dosagem, String descricao, String preco, String tipo) {
                this.nome= nome;
                this.laboratorio=laboratorio;
                this.dosagem=dosagem;
                this.descricao=descricao;
                this.preco=preco;
                this.tipo= tipo;
    }
}
