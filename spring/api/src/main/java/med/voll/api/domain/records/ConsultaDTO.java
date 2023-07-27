package med.voll.api.domain.records;

import java.time.LocalDateTime;

import med.voll.api.domain.entities.Consulta;

public record ConsultaDTO(Long id, Long medicoId, Long pacienteId, LocalDateTime data) {

    public ConsultaDTO(Consulta consulta) {
        this(consulta.getId(), consulta.getMedico().getId(), consulta.getPaciente().getId(), consulta.getSchedule());
    }

}
