package it.epicode.exam_epicode_be_s6.prenotazioni;

import it.epicode.exam_epicode_be_s6.dipendenti.Dipendente;
import it.epicode.exam_epicode_be_s6.viaggi.Viaggio;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "prenotazioni")
public class Prenotazione {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_prenotazione")
    private Long id;

    @NotNull(message = "Il viaggio è obbligatorio")
    @ManyToOne
    @JoinColumn(name = "id_viaggio", nullable = false)
    private Viaggio viaggio;

    @NotNull(message = "Il dipendente è obbligatorio")
    @ManyToOne
    @JoinColumn(name = "id_dipendente", nullable = false)
    private Dipendente dipendente;

    @NotNull(message = "La data di prenotazione è obbligatoria")
    @FutureOrPresent(message = "La data di prenotazione non può essere nel passato")
    @Column(name = "data_richiesta_prenotazione")
    private LocalDate dataPrenotazione;

    @Size(max = 255, message = "Le note non possono superare i 255 caratteri")
    @Column(name = "note")
    private String note;
}