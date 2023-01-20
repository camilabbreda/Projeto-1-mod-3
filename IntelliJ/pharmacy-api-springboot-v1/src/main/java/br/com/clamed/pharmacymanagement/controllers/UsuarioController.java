package br.com.clamed.pharmacymanagement.controllers;

import br.com.clamed.pharmacymanagement.model.entity.UsuarioEntity;
import br.com.clamed.pharmacymanagement.repository.UsuarioRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Email;
import java.util.List;

@RestController
@RequestMapping(value="/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @PostMapping(produces = "application/json")
    public ResponseEntity<UsuarioEntity> cadastrar(@RequestBody UsuarioEntity usuarioSalvo){
       UsuarioEntity  usuario = usuarioRepository.save(usuarioSalvo);
    return new ResponseEntity<UsuarioEntity>(usuario, HttpStatus.CREATED);
   }
   @PutMapping(produces = "application/json")
   public ResponseEntity<UsuarioEntity> atualizar(@RequestBody UsuarioEntity usuarioAtualizado){
        UsuarioEntity usuario = usuarioRepository.save(usuarioAtualizado);
        return new ResponseEntity<UsuarioEntity>(usuario,HttpStatus.OK);
   }
    @DeleteMapping(value="/")
    @ResponseBody
    public ResponseEntity<String>deletar(@RequestParam Long idUsuario){
        usuarioRepository.deleteById(idUsuario);
        return new ResponseEntity<String>("Usuario deletado com sucesso", HttpStatus.OK);
    }
    @GetMapping(value="/{id}")
    public ResponseEntity<UsuarioEntity> encontrarUsuarioPelaId(@PathVariable(value="id") Long id){
        UsuarioEntity usuario = usuarioRepository.findById(id).get();
        return new ResponseEntity<UsuarioEntity>(usuario, HttpStatus.OK);
    }
    @GetMapping(value="/buscarEmail", produces = "application/json")
    public ResponseEntity<List<UsuarioEntity>> getUsuarioByEmail(@RequestParam(name="email") String filter){
        List<UsuarioEntity> usuario = usuarioRepository.getUsuarioEntityByEmail(filter);
        return new ResponseEntity<List<UsuarioEntity>>(usuario, HttpStatus.OK);
    }

    @GetMapping(value="/todosUsuarios", produces = "application/json")
    public ResponseEntity<List<UsuarioEntity>> getUsuarios(){
        List<UsuarioEntity> usuarios = usuarioRepository.findAll();
        return new ResponseEntity<List<UsuarioEntity>>(usuarios, HttpStatus.OK);
    }
}
