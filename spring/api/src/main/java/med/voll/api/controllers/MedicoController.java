package med.voll.api.controllers;

import jakarta.validation.Valid;
import med.voll.api.entities.Medico;
import med.voll.api.records.MedicoAtualizacaoDTO;
import med.voll.api.records.MedicoCadastroDTO;
import med.voll.api.records.MedicoListagemDTO;
import med.voll.api.services.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    MedicoService medicoService;

    @PostMapping
    public ResponseEntity cadastrar(@RequestBody @Valid MedicoCadastroDTO medicoCadastroDTO, UriComponentsBuilder uriBuilder){
        var medicoDTO = medicoService.cadastrar(new Medico(medicoCadastroDTO));
        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medicoDTO.id()).toUri();
        return ResponseEntity.created(uri).body(medicoDTO);
    }

    @GetMapping
    public ResponseEntity<Page<MedicoListagemDTO>> listar(@PageableDefault(size = 10, page = 0, sort = {"nome", "crm"}) Pageable page){
        return ResponseEntity.ok(medicoService.listar(page));
    }

    @PutMapping
    public ResponseEntity atualizar(@RequestBody @Valid MedicoAtualizacaoDTO medicoAtualizacaoDTO){
        var medicoDTO = medicoService.atualizar(medicoAtualizacaoDTO);
        return ResponseEntity.ok(medicoDTO);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable Long id){
        medicoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id){
        var medicoDTO = medicoService.detalhar(id);
        return ResponseEntity.ok(medicoDTO);
    }

}
