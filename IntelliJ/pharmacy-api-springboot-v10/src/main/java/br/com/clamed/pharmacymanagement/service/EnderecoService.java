package br.com.clamed.pharmacymanagement.service;

import br.com.clamed.pharmacymanagement.client.FeignClient;
import br.com.clamed.pharmacymanagement.controllers.dto.EnderecoRequest;
import br.com.clamed.pharmacymanagement.controllers.dto.EnderecoResponse;


import br.com.clamed.pharmacymanagement.exception.NotFoundException;
import br.com.clamed.pharmacymanagement.exception.ServerErrorException;
import br.com.clamed.pharmacymanagement.model.entity.EnderecoEntity;

import br.com.clamed.pharmacymanagement.model.repository.EnderecoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;




import java.util.ArrayList;
import java.util.List;


@Service
public class EnderecoService {
    @Autowired
    private EnderecoRepository repository;
    @Autowired
    private final FeignClient feign;

    public EnderecoService(FeignClient feign, EnderecoRepository repository) {
        this.feign = feign;
        this.repository = repository;
    }

    public EnderecoResponse save(EnderecoRequest request) {
        try {
            EnderecoEntity endereco = feign.buscaCep(request.getCep());

            EnderecoEntity entity = repository.save(
                    new EnderecoEntity(
                            request.getCep(),
                            endereco.getLogradouro().isEmpty() ? request.getLogradouro():endereco.getLogradouro(),
                            request.getNumero(),
                            endereco.getBairro(),
                            endereco.getLocalidade(),
                            endereco.getUf(),
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
                    entity.getLocalidade(),
                    entity.getUf(),
                    entity.getComplemento(),
                    entity.getLatitude(),
                    entity.getLongitude()
            );
        } catch (Exception e) {
            throw new ServerErrorException("Erro desconhecido ao salvar endereço", HttpStatus.BAD_REQUEST);
        }
    }
//public EnderecoResponse save(EnderecoRequest request) {
//    try {
//        EnderecoEntity entity = repository.save(
//                new EnderecoEntity(
//                            request.getCep(),
//                            request.getLogradouro(),
//                            request.getNumero(),
//                            request.getBairro(),
//                            request.getLocalidade(),
//                            request.getUf(),
//                            request.getComplemento(),
//                            request.getLatitude(),
//                            request.getLongitude()
//                )
//        );
//        return new EnderecoResponse(
//                    entity.getId(),
//                    entity.getCep(),
//                    entity.getLogradouro(),
//                    entity.getNumero(),
//                    entity.getBairro(),
//                    entity.getLocalidade(),
//                    entity.getUf(),
//                    entity.getComplemento(),
//                    entity.getLatitude(),
//                    entity.getLongitude()
//        );
//    } catch (Exception e) {
//        throw new ServerErrorException("Erro desconhecido ao salvar medicamento", HttpStatus.BAD_REQUEST);
//    }
//}

    public EnderecoResponse update(Long id, EnderecoRequest request) {
        try {

            EnderecoEntity entity = repository.findById(id).get();
            System.out.println(entity.getId());
            if (entity.getId().equals(null)) {
                throw new NotFoundException();
            } else {
                entity.setCep(request.getCep());
                entity.setLogradouro(request.getLogradouro());
                entity.setNumero(request.getNumero());
                entity.setBairro(request.getBairro());
                entity.setLocalidade(request.getLocalidade());
                entity.setUf(request.getUf());
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
                        entity.getLocalidade(),
                        entity.getUf(),
                        entity.getComplemento(),
                        entity.getLatitude(),
                        entity.getLongitude()
                );
            }
        } catch (NotFoundException e) {
            throw new NotFoundException("Nenhum endereço encontrado", HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            throw new ServerErrorException("Erro desconhecido ao atualizar endereço", HttpStatus.BAD_REQUEST);
        }
    }

        public void delete(Long id) {
        try {
            repository.deleteById(id);
        }catch (Exception e) {
            throw new ServerErrorException("Endereço não encontrado.", HttpStatus.BAD_REQUEST);
        }
    }

    public EnderecoEntity encontraById(Long id) {
        try {
            return repository.findById(id).get();
        }catch (Exception e) {
            throw new ServerErrorException("Endereço não encontrado.", HttpStatus.BAD_REQUEST);
        }
    }

    public List<EnderecoResponse> encontraTodosEnderecos()  {
        try {
            List<EnderecoResponse> enderecos = new ArrayList<>();
            List<EnderecoEntity> entity = repository.findAll();
            if (entity.size()==0) {
                throw new NotFoundException();
            }
            for (EnderecoEntity item : entity) {
                enderecos.add(new EnderecoResponse(
                        item.getId(),
                        item.getCep(),
                        item.getLogradouro(),
                        item.getNumero(),
                        item.getBairro(),
                        item.getLocalidade(),
                        item.getUf(),
                        item.getComplemento(),
                        item.getLatitude(),
                        item.getLongitude()
                ));
            }
            return enderecos;
        }catch(NotFoundException e){
            throw new NotFoundException("Nenhum assunto encontrado", HttpStatus.NO_CONTENT);
        }catch (Exception e) {
            throw new ServerErrorException("Erro desconhecido ao buscar endereços.", HttpStatus.BAD_REQUEST);
        }
    }
}
