package br.com.clamed.pharmacymanagement.model.repository;

import br.com.clamed.pharmacymanagement.model.entity.EnderecoEntity;
import br.com.clamed.pharmacymanagement.model.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface EnderecoRepository extends JpaRepository<EnderecoEntity, Long> {
    @Query(value="select * from usuario where cep like %?1%", nativeQuery = true)
    ArrayList<UsuarioEntity> getUsuarioEntityByEmail(String cep);
}
