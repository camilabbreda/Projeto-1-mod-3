package br.com.clamed.pharmacymanagement.controllers;

import br.com.clamed.pharmacymanagement.controllers.dto.UsuarioRequest;
import br.com.clamed.pharmacymanagement.controllers.dto.UsuarioResponse;
import br.com.clamed.pharmacymanagement.model.entity.UsuarioEntity;
import br.com.clamed.pharmacymanagement.model.repository.UsuarioRepository;
import br.com.clamed.pharmacymanagement.service.UsuarioService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private UsuarioService service;
    @PostMapping(produces = "application/json")
    public ResponseEntity<Object> cadastrar(@RequestBody UsuarioRequest usuarioRequest) {
        UsuarioResponse usuarioResponse = service.save(usuarioRequest);
        return ResponseEntity.ok(usuarioResponse);
    }

   @PutMapping(produces = "application/json")
   public ResponseEntity<String> atualizar(@RequestBody UsuarioRequest usuarioRequest){
       UsuarioResponse usuarioResponse = service.update(usuarioRequest);
        return new ResponseEntity<String>("Usuário atualizado com sucesso",HttpStatus.OK);
   }

    @DeleteMapping(value="/")
    @ResponseBody
    public ResponseEntity<String>deletar(@RequestParam Long id){
        service.delete(id);
        return new ResponseEntity<String>("Usuário deletado com sucesso", HttpStatus.OK);
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<UsuarioEntity> encontrarUsuarioPelaId(@PathVariable(value="id") Long id){
        UsuarioEntity usuario = service.encontraById(id);
        return new ResponseEntity<UsuarioEntity>(usuario, HttpStatus.OK);
    }
    @GetMapping(value="/buscarEmail", produces = "application/json")
    public ResponseEntity<Object> getUsuarioByEmail(@RequestParam(name="email") String email) throws NotFoundException {
        List<UsuarioResponse> usuarios = service.encontraByEmail(email);
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping(value="/todosUsuarios", produces = "application/json")
    public ResponseEntity<List<UsuarioResponse>> getUsuarios() throws NotFoundException {
        List<UsuarioResponse> usuarios = service.encontraTodosUsuarios();
        return ResponseEntity.ok(usuarios);
    }
}
