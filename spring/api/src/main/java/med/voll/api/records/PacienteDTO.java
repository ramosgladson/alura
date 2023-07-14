package med.voll.api.records;

import med.voll.api.entities.Paciente;

public record PacienteDTO(Long id, String nome, String email, String telefone, String cpf, EnderecoDTO endereco) {
    public PacienteDTO(Paciente paciente){
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getTelefone(), paciente.getCpf(), new EnderecoDTO(paciente.getEndereco()));
    }
}
