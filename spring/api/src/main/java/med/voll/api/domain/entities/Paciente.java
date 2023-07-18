package med.voll.api.domain.entities;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import med.voll.api.domain.records.PacienteAtualizacaoDTO;
import med.voll.api.domain.records.PacienteEntradaDTO;

@Table(name = "tb_pacientes")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    private String nome;
    private String email;
    private String cpf;
    private String telefone;
    private Boolean ativo;
    @Embedded
    private Endereco endereco;

    public Paciente(PacienteEntradaDTO dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.cpf = dados.cpf();
        this.telefone = dados.telefone();
        this.endereco = new Endereco(dados.endereco());
        this.ativo = true;
    }

    public Paciente(PacienteAtualizacaoDTO pacienteAtualizarDto) {
        this.id = pacienteAtualizarDto.id();
        this.nome = pacienteAtualizarDto.nome();
        this.telefone = pacienteAtualizarDto.telefone();
        this.endereco = new Endereco(pacienteAtualizarDto.endereco());
    }

    public void atualizar(PacienteAtualizacaoDTO pacienteAtualizarDTO) {
        if (pacienteAtualizarDTO.nome() != null) {
            this.nome = pacienteAtualizarDTO.nome();
        }
        if (pacienteAtualizarDTO.telefone() != null) {
            this.telefone = pacienteAtualizarDTO.telefone();
        }
        if (pacienteAtualizarDTO.endereco() != null) {
            this.endereco.atualizar(pacienteAtualizarDTO.endereco());
        }
    }
}
