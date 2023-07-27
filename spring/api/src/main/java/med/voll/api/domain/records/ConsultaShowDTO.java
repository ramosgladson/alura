package med.voll.api.domain.records;

import java.time.LocalDateTime;

public record ConsultaShowDTO(Long medicoId, Long pacienteId, LocalDateTime data) {

    public ConsultaShowDTO(ConsultaAddDTO consultaCadastroDTO) {
        this(consultaCadastroDTO.medicoId(), consultaCadastroDTO.pacienteId(), consultaCadastroDTO.data());
    }

}
