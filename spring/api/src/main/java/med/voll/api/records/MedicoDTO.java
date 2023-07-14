package med.voll.api.records;

import med.voll.api.entities.Medico;
import med.voll.api.enums.Especialidade;

public record MedicoDTO(Long id, String nome, String email, String telefone, String crm, Especialidade especialidade, EnderecoDTO endereco) {
    public MedicoDTO(Medico medico){
        this(medico.getId(),medico.getNome(), medico.getEmail(), medico.getTelefone(), medico.getCrm(), medico.getEspecialidade(), new EnderecoDTO(medico.getEndereco()));
    }
}
