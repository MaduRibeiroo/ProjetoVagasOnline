package fipp.ariane.vagasonlinebe.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import fipp.ariane.vagasonlinebe.entities.Cargo;
import fipp.ariane.vagasonlinebe.entities.Empresa;
import fipp.ariane.vagasonlinebe.entities.Erro;
import fipp.ariane.vagasonlinebe.entities.Interesse;
import fipp.ariane.vagasonlinebe.entities.Vaga;
import fipp.ariane.vagasonlinebe.services.CargoService;
import fipp.ariane.vagasonlinebe.services.EmpresaService;
import fipp.ariane.vagasonlinebe.services.InteresseService;
import fipp.ariane.vagasonlinebe.services.VagasService;

@CrossOrigin
@RestController
@RequestMapping(value = "apis/")
public class    vagasonlineRestController {

    @Autowired
    VagasService vagasService;

    @Autowired
    EmpresaService empresaService;

    @Autowired
    CargoService cargoService;

    @Autowired
    InteresseService interesseService;

    @GetMapping("vagas/get-all")
    public ResponseEntity<Object> getAllVagas(){
        try{
            List<Vaga> lista = vagasService.getAll();
            return ResponseEntity.ok(lista);
        }catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new Erro(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Erro("Erro ao buscar vagas: " + e.getMessage()));
        }
    }

    @GetMapping("vagas/get-one/{registro}")
    public ResponseEntity<Object> getOneVaga(@PathVariable String registro){
        try{
            Vaga vaga = vagasService.getOne(registro);
            return ResponseEntity.ok(vaga.getRegistro()+" - "+vaga.getCargo());
        }catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new Erro(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Erro("Erro ao buscar vaga: " + e.getMessage()));
        }
    }

    @GetMapping("empresa/get-all")
    public ResponseEntity<Object> getAllEmpresa(){
        try{
            List<Empresa> lista = empresaService.getAll();
            return ResponseEntity.ok(lista);
        }catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new Erro(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Erro("Erro ao buscar empresas: " + e.getMessage()));
        }
    }

    @GetMapping("cargo/get-all")
    public ResponseEntity<Object> getAllCargo(){
        try{
            List<Cargo> lista = cargoService.getAll();
            return ResponseEntity.ok(lista);
        }catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new Erro(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Erro("Erro ao buscar cargos: " + e.getMessage()));
        }
    }

    @DeleteMapping("vagas/{registro}")
    public ResponseEntity<Object> deleteVaga(@PathVariable String registro){
        try{
            boolean deleted = vagasService.delete(registro);
            return ResponseEntity.ok("Vaga deletada com sucesso!");
        }catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new Erro(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Erro("Erro ao excluir vaga: " + e.getMessage()));
        }
    }

    @PostMapping("vagas")
    public ResponseEntity<Object> addVaga(@RequestBody Vaga vaga){
        try {
            Vaga novaVaga = vagasService.add(vaga);
            return ResponseEntity.ok("Vaga adicionada com sucesso! " + novaVaga);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new Erro(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Erro("Erro ao cadastrar vaga: " + e.getMessage()));
        }
    }

    @PostMapping("interesse")
    public ResponseEntity<Object> addInteresse(@RequestBody Interesse interesse){
        try {
            Interesse novoInteresse = interesseService.add(interesse);
            return ResponseEntity.ok("Interesse adicionado com sucesso! " + novoInteresse);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new Erro(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Erro("Erro ao cadastrar interesse: " + e.getMessage()));
        }
    }

    @PutMapping("vagas")
    public ResponseEntity<Object> alterarVaga(@RequestBody Vaga vaga){
        try {
            Vaga novaVaga = vagasService.alterar(vaga.getRegistro(), vaga);
            return ResponseEntity.ok("Vaga alterada com sucesso! " + novaVaga);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new Erro(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Erro("Erro ao alterar vaga: " + e.getMessage()));
        }
    }
}
