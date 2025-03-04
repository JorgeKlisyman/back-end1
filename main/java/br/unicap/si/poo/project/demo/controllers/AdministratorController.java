package br.unicap.si.poo.project.demo.controllers;

import br.unicap.si.poo.project.demo.models.Administrator;
import br.unicap.si.poo.project.demo.services.AdministratorService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/administrators") // caminho para acessar o controller e as suas funções relacionadas à entidade
@CrossOrigin(origins = "*")
public class AdministratorController {

    private final AdministratorService administratorService;

    // método para adicionar um novo adm que será chamado no service
    @PostMapping
    public ResponseEntity<Administrator> create(@Validated @RequestBody Administrator administrator) {
        Administrator administrador = administratorService.save(administrator);
        return ResponseEntity.status(HttpStatus.CREATED).body(administrador);
    }

    // método para buscar um adm no banco de dados pelo id
    @GetMapping("/{id}")
    public ResponseEntity<Administrator> getById(@PathVariable Long id) {
        Administrator administrador = administratorService.searchById(id);
        return administrador != null 
            ? ResponseEntity.ok(administrador) 
            : ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    // método para alterar a senha de um adm
    @PatchMapping("/{id}/updateadmpassword")
    public ResponseEntity<Administrator> updatePassword(@PathVariable Long id, 
                                                       @RequestBody Administrator administrator) {
        Administrator administrador = administratorService.updatePassword(id, administrator.getPasswordAdministrator());
        return administrador != null 
            ? ResponseEntity.ok(administrador) 
            : ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    private void getPasswordAdministrator() {
        // TODO
    }

    // método para alterar o email de um adm
    @PatchMapping("/{id}/updateadmemail")
    public ResponseEntity<Administrator> updateEmail(@PathVariable Long id, @RequestBody Administrator administrator) {
        Administrator administrador = administratorService.updateEmail(id, administrator.getEmailAdministrator());
        return administrador != null 
            ? ResponseEntity.ok(administrador) 
            : ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    // método para alterar o nome de um adm
    @PatchMapping("/{id}/updateadmusername")
    public ResponseEntity<Administrator> updateName(@PathVariable Long id, @RequestBody Administrator administrator) {
        Administrator administrador = administratorService.updateName(id, administrator.getNameAdministrator());
        return administrador != null 
            ? ResponseEntity.ok(administrador) 
            : ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    // método para listar todos os adms cadastrados
    @GetMapping
    public ResponseEntity<List<Administrator>> getAll() {
        List<Administrator> administradores = administratorService.searchAll();
        return ResponseEntity.ok(administradores);
    }

    // método para deletar um adm
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAdmin(@PathVariable Long id) {
        boolean deleted = administratorService.deleteAdmin(id);
        if (deleted) {
            return ResponseEntity.status(HttpStatus.OK).body("O administrador foi deletado com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Administrador não encontrado.");
        }
    }
}
