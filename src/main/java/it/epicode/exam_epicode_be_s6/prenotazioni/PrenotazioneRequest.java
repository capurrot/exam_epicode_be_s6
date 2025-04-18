package it.epicode.exam_epicode_be_s6.prenotazioni;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PrenotazioneRequest {
    @NotNull(message = "L'ID del dipendente è obbligatorio")
    private Long idDipendente;

    @NotNull(message = "L'ID del viaggio è obbligatorio")
    private Long idViaggio;

    @NotNull(message = "La data di prenotazione è obbligatoria")
    @FutureOrPresent(message = "La data di prenotazione non può essere nel passato")
    private LocalDate dataPrenotazione;

    @Size(max = 255, message = "Le note non possono superare i 255 caratteri")
    private String note;
}
