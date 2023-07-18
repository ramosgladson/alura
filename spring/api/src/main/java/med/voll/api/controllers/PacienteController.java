package med.voll.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.validation.Valid;
import med.voll.api.domain.entities.Paciente;
import med.voll.api.domain.records.PacienteAtualizacaoDTO;
import med.voll.api.domain.records.PacienteEntradaDTO;
import med.voll.api.domain.records.PacienteListagemDTO;
import med.voll.api.domain.services.PacienteService;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    PacienteService pacienteService;

    @PostMapping
    public ResponseEntity cadastrar(@RequestBody @Valid PacienteEntradaDTO pacienteEntradaDTO,
            UriComponentsBuilder uriComponentsBuilder) {
        var pacienteDTO = pacienteService.cadastrar(new Paciente(pacienteEntradaDTO));
        var uri = uriComponentsBuilder.path("/pacientes/{id}").buildAndExpand(pacienteDTO.id()).toUri();
        return ResponseEntity.created(uri).body(pacienteDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        return ResponseEntity.ok(pacienteService.detalhar(id));
    }

    @GetMapping
    public ResponseEntity<Page<PacienteListagemDTO>> listar(
            @PageableDefault(page = 0, size = 10, sort = { "nome" }) Pageable page) {
        return ResponseEntity.ok(pacienteService.listar(page));
    }

    @PutMapping
    public ResponseEntity atualizar(@RequestBody @Valid PacienteAtualizacaoDTO pacienteAtualizarDto) {
        var pacienteDTO = pacienteService.autualizar(pacienteAtualizarDto);
        return ResponseEntity.ok().body(pacienteDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable Long id) {
        pacienteService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
