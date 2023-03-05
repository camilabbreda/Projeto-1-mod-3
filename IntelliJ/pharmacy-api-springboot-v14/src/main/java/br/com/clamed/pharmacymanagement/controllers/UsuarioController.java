package br.com.clamed.pharmacymanagement.controllers;

import br.com.clamed.pharmacymanagement.controllers.dto.UsuarioRequest;
import br.com.clamed.pharmacymanagement.controllers.dto.UsuarioResponse;
import br.com.clamed.pharmacymanagement.model.entity.UsuarioEntity;
import br.com.clamed.pharmacymanagement.padronizacaoErro.DefaultResponse;
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
    private UsuarioService service;
    @PostMapping(value ="/cadastro", produces = "application/json")
    public ResponseEntity<DefaultResponse> cadastrar(@RequestBody UsuarioRequest usuarioRequest) {
        UsuarioResponse usuarioResponse = service.save(usuarioRequest);
        return new ResponseEntity<>((
                new DefaultResponse<UsuarioResponse>(
                        HttpStatus.CREATED.value(),
                        usuarioResponse
                )),
                HttpStatus.CREATED
                );
    }

   @PutMapping(value ="/cadastro", produces = "application/json")
   public ResponseEntity<DefaultResponse> atualizar(@RequestBody UsuarioRequest usuarioRequest){
       UsuarioResponse usuarioResponse = service.update(usuarioRequest);
       return new ResponseEntity<>((
               new DefaultResponse<UsuarioResponse>(
                       HttpStatus.OK.value(),
                       usuarioResponse
               )),
               HttpStatus.OK
       );
   }

    @DeleteMapping(value="/")
    @ResponseBody
    public ResponseEntity<DefaultResponse>deletar(@RequestParam Long id){
        service.delete(id);
        return new ResponseEntity<>((
                new DefaultResponse<UsuarioResponse>(
                        HttpStatus.OK.value(),
                        "Usuário deletado"
                )),
                HttpStatus.OK
        );
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<DefaultResponse> encontrarUsuarioPelaId(@PathVariable(value="id") Long id) throws NotFoundException {
        UsuarioEntity usuario = service.encontraById(id);
        return new ResponseEntity<>((
                new DefaultResponse<UsuarioEntity>(
                        HttpStatus.OK.value(),
                        usuario
                )),
                HttpStatus.OK
        );
    }

    @GetMapping(value="/username", produces = "application/json")
    public ResponseEntity<DefaultResponse> getUsuarios() throws NotFoundException {
        List<UsuarioResponse> usuarios = service.encontraTodosUsuarios();
        return new ResponseEntity<>((
                new DefaultResponse<List>(
                        HttpStatus.OK.value(),
                        usuarios
                )),
                HttpStatus.OK
        );
    }
}
