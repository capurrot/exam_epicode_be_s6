package it.epicode.exam_epicode_be_s6.dipendenti;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "dipendenti")

public class Dipendente {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_dipendente")
    private Long id;

    @NotBlank(message = "Lo username è obbligatorio")
    @Size(max = 50, message = "Lo username non può superare i 50 caratteri")
    @Column(unique = true, nullable = false, length = 50)
    private String username;

    @NotBlank(message = "Il nome è obbligatorio")
    @Size(max = 50, message = "Il nome non può superare i 50 caratteri")
    @Column(nullable = false, length = 50)
    private String nome;

    @NotBlank(message = "Il cognome è obbligatorio")
    @Size(max = 50, message = "Il cognome non può superare i 50 caratteri")
    @Column(nullable = false, length = 50)
    private String cognome;

    @NotBlank(message = "L'email è obbligatoria")
    @Email(message = "L'email deve essere valida")
    @Column(unique = true, nullable = false)
    private String email;

    private String avatar;
}