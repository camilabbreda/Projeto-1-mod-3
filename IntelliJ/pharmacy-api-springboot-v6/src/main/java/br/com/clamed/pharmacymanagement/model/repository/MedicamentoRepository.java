package br.com.clamed.pharmacymanagement.model.repository;


import br.com.clamed.pharmacymanagement.model.entity.MedicamentoEntity;
import br.com.clamed.pharmacymanagement.model.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
@Repository
public interface MedicamentoRepository extends JpaRepository<MedicamentoEntity, Long> {
    @Query(value="select * from medicamento where nome like %?1%", nativeQuery = true)
    ArrayList<UsuarioEntity> getUsuarioEntityByEmail(String nome);
}
