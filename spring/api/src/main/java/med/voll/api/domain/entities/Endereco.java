package med.voll.api.domain.entities;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import med.voll.api.domain.records.EnderecoDTO;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Endereco {
    private String logradouro;
    private String bairro;
    private String cep;
    private String numero;
    private String complemento;
    private String cidade;
    private String uf;

    public Endereco(EnderecoDTO dados) {
        this.logradouro = dados.logradouro();
        this.bairro = dados.bairro();
        this.cep = dados.cep();
        this.numero = dados.numero();
        this.complemento = dados.complemento();
        this.cidade = dados.cidade();
        this.uf = dados.uf();
    }

    public void atualizar(EnderecoDTO enderecoDTO) {
        if (enderecoDTO.logradouro() != null) {
            this.logradouro = enderecoDTO.logradouro();
        }
        if (enderecoDTO.bairro() != null) {
            this.bairro = enderecoDTO.bairro();
        }
        if (enderecoDTO.cep() != null) {
            this.cep = enderecoDTO.cep();
        }
        if (enderecoDTO.numero() != null) {
            this.numero = enderecoDTO.numero();
        }
        if (enderecoDTO.complemento() != null) {
            this.complemento = enderecoDTO.complemento();
        }
        if (enderecoDTO.cidade() != null) {
            this.cidade = enderecoDTO.cidade();
        }
        if (enderecoDTO.uf() != null) {
            this.uf = enderecoDTO.uf();
        }
    }
}
