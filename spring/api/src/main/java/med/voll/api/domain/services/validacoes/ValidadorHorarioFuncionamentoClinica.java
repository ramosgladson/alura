package med.voll.api.domain.services.validacoes;

import java.time.DayOfWeek;

import org.springframework.stereotype.Service;

import med.voll.api.domain.records.ConsultaAddDTO;
import med.voll.api.infra.exception.ValidacaoException;

@Service
public class ValidadorHorarioFuncionamentoClinica implements ValidadorAgendamentoConsulta {

    @Override
    public void validar(ConsultaAddDTO consultaAddDTO) {
        var dataConsulta = consultaAddDTO.data();

        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesAbertura = dataConsulta.getHour() < 7;
        var depoisUltimoHorario = dataConsulta.getHour() > 18;

        if (domingo || antesAbertura || depoisUltimoHorario) {
            throw new ValidacaoException("Funcionamento da clinica de segunda a sabado das 7 as 19hrs");
        }

    }

}
