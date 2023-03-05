package br.com.clamed.pharmacymanagement.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name="usuario")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UsuarioEntity  implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
;
    @Column(nullable = false, unique = true)
    private String login;

    @Column
    @NotNull
    private String senha;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuarios_role", uniqueConstraints = @UniqueConstraint(columnNames = {"usuario_id", "role_id"}, name = "unique_role_usuario"),
            joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "id", table = "usuario",
                    foreignKey = @ForeignKey(name = "fk_usuario", value = ConstraintMode.CONSTRAINT)),

            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id", table = "role", updatable = false,
                    foreignKey = @ForeignKey(name = "fk_role", value = ConstraintMode.CONSTRAINT)))
    private List<RoleEntity> roles;

    public UsuarioEntity(Long id, String login, String senha) {
        this.id=id;
        this.login=login;
        this.senha=senha;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    public UsuarioEntity(String login, String senha) {
        this.login=login;
        this.senha=senha;
    }
}
