package med.voll.api.domain.services.validacoes;

import med.voll.api.domain.records.ConsultaAddDTO;

public interface ValidadorAgendamentoConsulta {
    void validar(ConsultaAddDTO consultaAddDTO);
}
