package med.voll.api.controllers;

import jakarta.validation.Valid;
import med.voll.api.entities.Paciente;
import med.voll.api.records.PacienteEntradaDTO;
import med.voll.api.records.PacienteListagemDTO;
import med.voll.api.services.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    PacienteService pacienteService;
    @PostMapping
    public ResponseEntity cadastrar(@RequestBody @Valid PacienteEntradaDTO pacienteEntradaDTO, UriComponentsBuilder uriComponentsBuilder) {
        var paciente = pacienteService.cadastrar(new Paciente(pacienteEntradaDTO));
        var uri = uriComponentsBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
        return ResponseEntity.created(uri).body(paciente);
    }
    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id){
        return ResponseEntity.ok(pacienteService.detalhar(id));
    }
    @GetMapping
    public Page<PacienteListagemDTO> listar(@PageableDefault(page = 0, size = 10, sort = {"nome"}) Pageable page){
        return pacienteService.listar(page).map(PacienteListagemDTO::new);
    }
}
