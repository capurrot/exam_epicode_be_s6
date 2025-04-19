package it.epicode.exam_epicode_be_s6.viaggi;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "viaggi")

public class Viaggio {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_viaggio")
    private Long id;

    @NotBlank(message = "La destinazione è obbligatoria")
    @Column(nullable = false, length = 50)
    private String destinazione;

    @NotNull(message = "La data di partenza è obbligatoria")
    @FutureOrPresent(message = "La data di partenza non può essere nel passato")
    @Column(name = "data_partenza")
    private LocalDate dataPartenza;

    @NotNull(message = "La data di ritorno è obbligatoria")
    @Future(message = "La data di ritorno deve essere nel futuro")
    @Column(name = "data_ritorno")
    private LocalDate dataRitorno;

    @NotNull(message = "Lo stato del viaggio è obbligatorio")
    @Enumerated(EnumType.STRING)
    private ViaggioStato stato;
}