package br.com.clamed.pharmacymanagement.service;

import br.com.clamed.pharmacymanagement.controllers.dto.UsuarioRequest;
import br.com.clamed.pharmacymanagement.controllers.dto.UsuarioResponse;
import br.com.clamed.pharmacymanagement.model.entity.UsuarioEntity;
import br.com.clamed.pharmacymanagement.model.repository.UsuarioRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerErrorException;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public UsuarioResponse save(UsuarioRequest usuarioRequest) {
        UsuarioEntity usuarioEntity = repository.save(new UsuarioEntity(usuarioRequest.getEmail(), usuarioRequest.getSenha()));
        return new UsuarioResponse(usuarioEntity.getEmail(), usuarioEntity.getSenha());
    }

    public UsuarioResponse update(UsuarioRequest usuarioRequest) {
        UsuarioEntity usuarioEntity = repository.save(new UsuarioEntity(usuarioRequest.getId(), usuarioRequest.getEmail(), usuarioRequest.getSenha()));
        return new UsuarioResponse(usuarioEntity.getEmail(), usuarioEntity.getSenha());
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public UsuarioEntity encontraById(Long id) {
        return repository.findById(id).get();
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
                        entity.getEmail(),
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
                        entity.getEmail(),
                        entity.getSenha()
                ));
            }
            return usuarios;
        } catch (Exception e) {
            throw new ServerErrorException("Erro ao buscar usuarios");
        }
    }
}


