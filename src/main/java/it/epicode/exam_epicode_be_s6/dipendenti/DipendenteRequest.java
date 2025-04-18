package it.epicode.exam_epicode_be_s6.dipendenti;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DipendenteRequest {
    @NotNull(message = "Il nome è obbligatorio")
    @Size(max = 50)
    private String nome;

    @NotNull(message = "Il cognome è obbligatorio")
    @Size(max = 50)
    private String cognome;

    @NotNull(message = "L'username è obbligatorio")
    @Size(max = 50)
    private String username;

    @NotNull(message = "L'email è obbligatoria")
    @Email(message = "Formato email non valido")
    private String email;
}
