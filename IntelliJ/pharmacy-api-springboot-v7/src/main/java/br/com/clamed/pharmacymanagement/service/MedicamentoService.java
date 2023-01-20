package br.com.clamed.pharmacymanagement.service;



import br.com.clamed.pharmacymanagement.controllers.dto.MedicamentoRequest;
import br.com.clamed.pharmacymanagement.controllers.dto.MedicamentoResponse;

import br.com.clamed.pharmacymanagement.exception.NotFoundException;
import br.com.clamed.pharmacymanagement.exception.ServerErrorException;

import br.com.clamed.pharmacymanagement.model.entity.MedicamentoEntity;
import br.com.clamed.pharmacymanagement.model.repository.MedicamentoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MedicamentoService {
    @Autowired
    private MedicamentoRepository repository;

    public MedicamentoService(MedicamentoRepository repository) {
        this.repository = repository;
    }

    public MedicamentoResponse save(MedicamentoRequest request) {
        try {
            MedicamentoEntity entity = repository.save(
                    new MedicamentoEntity(
                            request.getNome(),
                            request.getLaboratorio(),
                            request.getDosagem(),
                            request.getDescricao(),
                            request.getPreco(),
                            request.getTipo()
                    )
            );
            return new MedicamentoResponse(
                    entity.getNome(),
                    entity.getLaboratorio(),
                    entity.getDosagem(),
                    entity.getDescricao(),
                    entity.getPreco(),
                    entity.getTipo()
            );
        } catch (Exception e) {
            throw new ServerErrorException("Erro desconhecido ao salvar medicamento", HttpStatus.BAD_REQUEST);
        }
    }



    public MedicamentoResponse update(Long id, MedicamentoRequest request) {
        try {

            MedicamentoEntity entity = repository.findById(id).get();
            System.out.println(entity.getId());
            if (entity.getId().equals(null)) {
                throw new NotFoundException();
            } else {
                entity.setNome(request.getNome());
                entity.setLaboratorio(request.getLaboratorio());
                entity.setDosagem(request.getDosagem());
                entity.setDescricao(request.getDescricao());
                entity.setPreco(request.getPreco());
                entity.setTipo(request.getTipo());
                repository.save(entity);

                return new MedicamentoResponse(
                        entity.getId(),
                        entity.getNome(),
                        entity.getLaboratorio(),
                        entity.getDosagem(),
                        entity.getDescricao(),
                        entity.getPreco(),
                        entity.getTipo()
                );
            }
        } catch (NotFoundException e) {
            throw new NotFoundException("Nenhum medicamento encontrado", HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            throw new ServerErrorException("Erro desconhecido ao atualizar medicamento", HttpStatus.BAD_REQUEST);
        }
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        }catch (Exception e) {
            throw new ServerErrorException("Medicamento não encontrado.", HttpStatus.BAD_REQUEST);
        }
    }

    public MedicamentoEntity encontraById(Long id) {
        try {
            return repository.findById(id).get();
        }catch (Exception e) {
            throw new ServerErrorException("Medicamento não encontrado.", HttpStatus.BAD_REQUEST);
        }
    }

    public List<MedicamentoResponse> encontraTodosMedicamentos()  {
        try {
            List<MedicamentoResponse> list = new ArrayList<>();
            List<MedicamentoEntity> entity = repository.findAll();
            if (entity.size()==0) {
                throw new NotFoundException();
            }
            for (MedicamentoEntity item : entity) {
                list.add(new MedicamentoResponse(
                        item.getId(),
                        item.getNome(),
                        item.getLaboratorio(),
                        item.getDosagem(),
                        item.getDescricao(),
                        item.getPreco(),
                        item.getTipo()
                ));
            }
            return list;
        }catch(NotFoundException e){
            throw new NotFoundException("Nenhum assunto encontrado", HttpStatus.NO_CONTENT);
        }catch (Exception e) {
            throw new ServerErrorException("Erro desconhecido ao buscar medicamentos.", HttpStatus.BAD_REQUEST);
        }
    }
}
