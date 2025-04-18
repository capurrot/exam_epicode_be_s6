package it.epicode.exam_epicode_be_s6.viaggi;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ViaggioRequest {

    @NotNull(message = "La destinazione è obbligatoria")
    @Size(max = 50)
    private String destinazione;

    @NotNull(message = "La data di partenza è obbligatoria")
    @FutureOrPresent(message = "La data di partenza non può essere nel passato")
    private LocalDate dataPartenza;

    @NotNull(message = "La data di ritorno è obbligatoria")
    @FutureOrPresent(message = "La data di ritorno non può essere nel passato")
    private LocalDate dataRitorno;
}
