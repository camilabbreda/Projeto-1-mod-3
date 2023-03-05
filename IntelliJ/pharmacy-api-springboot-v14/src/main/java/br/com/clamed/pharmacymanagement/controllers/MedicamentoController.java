package br.com.clamed.pharmacymanagement.controllers;



import br.com.clamed.pharmacymanagement.controllers.dto.MedicamentoRequest;
import br.com.clamed.pharmacymanagement.controllers.dto.MedicamentoResponse;

import br.com.clamed.pharmacymanagement.model.entity.MedicamentoEntity;

import br.com.clamed.pharmacymanagement.service.MedicamentoService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/medicamento")
public class MedicamentoController {
    @Autowired
    private MedicamentoService service;
    @PostMapping(produces = "application/json")
    public ResponseEntity<String> cadastrar(@RequestBody MedicamentoRequest request) {
        MedicamentoResponse response = service.save(request);
        return new ResponseEntity<String>("medicamento cadastrado com wsucesso",HttpStatus.CREATED);
    }

    @PutMapping(value="/update")
    public ResponseEntity<String> atualizar(
                 @RequestBody MedicamentoRequest request
    ){
        MedicamentoResponse response = service.update(request);
        return new ResponseEntity<String>("Medicamento atualizado com sucesso", HttpStatus.OK);
    }

    @DeleteMapping(value="/")
    @ResponseBody
    public ResponseEntity<String>deletar(@RequestParam Long id){
        service.delete(id);
        return new ResponseEntity<String>("Medicamento deletado com sucesso", HttpStatus.OK);
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<MedicamentoEntity> encontrarPorId(@PathVariable(value="id") Long id){
        MedicamentoEntity entity = service.encontraById(id);
        return new ResponseEntity<MedicamentoEntity>(entity, HttpStatus.OK);
    }

    @GetMapping(value="/todosMedicamentos", produces = "application/json")
    public ResponseEntity<List<MedicamentoResponse>> encontrarTodosItens() throws NotFoundException {
        List<MedicamentoResponse> response = service.encontraTodosMedicamentos();
        return ResponseEntity.ok(response);
    }
}
