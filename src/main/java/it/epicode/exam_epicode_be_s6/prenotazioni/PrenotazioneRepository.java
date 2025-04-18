package it.epicode.exam_epicode_be_s6.prenotazioni;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {
    @Query("""
    SELECT COUNT(p) > 0 FROM Prenotazione p 
    WHERE p.dipendente.id = :dipendenteId 
      AND p.viaggio.dataPartenza <= :dataRitorno 
      AND p.viaggio.dataRitorno >= :dataPartenza
""")
    boolean existsByDipendenteAndDateOverlap(Long dipendenteId, LocalDate dataPartenza, LocalDate dataRitorno);

}