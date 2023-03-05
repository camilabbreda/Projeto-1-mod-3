package br.com.clamed.pharmacymanagement.controllers;


import br.com.clamed.pharmacymanagement.controllers.dto.EnderecoResponse;
import br.com.clamed.pharmacymanagement.controllers.dto.FarmaciaRequest;
import br.com.clamed.pharmacymanagement.controllers.dto.FarmaciaResponse;
import br.com.clamed.pharmacymanagement.model.entity.EnderecoEntity;

import br.com.clamed.pharmacymanagement.model.entity.FarmaciaEntity;
import br.com.clamed.pharmacymanagement.service.FarmaciaService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/farmacia")
public class FarmaciaController {
    @Autowired
    private FarmaciaService service;
    @PostMapping(produces = "application/json")
    public ResponseEntity<String> cadastrar(@RequestBody FarmaciaRequest request) {
        FarmaciaResponse response = service.save(request);
        return new ResponseEntity<String>("Farmácia cadastrada com sucesso!", HttpStatus.CREATED);
    }

    @PutMapping(value="/update")
    public ResponseEntity<String> atualizar(
            @RequestBody FarmaciaRequest request
    ){
        FarmaciaResponse response = service.update(request);
        return new ResponseEntity<String>("Farmácia atualizada com sucesso", HttpStatus.OK);
    }

    @DeleteMapping(value="/")
    @ResponseBody
    public ResponseEntity<String>deletar(@RequestParam Long id){
        service.delete(id);
        return new ResponseEntity<String>("Farmácia deletada com sucesso", HttpStatus.OK);
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<FarmaciaEntity> encontrarPorId(@PathVariable(value="id") Long id){
        FarmaciaEntity entity = service.encontraById(id);
        return new ResponseEntity<FarmaciaEntity>(entity, HttpStatus.OK);
    }

    @GetMapping(value="/todasFarmacias", produces = "application/json")
    public ResponseEntity<List<FarmaciaResponse>> encontrarTodosItens() throws NotFoundException {
        List<FarmaciaResponse> response = service.encontraTodosFarmacias();
        return ResponseEntity.ok(response);
    }
}
