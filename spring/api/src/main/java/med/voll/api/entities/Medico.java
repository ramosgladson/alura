package med.voll.api.entities;

import jakarta.persistence.*;
import lombok.*;
import med.voll.api.enums.Especialidade;
import med.voll.api.records.MedicoAtualizacaoDTO;
import med.voll.api.records.MedicoCadastroDTO;
import med.voll.api.records.MedicoDTO;

import java.io.Serializable;

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
        this.nome=dados.nome();
        this.email=dados.email();
        this.telefone=dados.telefone();
        this.crm=dados.crm();
        this.especialidade = dados.especialidade();
        this.endereco=new Endereco(dados.endereco());
    }

    public Medico(MedicoAtualizacaoDTO dados){
        this.nome=dados.nome();
        this.email=dados.telefone();
        this.endereco=new Endereco(dados.enderecoDTO());
    }

    public void atualizar(MedicoAtualizacaoDTO medicoAtualizacaoDTO) {
        if (medicoAtualizacaoDTO.nome() != null){
            this.nome = medicoAtualizacaoDTO.nome();
        }
        if (medicoAtualizacaoDTO.telefone() != null){
            this.telefone = medicoAtualizacaoDTO.telefone();
        }
        if (medicoAtualizacaoDTO.enderecoDTO() != null){
            this.endereco.atualizar(medicoAtualizacaoDTO.enderecoDTO());
        }


    }

}
