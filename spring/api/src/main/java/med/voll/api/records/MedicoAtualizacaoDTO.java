package med.voll.api.records;

import jakarta.validation.constraints.NotNull;

public record MedicoAtualizacaoDTO(@NotNull Long id, String nome, String telefone, EnderecoDTO endereco) {
}
