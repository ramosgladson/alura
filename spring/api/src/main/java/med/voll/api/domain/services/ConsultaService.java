package med.voll.api.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import med.voll.api.domain.entities.Consulta;
import med.voll.api.domain.entities.Medico;
import med.voll.api.domain.records.ConsultaAddDTO;
import med.voll.api.domain.records.ConsultaCancelaDTO;
import med.voll.api.domain.records.ConsultaDTO;
import med.voll.api.domain.repository.ConsultaRepository;
import med.voll.api.domain.repository.MedicoRepository;
import med.voll.api.domain.repository.PacienteRepository;

@Service
public class ConsultaService {

    @Autowired
    ConsultaRepository consultaRepository;

    @Autowired
    MedicoRepository medicoRepository;

    @Autowired
    PacienteRepository pacienteRepository;

    public ConsultaDTO add(ConsultaAddDTO consultaAddDTO) {

        Medico medico = new Medico();

        if (!pacienteRepository.existsById(consultaAddDTO.pacienteId())) {
            throw new EntityNotFoundException("Paciente informado não existe");
        }

        if (consultaAddDTO.medicoId() == null || !medicoRepository.existsById(consultaAddDTO.medicoId())) {
            medico = Random(consultaAddDTO);
        } else {
            medico = medicoRepository.getReferenceById(consultaAddDTO.medicoId());
        }

        var consulta = new Consulta(null, medico,
                pacienteRepository.getReferenceById(consultaAddDTO.pacienteId()), consultaAddDTO.data(), true, null);
        return new ConsultaDTO(consultaRepository.save(consulta));

    }

    private Medico Random(ConsultaAddDTO consultaAddDTO) {
        if (consultaAddDTO.especialidade() == null) {
            throw new EntityNotFoundException("Especialidade não informada");
        }
        return medicoRepository.getRandomMedico(consultaAddDTO.especialidade(),
                consultaAddDTO.data());

    }

    @Transactional
    public void deletar(ConsultaCancelaDTO consultaCancelaDTO) {
        var consulta = consultaRepository.findById(consultaCancelaDTO.id()).get();
        if (consulta == null || !consulta.isAtivo()) {
            throw new EntityNotFoundException("Consulta nao encontrada");

        }
        if (consultaCancelaDTO.motivoCancelamento() == null) {
            throw new EntityNotFoundException("Motivo deve ser informado");
        } else {
            consulta.setAtivo(false);
            consulta.setMotivoCancelamento(consultaCancelaDTO.motivoCancelamento());
        }

    }

}
