package med.voll.api.domain.entities;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import med.voll.api.domain.enums.Especialidade;
import med.voll.api.domain.records.MedicoAtualizacaoDTO;
import med.voll.api.domain.records.MedicoCadastroDTO;

@Table(name = "tb_medicos")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;
    @Embedded
    private Endereco endereco;
    private Boolean ativo;

    public Medico(MedicoCadastroDTO dados) {
        this.ativo = true;
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.crm = dados.crm();
        this.especialidade = dados.especialidade();
        this.endereco = new Endereco(dados.endereco());
    }

    public Medico(MedicoAtualizacaoDTO dados) {
        this.nome = dados.nome();
        this.email = dados.telefone();
        this.endereco = new Endereco(dados.endereco());
    }

    public void atualizar(MedicoAtualizacaoDTO medicoAtualizacaoDTO) {
        if (medicoAtualizacaoDTO.nome() != null) {
            this.nome = medicoAtualizacaoDTO.nome();
        }
        if (medicoAtualizacaoDTO.telefone() != null) {
            this.telefone = medicoAtualizacaoDTO.telefone();
        }
        if (medicoAtualizacaoDTO.endereco() != null) {
            this.endereco.atualizar(medicoAtualizacaoDTO.endereco());
        }

    }

}
