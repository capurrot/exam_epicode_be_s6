package it.epicode.exam_epicode_be_s6.dipendenti;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;


@Repository
public interface DipendenteRepository extends JpaRepository<Dipendente, Long> {
    Optional<Dipendente> findByUsername(String username);
    Optional<Dipendente> findByEmail(String email);
    boolean existsByUsername(String username);

    boolean existsByEmail(String email)  ;
}