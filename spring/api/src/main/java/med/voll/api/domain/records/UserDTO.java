package med.voll.api.domain.records;

import jakarta.validation.constraints.NotBlank;

public record UserDTO(@NotBlank String login, @NotBlank String senha) {
}
