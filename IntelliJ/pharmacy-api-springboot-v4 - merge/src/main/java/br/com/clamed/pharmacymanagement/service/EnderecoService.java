package br.com.clamed.pharmacymanagement.service;

import br.com.clamed.pharmacymanagement.controllers.dto.EnderecoRequest;
import br.com.clamed.pharmacymanagement.controllers.dto.EnderecoResponse;

import br.com.clamed.pharmacymanagement.model.entity.EnderecoEntity;
import br.com.clamed.pharmacymanagement.model.repository.EnderecoRepository;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerErrorException;

import java.util.ArrayList;
import java.util.List;

@Service
public class EnderecoService {
    @Autowired
    private EnderecoRepository repository;

    public EnderecoService(EnderecoRepository repository) {
        this.repository = repository;
    }

    public EnderecoResponse save(EnderecoRequest request) {
        try {
            EnderecoEntity entity = repository.save(
                    new EnderecoEntity(
                            request.getCep(),
                            request.getLogradouro(),
                            request.getNumero(),
                            request.getBairro(),
                            request.getCidade(),
                            request.getEstado(),
                            request.getComplemento(),
                            request.getLatitude(),
                            request.getLongitude()
                    )
            );
            return new EnderecoResponse(
                    entity.getId(),
                    entity.getCep(),
                    entity.getLogradouro(),
                    entity.getNumero(),
                    entity.getBairro(),
                    entity.getCidade(),
                    entity.getEstado(),
                    entity.getComplemento(),
                    entity.getLatitude(),
                    entity.getLongitude()
            );
        } catch (Exception e) {
            throw new ServerErrorException("Erro desconhecido ao salvar endereço");
        }
    }

    public EnderecoResponse update(Long id, EnderecoRequest request) {
        try {
            EnderecoEntity entity = repository.findById(id).get();

            entity.setCep(request.getCep());
            entity.setLogradouro(request.getLogradouro());
            entity.setNumero(request.getNumero());
            entity.setBairro(request.getBairro());
            entity.setCidade(request.getCidade());
            entity.setEstado(request.getEstado());
            entity.setComplemento(request.getComplemento());
            entity.setLatitude(request.getLatitude());
            entity.setLongitude(request.getLongitude());
            repository.save(entity);

            return new EnderecoResponse(
                    entity.getId(),
                    entity.getCep(),
                    entity.getLogradouro(),
                    entity.getNumero(),
                    entity.getBairro(),
                    entity.getCidade(),
                    entity.getEstado(),
                    entity.getComplemento(),
                    entity.getLatitude(),
                    entity.getLongitude()
            );
        }catch (Exception e) {
            throw new ServerErrorException("Erro desconhecido ao atualizar endereço");
        }
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        }catch (Exception e) {
            throw new ServerErrorException("Erro desconhecido ao deletar endereço");
        }
    }

    public EnderecoEntity encontraById(Long id) {
        try {
            return repository.findById(id).get();
        }catch (Exception e) {
            throw new ServerErrorException("Erro desconhecido ao encontrar endereço");
        }
    }

    public List<EnderecoResponse> encontraTodosEnderecos() throws NotFoundException {
        try {
            List<EnderecoResponse> enderecos = new ArrayList<>();
            List<EnderecoEntity> entity = repository.findAll();
            if (entity.isEmpty()) {
                throw new NotFoundException("Nenhum endereço encontrado.");
            }
            for (EnderecoEntity item : entity) {
                enderecos.add(new EnderecoResponse(
                        item.getId(),
                        item.getCep(),
                        item.getLogradouro(),
                        item.getNumero(),
                        item.getBairro(),
                        item.getCidade(),
                        item.getEstado(),
                        item.getComplemento(),
                        item.getLatitude(),
                        item.getLongitude()
                ));
            }
            return enderecos;
        } catch (Exception e) {
            throw new ServerErrorException("Erro desconhecido ao buscar endereços.");
        }
    }
}
