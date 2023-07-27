package med.voll.api.domain.records;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.enums.Especialidade;

public record ConsultaAddDTO(Long medicoId,
        @NotNull(message = "id do paciente é obrigatório") Long pacienteId,
        @NotNull @Future(message = "Inserir uma data futura") LocalDateTime data, Especialidade especialidade) {

}
