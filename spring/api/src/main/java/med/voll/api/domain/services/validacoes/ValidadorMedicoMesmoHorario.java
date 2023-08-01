package med.voll.api.domain.services.validacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import med.voll.api.domain.records.ConsultaAddDTO;
import med.voll.api.domain.repository.ConsultaRepository;
import med.voll.api.infra.exception.ValidacaoException;

@Service
public class ValidadorMedicoMesmoHorario implements ValidadorAgendamentoConsulta {

    @Autowired
    ConsultaRepository consultaRepository;

    @Override
    public void validar(ConsultaAddDTO consultaAddDTO) {
        if (consultaRepository.existsByMedicoIdAndSchedule(consultaAddDTO.medicoId(), consultaAddDTO.data())) {
            throw new ValidacaoException("Já existe agendamento para esse médico nesse horário");
        }
    }
}
