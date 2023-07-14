package med.voll.api.records;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import med.voll.api.entities.Endereco;

public record EnderecoDTO(@NotBlank String logradouro,
                          @NotBlank
                          String bairro,
                          @NotBlank
                          String cidade,
                          @NotBlank @Pattern(regexp = "\\d{8}")
                          String cep,
                          @NotBlank
                          String uf,
                          String complemento,
                          String numero) {
    public EnderecoDTO(Endereco endereco){
        this(endereco.getLogradouro(), endereco.getBairro(), endereco.getCidade(), endereco.getCep(), endereco.getUf(), endereco.getComplemento(), endereco.getNumero());
    }
}
