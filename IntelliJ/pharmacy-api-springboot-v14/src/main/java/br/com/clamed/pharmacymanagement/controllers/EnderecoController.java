package br.com.clamed.pharmacymanagement.controllers;

import br.com.clamed.pharmacymanagement.controllers.dto.EnderecoRequest;
import br.com.clamed.pharmacymanagement.controllers.dto.EnderecoResponse;
import br.com.clamed.pharmacymanagement.model.entity.EnderecoEntity;
import br.com.clamed.pharmacymanagement.service.EnderecoService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/endereco")
public class EnderecoController {
    @Autowired
    private EnderecoService service;
    @PostMapping(produces = "application/json")
    public ResponseEntity<String> cadastrar(@RequestBody EnderecoRequest request) {
        EnderecoResponse response = service.save(request);
    return new ResponseEntity<String>("Endereço atualizado com sucesso", HttpStatus.CREATED);
    }

    @PutMapping(value="/update")
    public ResponseEntity<String> atualizar(
            @RequestBody EnderecoRequest request
    ){
        EnderecoResponse enderecoResponse = service.update(request);
        return new ResponseEntity<String>("Endereço atualizado com sucesso", HttpStatus.OK);
    }

    @DeleteMapping(value="/")
    @ResponseBody
    public ResponseEntity<String>deletar(@RequestParam Long id){
        service.delete(id);
        return new ResponseEntity<String>("Endereço deletado com sucesso", HttpStatus.OK);
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<EnderecoEntity> encontrarPorId(@PathVariable(value="id") Long id){
        EnderecoEntity entity = service.encontraById(id);
        return new ResponseEntity<EnderecoEntity>(entity, HttpStatus.OK);
    }

    @GetMapping(value="/todosEnderecos", produces = "application/json")
    public ResponseEntity<List<EnderecoResponse>> encontrarTodosItens() throws NotFoundException {
        List<EnderecoResponse> response = service.encontraTodosEnderecos();
        return ResponseEntity.ok(response);
    }
}
