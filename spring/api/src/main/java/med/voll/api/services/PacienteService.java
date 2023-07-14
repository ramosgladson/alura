package med.voll.api.services;

import med.voll.api.entities.Paciente;
import med.voll.api.records.PacienteDTO;
import med.voll.api.records.PacienteListagemDTO;
import med.voll.api.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PacienteService {
    @Autowired
    PacienteRepository pacienteRepository;

    public Paciente cadastrar(Paciente paciente){
        pacienteRepository.save(paciente);
        return paciente;
    }
    public Page<Paciente> listar(Pageable page){
        return pacienteRepository.findAll(page);
    }

    public PacienteDTO detalhar(Long id) {
        return new PacienteDTO(pacienteRepository.getReferenceById(id));
    }
}
