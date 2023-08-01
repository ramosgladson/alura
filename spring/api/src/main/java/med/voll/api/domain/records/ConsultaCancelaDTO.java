package med.voll.api.domain.records;

import jakarta.validation.constraints.NotBlank;

public record ConsultaCancelaDTO(Long id,
        @NotBlank(message = "Motivo do cancelamento obrigatório!!") String motivoCancelamento) {

}
