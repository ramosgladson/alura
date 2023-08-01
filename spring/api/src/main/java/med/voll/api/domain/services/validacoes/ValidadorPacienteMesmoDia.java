package med.voll.api.domain.services.validacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import med.voll.api.domain.records.ConsultaAddDTO;
import med.voll.api.domain.repository.ConsultaRepository;
import med.voll.api.infra.exception.ValidacaoException;

@Service
public class ValidadorPacienteMesmoDia implements ValidadorAgendamentoConsulta {

    @Autowired
    private ConsultaRepository repository;

    public void validar(ConsultaAddDTO consultaAddDTO) {
        var primeiroHorario = consultaAddDTO.data().withHour(7);
        var ultimoHorario = consultaAddDTO.data().withHour(18);
        var pacientePossuiOutraConsultaNoDia = repository.existsByPacienteIdAndScheduleBetween(
                consultaAddDTO.pacienteId(),
                primeiroHorario, ultimoHorario);
        if (pacientePossuiOutraConsultaNoDia) {
            throw new ValidacaoException("Paciente já possui uma consulta agendada nesse dia");
        }
    }

}
