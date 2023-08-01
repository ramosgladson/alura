package med.voll.api.domain.services.validacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import med.voll.api.domain.records.ConsultaAddDTO;
import med.voll.api.domain.services.MedicoService;
import med.voll.api.infra.exception.ValidacaoException;

@Service
public class ValidadorMedicoInativo implements ValidadorAgendamentoConsulta {

    @Autowired
    MedicoService medicoService;

    @Override
    public void validar(ConsultaAddDTO consultaAddDTO) {
        if (consultaAddDTO.medicoId() == null) {
            return;
        }
        if (!medicoService.isAtivo(consultaAddDTO.medicoId())) {
            throw new ValidacaoException("Medico inativo");
        }
    }

}
