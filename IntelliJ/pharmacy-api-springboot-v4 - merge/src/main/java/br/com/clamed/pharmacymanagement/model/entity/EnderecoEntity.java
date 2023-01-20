package br.com.clamed.pharmacymanagement.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="endereco")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EnderecoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;
    @NotNull
    @Column
    private String cep;
    @NotNull
    @Column
    private String logradouro;
    @NotNull
    @Column
    private String numero;
    @NotNull
    @Column
    private String bairro;
    @NotNull
    @Column
    private String cidade;
    @NotNull
    @Column
    private String estado;
    @Column
    private String complemento;
    @NotNull
    @Column
    private String latitude;
    @NotNull
    @Column
    private String longitude;

    public EnderecoEntity(String cep, String logradouro, String numero, String bairro, String cidade, String estado, String complemento, String latitude, String longitude) {
        this.cep = cep;
        this.logradouro = logradouro;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.complemento = complemento;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public EnderecoEntity(String cep, String logradouro, String bairro, String cidade, String estado) {
        this.cep = cep;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
    }
}
