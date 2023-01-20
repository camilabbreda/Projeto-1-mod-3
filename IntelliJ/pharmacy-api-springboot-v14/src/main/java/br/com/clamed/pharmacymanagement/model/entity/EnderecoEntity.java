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
    @Column(name = "cidade")
    private String localidade;
    @NotNull
    @Column(name = "estado")
    private String uf;
    @Column
    private String complemento;
    @NotNull
    @Column
    private String latitude;
    @NotNull
    @Column
    private String longitude;

    public EnderecoEntity(String cep, String logradouro, String numero, String bairro, String localidade, String uf, String complemento, String latitude, String longitude) {
        this.cep = cep;
        this.logradouro = logradouro;
        this.numero = numero;
        this.bairro = bairro;
        this.localidade = localidade;
        this.uf = uf;
        this.complemento = complemento;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public EnderecoEntity(String cep, String logradouro, String bairro, String localidade, String uf) {
        this.cep = cep;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.localidade = localidade;
        this.uf = uf;
    }
    public EnderecoEntity(String cep, String numero, String logradouro, String complemento, String latitude, String longitude){

                this.cep=cep;
                this.numero=numero;
                this.complemento=complemento;
                this.latitude=latitude;
                this.longitude=longitude;
    }

}
