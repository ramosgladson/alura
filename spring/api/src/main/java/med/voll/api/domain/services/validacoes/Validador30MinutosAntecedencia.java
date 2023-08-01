package med.voll.api.domain.services.validacoes;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import med.voll.api.domain.records.ConsultaAddDTO;
import med.voll.api.infra.exception.ValidacaoException;

@Service
public class Validador30MinutosAntecedencia implements ValidadorAgendamentoConsulta {

    @Override
    public void validar(ConsultaAddDTO consultaAddDTO) {

        if (Duration.between(LocalDateTime.now(), consultaAddDTO.data()).toMinutes() < 30) {
            throw new ValidacaoException("Agendamento com antecedencia mínima de 30 minutos");
        }
    }

}
