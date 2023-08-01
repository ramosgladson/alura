package med.voll.api.domain.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;

import med.voll.api.domain.entities.Consulta;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    boolean existsByPacienteIdAndScheduleBetween(Long idPaciente, LocalDateTime primeiroHorario,
            LocalDateTime ultimoHorario);

    boolean existsByMedicoIdAndSchedule(Long idMedico, LocalDateTime data);

    // boolean existsByMedicoIdAndDataAndMotivoCancelamentoIsNull(Long idMedico,
    // LocalDateTime data);
}
