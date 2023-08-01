package med.voll.api.domain.services.validacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import med.voll.api.domain.records.ConsultaAddDTO;
import med.voll.api.domain.services.PacienteService;
import med.voll.api.infra.exception.ValidacaoException;

@Service
public class ValidadorPacientesInativos implements ValidadorAgendamentoConsulta {

    @Autowired
    PacienteService pacienteService;

    @Override
    public void validar(ConsultaAddDTO consultaAddDTO) {
        if (!pacienteService.isAtivo(consultaAddDTO.pacienteId())) {
            throw new ValidacaoException("Paciente inativo");
        }
    }

}
