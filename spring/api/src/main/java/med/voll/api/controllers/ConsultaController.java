package med.voll.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.api.domain.records.ConsultaAddDTO;
import med.voll.api.domain.records.ConsultaCancelaDTO;
import med.voll.api.domain.services.ConsultaService;

@RestController
@RequestMapping("/consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {

    @Autowired
    ConsultaService consultaService;

    @PostMapping
    public ResponseEntity agendar(@RequestBody @Valid ConsultaAddDTO consultaAddDTO) {
        return ResponseEntity.ok(consultaService.add(consultaAddDTO));
    }

    @DeleteMapping()
    public ResponseEntity cancelar(@RequestBody @Valid ConsultaCancelaDTO consultaCancelaDTO) {
        consultaService.deletar(consultaCancelaDTO);
        return ResponseEntity.noContent().build();
    }

}
