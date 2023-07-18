package med.voll.api.domain.records;

import med.voll.api.domain.entities.Paciente;

public record PacienteListagemDTO(Long id, String nome, String email) {

    public PacienteListagemDTO(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail());
    }
}
