package med.voll.api.records;

import med.voll.api.entities.Paciente;

public record PacienteListagemDTO(String nome, String email) {

    public PacienteListagemDTO(Paciente paciente){
        this(paciente.getNome(), paciente.getEmail());
    }
}
