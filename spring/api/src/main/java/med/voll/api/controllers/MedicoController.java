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

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.api.domain.records.MedicoAtualizacaoDTO;
import med.voll.api.domain.records.MedicoCadastroDTO;
import med.voll.api.domain.records.MedicoListagemDTO;
import med.voll.api.domain.services.MedicoService;

@RestController
@RequestMapping("/medicos")
@SecurityRequirement(name = "bearer-key")
public class MedicoController {

    @Autowired
    MedicoService medicoService;

    @PostMapping
    public ResponseEntity cadastrar(@RequestBody @Valid MedicoCadastroDTO medicoCadastroDTO,
            UriComponentsBuilder uriBuilder) {
        var medicoDTO = medicoService.cadastrar(medicoCadastroDTO);
        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medicoDTO.id()).toUri();
        return ResponseEntity.created(uri).body(medicoDTO);
    }

    @GetMapping
    public ResponseEntity<Page<MedicoListagemDTO>> listar(
            @PageableDefault(size = 10, page = 0, sort = { "nome", "crm" }) Pageable page) {
        return ResponseEntity.ok(medicoService.listar(page));
    }

    @PutMapping
    public ResponseEntity atualizar(@RequestBody @Valid MedicoAtualizacaoDTO medicoAtualizacaoDTO) {
        var medicoDTO = medicoService.atualizar(medicoAtualizacaoDTO);
        System.out.println(medicoAtualizacaoDTO);
        return ResponseEntity.ok(medicoDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable Long id) {
        medicoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var medicoDTO = medicoService.detalhar(id);
        return ResponseEntity.ok(medicoDTO);
    }

}
