package br.com.clamed.pharmacymanagement.service;



//import br.com.clamed.pharmacymanagement.client.FeignClient;
import br.com.clamed.pharmacymanagement.controllers.dto.FarmaciaRequest;
import br.com.clamed.pharmacymanagement.controllers.dto.FarmaciaResponse;
import br.com.clamed.pharmacymanagement.exception.NotFoundException;
import br.com.clamed.pharmacymanagement.exception.ServerErrorException;


import br.com.clamed.pharmacymanagement.model.entity.EnderecoEntity;
import br.com.clamed.pharmacymanagement.model.entity.FarmaciaEntity;
import br.com.clamed.pharmacymanagement.model.repository.EnderecoRepository;
import br.com.clamed.pharmacymanagement.model.repository.FarmaciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;




import java.util.ArrayList;
import java.util.List;


@Service
public class FarmaciaService {
    @Autowired
    private FarmaciaRepository repository;
    @Autowired
    private EnderecoRepository enderecoRepository;






//    public FarmaciaResponse save(FarmaciaRequest request) {
//        try {
//        EnderecoEntity endereco = enderecoRepository.findById(request.getEnderecoId()).orElseThrow(()-> new NotFoundException("Endereço não existe"));
//
//               FarmaciaEntity entity = repository.save(
//                    new FarmaciaEntity(
//                            request.getRazaoSocial(),
//                            request.getCnpj(),
//                            request.getNomeFantasia(),
//                            request.getEmail(),
//                            request.getTelefone(),
//                            request.getCelular(),
//                            endereco
//                    )
//            );
//            return new FarmaciaResponse(
//                    entity.getId(),
//                    entity.getRazaoSocial(),
//                    entity.getCnpj(),
//                    entity.getNomeFantasia(),
//                    entity.getEmail(),
//                    entity.getTelefone(),
//                    entity.getCelular(),
//                    entity.getEnderecoEntity()
//            );
//        } catch (Exception e) {
//            throw new ServerErrorException("Erro desconhecido ao salvar farmácia", HttpStatus.BAD_REQUEST);
//        }
//    }
public FarmaciaResponse save(FarmaciaRequest request) {

        EnderecoEntity endereco = enderecoRepository.findById(request.getEnderecoId()).get();

               FarmaciaEntity entity = repository.save(
                    new FarmaciaEntity(
                            request.getRazaoSocial(),
                            request.getCnpj(),
                            request.getNomeFantasia(),
                            request.getEmail(),
                            request.getTelefone(),
                            request.getCelular(),
                            endereco

                    )
            );
            return new FarmaciaResponse(
                    entity.getId(),
                    entity.getRazaoSocial(),
                    entity.getCnpj(),
                    entity.getNomeFantasia(),
                    entity.getEmail(),
                    entity.getTelefone(),
                    entity.getCelular(),
                    entity.getEnderecoEntity()
            );

    }

    public FarmaciaResponse update(FarmaciaRequest request) {
        try {

            EnderecoEntity endereco = enderecoRepository.findById(request.getEnderecoId()).orElseThrow(()-> new NotFoundException("Endereço não existe"));
            FarmaciaEntity entity = repository.findById(request.getId()).get();

            if (entity.getId().equals(null)) {
                throw new NotFoundException();
            } else {

                entity.setRazaoSocial(request.getRazaoSocial());
                entity.setCnpj(request.getCnpj());
                entity.setNomeFantasia(request.getNomeFantasia());
                entity.setEmail(request.getEmail());
                entity.setTelefone(request.getTelefone());
                entity.setCelular(request.getCelular());
                entity.setEnderecoEntity(endereco);

                repository.save(entity);

                return new FarmaciaResponse(
                        entity.getId(),
                        entity.getRazaoSocial(),
                        entity.getCnpj(),
                        entity.getNomeFantasia(),
                        entity.getEmail(),
                        entity.getTelefone(),
                        entity.getCelular(),
                        entity.getEnderecoEntity()
                );
            }
        } catch (NotFoundException e) {
            throw new NotFoundException("Nenhuma farmácia encontrado", HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            throw new ServerErrorException("Erro desconhecido ao atualizar farmácia", HttpStatus.BAD_REQUEST);
        }
    }

    public void delete(Long id) {
        try {
            FarmaciaEntity farmacia = repository.findById(id).orElseThrow(()-> new NotFoundException("Endereço não existe"));;
            farmacia.setEnderecoEntity(null);


            repository.deleteById(id);
        }catch (Exception e) {
            throw new ServerErrorException("Farmácia não encontrado.", HttpStatus.BAD_REQUEST);
        }
    }

    public FarmaciaEntity encontraById(Long id) {
        try {
            return repository.findById(id).get();
        }catch (Exception e) {
            throw new ServerErrorException("Farmácia não encontrado.", HttpStatus.BAD_REQUEST);
        }
    }

    public List<FarmaciaResponse> encontraTodosFarmacias()  {
        try {
            List<FarmaciaResponse> farmacias = new ArrayList<>();
            List<FarmaciaEntity> entity = repository.findAll();
            if (entity.size()==0) {
                throw new NotFoundException();
            }
            for (FarmaciaEntity item : entity) {
                farmacias.add(new FarmaciaResponse(
                        item.getId(),
                        item.getRazaoSocial(),
                        item.getCnpj(),
                        item.getNomeFantasia(),
                        item.getEmail(),
                        item.getTelefone(),
                        item.getCelular(),
                        item.getEnderecoEntity()
                ));
            }
            return farmacias;
        }catch(NotFoundException e){
            throw new NotFoundException("Nenhuma farmácia encontrada", HttpStatus.NO_CONTENT);
        }catch (Exception e) {
            throw new ServerErrorException("Erro desconhecido ao buscar farmácias.", HttpStatus.BAD_REQUEST);
        }
    }
}
