package med.voll.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import med.voll.api.entities.Paciente;
import med.voll.api.records.PacienteAtualizacaoDTO;
import med.voll.api.records.PacienteDTO;
import med.voll.api.records.PacienteListagemDTO;
import med.voll.api.repository.PacienteRepository;

@Service
public class PacienteService {
    @Autowired
    PacienteRepository pacienteRepository;

    @Transactional
    public PacienteDTO cadastrar(Paciente paciente) {
        pacienteRepository.save(paciente);
        return new PacienteDTO(paciente);
    }

    @Transactional
    public Page<PacienteListagemDTO> listar(Pageable page) {
        return pacienteRepository.findByAtivoTrue(page).map(PacienteListagemDTO::new);
    }

    @Transactional
    public PacienteDTO detalhar(Long id) {
        return new PacienteDTO(pacienteRepository.getReferenceById(id));
    }

    @Transactional
    public PacienteDTO autualizar(PacienteAtualizacaoDTO pacienteAtualizarDTO) {
        var paciente = pacienteRepository.getReferenceById(pacienteAtualizarDTO.id());
        paciente.atualizar(pacienteAtualizarDTO);
        return new PacienteDTO(paciente);
    }

    @Transactional
    public void deletar(Long id) {
        var paciente = pacienteRepository.getReferenceById(id);
        paciente.setAtivo(false);
    }

}
