package br.com.clamed.pharmacymanagement.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name="usuario")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UsuarioEntity  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column(length=150)
    @NotNull
    private String email;

    @Column (length=16)
    @NotNull
    private String senha;


    public UsuarioEntity(String email, String senha) {
        this.email=email;
        this.senha=senha;
    }
}
