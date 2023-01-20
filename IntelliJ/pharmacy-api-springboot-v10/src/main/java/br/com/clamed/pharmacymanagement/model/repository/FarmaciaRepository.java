package br.com.clamed.pharmacymanagement.model.repository;

import br.com.clamed.pharmacymanagement.model.entity.FarmaciaEntity;
import br.com.clamed.pharmacymanagement.model.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface FarmaciaRepository extends JpaRepository<FarmaciaEntity, Long> {
    @Query(value="select * from farmacia where nome like %?1%", nativeQuery = true)
    ArrayList<UsuarioEntity> getFarmaciaByNome(String nome);
}
