package it.epicode.exam_epicode_be_s6.prenotazioni;

import it.epicode.exam_epicode_be_s6.dipendenti.Dipendente;
import it.epicode.exam_epicode_be_s6.dipendenti.DipendenteService;
import it.epicode.exam_epicode_be_s6.viaggi.Viaggio;
import it.epicode.exam_epicode_be_s6.viaggi.ViaggioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrenotazioneController {
    @Autowired
    private PrenotazioneService prenotazioneService;
    @Autowired
    private DipendenteService dipendenteService;
    @Autowired
    private ViaggioService viaggioService;
    @PostMapping("/prenotazioni")
    public Prenotazione save(@Valid @RequestBody PrenotazioneRequest request) {
        Dipendente dipendentePrenotazione = dipendenteService.findById(request.getIdDipendente());
        Viaggio viaggioPrenotazione = viaggioService.findById(request.getIdViaggio());

        Prenotazione p = new Prenotazione();
        p.setDipendente(dipendentePrenotazione);
        p.setViaggio(viaggioPrenotazione);
        p.setDataPrenotazione(request.getDataPrenotazione());
        p.setNote(request.getNote());

        return prenotazioneService.save(p);
    }
}
