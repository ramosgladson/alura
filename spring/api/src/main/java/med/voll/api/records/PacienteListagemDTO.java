package med.voll.api.records;

import med.voll.api.entities.Paciente;

public record PacienteListagemDTO(Long id, String nome, String email) {

    public PacienteListagemDTO(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail());
    }
}
