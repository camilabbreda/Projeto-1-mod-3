package br.com.clamed.pharmacymanagement.service;

import br.com.clamed.pharmacymanagement.controllers.dto.UsuarioRequest;
import br.com.clamed.pharmacymanagement.controllers.dto.UsuarioResponse;
import br.com.clamed.pharmacymanagement.model.entity.UsuarioEntity;
import br.com.clamed.pharmacymanagement.model.repository.UsuarioRepository;
import javassist.NotFoundException;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ServerErrorException;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService implements UserDetailsService {
    @Autowired
    private UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public UsuarioResponse save(UsuarioRequest usuarioRequest) {
        try {
            usuarioRequest.setSenha(new BCryptPasswordEncoder().encode(usuarioRequest.getSenha()));
            UsuarioEntity usuarioEntity = repository.save(new UsuarioEntity(usuarioRequest.getLogin(), usuarioRequest.getSenha()));
            return new UsuarioResponse(usuarioEntity.getLogin(), usuarioEntity.getSenha());
        } catch (Exception e) {
            throw new ServerErrorException("Erro desconhecido ao salvar endereço");
        }
    }


    public UsuarioResponse update(UsuarioRequest usuarioRequest) {
        try {
            UsuarioEntity usuarioEntity = repository.save(new UsuarioEntity(usuarioRequest.getId(), usuarioRequest.getLogin(), usuarioRequest.getSenha()));
            return new UsuarioResponse(usuarioEntity.getLogin(), usuarioEntity.getSenha());

        } catch (Exception e) {
            throw new ServerErrorException("Erro desconhecido ao atualizar usuário");
        }
    }

    @Transactional
    public void delete(Long id) {
        try {
            repository.deleteById(id);
        }catch (Exception e) {
            throw new ServerErrorException("Erro desconhecido ao deletar usuário");
        }
    }

    public UsuarioEntity encontraById(Long id) throws NotFoundException {
        try {
            return repository.findById(id)
                    .orElseThrow(() -> new NotFoundException("Usuário nao encontrado"));
        }catch (NotFoundException e){
            throw new NotFoundException("Usuário não encontrado.");
        }
        catch (Exception e) {
            throw new ServerErrorException("Erro desconhecido ao encontrar usuário");

        }
    }

    public List<UsuarioResponse> encontraByEmail(String email) throws NotFoundException {
        try {
            List<UsuarioResponse> usuarios = new ArrayList<>();
            List<UsuarioEntity> usuarioEntity = repository.getUsuarioEntityByEmail(email);
            if (usuarioEntity.isEmpty()) {
                throw new NotFoundException("Nenhum usuário encontrado.");
            }
            for (UsuarioEntity entity : usuarioEntity) {
                usuarios.add(new UsuarioResponse(
                        entity.getId(),
                        entity.getLogin(),
                        entity.getSenha()
                ));
            }
            return usuarios;
        } catch (Exception e) {
            throw new ServerErrorException("Erro ao buscar usuarios");
        }
    }

    public List<UsuarioResponse> encontraTodosUsuarios() throws NotFoundException {
        try {
            List<UsuarioResponse> usuarios = new ArrayList<>();
            List<UsuarioEntity> usuarioEntity = repository.findAll();
            if (usuarioEntity.isEmpty()) {
                throw new NotFoundException("Nenhum usuário encontrado.");
            }
            for (UsuarioEntity entity : usuarioEntity) {
                usuarios.add(new UsuarioResponse(
                        entity.getId(),
                        entity.getLogin(),
                        entity.getSenha()
                ));
            }
            return usuarios;
        } catch (Exception e) {
            throw new ServerErrorException("Erro ao buscar usuarios");
        }
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UsuarioEntity usuario = repository.findUserByLogin(username);

        if(usuario == null){
            throw new UsernameNotFoundException("Usuário não encontrado");
        }

        return new User(usuario.getLogin(), usuario.getPassword(), usuario.getAuthorities());

    }

    @Transactional(readOnly = true)
    public List<UsuarioEntity> getUsers(){

        List<UsuarioEntity> usuarios = repository.findAll();

        return usuarios;
    }
}


