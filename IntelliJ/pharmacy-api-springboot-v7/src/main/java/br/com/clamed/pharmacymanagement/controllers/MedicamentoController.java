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
    public ResponseEntity<Object> cadastrar(@RequestBody MedicamentoRequest request) {
        MedicamentoResponse response = service.save(request);
        return ResponseEntity.ok(request);
    }

    @PutMapping(value="/update/{id}")
    public ResponseEntity<String> atualizar(
            @PathVariable Long id,
            @RequestBody MedicamentoRequest request
    ){
        MedicamentoResponse response = service.update(id,request);
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
