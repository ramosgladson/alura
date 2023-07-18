package med.voll.api.domain.records;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.enums.Especialidade;


public record MedicoCadastroDTO(@NotBlank String nome,
                                @Email String email,
                                @NotBlank String telefone,
                                @NotBlank @Pattern(regexp = "\\d{4,6}") String crm,
                                @NotNull Especialidade especialidade,
                                @NotNull @Valid EnderecoDTO endereco) {
}
