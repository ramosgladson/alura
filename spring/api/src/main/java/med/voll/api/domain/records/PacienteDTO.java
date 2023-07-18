package med.voll.api.domain.records;

import med.voll.api.domain.entities.Paciente;

public record PacienteDTO(Long id, String nome, String email, String telefone, String cpf, EnderecoDTO endereco) {
    public PacienteDTO(Paciente paciente){
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getTelefone(), paciente.getCpf(), new EnderecoDTO(paciente.getEndereco()));
    }
}
