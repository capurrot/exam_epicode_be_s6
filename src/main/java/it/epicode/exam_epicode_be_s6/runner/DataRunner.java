package it.epicode.exam_epicode_be_s6.runner;

import com.github.javafaker.Faker;
import it.epicode.exam_epicode_be_s6.dipendenti.Dipendente;
import it.epicode.exam_epicode_be_s6.dipendenti.DipendenteRepository;
import it.epicode.exam_epicode_be_s6.viaggi.Viaggio;
import it.epicode.exam_epicode_be_s6.viaggi.ViaggioRepository;
import it.epicode.exam_epicode_be_s6.viaggi.ViaggioStato;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Locale;

@Component
public class DataRunner implements CommandLineRunner {

    private final DipendenteRepository dipendenteRepository;
    private final ViaggioRepository viaggioRepository;

    public DataRunner(DipendenteRepository dipendenteRepository,
                           ViaggioRepository viaggioRepository) {
        this.dipendenteRepository = dipendenteRepository;
        this.viaggioRepository = viaggioRepository;
    }

    @Override
    public void run(String... args) {
        Faker faker = new Faker(Locale.ITALIAN);

        for (int i = 0; i < 20; i++) {
            Dipendente d = new Dipendente();
            d.setNome(faker.name().firstName());
            d.setCognome(faker.name().lastName());
            d.setEmail(faker.internet().emailAddress());
            d.setUsername(faker.name().username());
            if  (!dipendenteRepository.existsByUsername(d.getUsername())) {
                dipendenteRepository.save(d);
            }
        }

        for (int i = 0; i < 20; i++) {
            LocalDate dataPartenza = LocalDate.now().plusDays(faker.number().numberBetween(1, 30));
            LocalDate dataRitorno = dataPartenza.plusDays(faker.number().numberBetween(1, 10));

            Viaggio viaggio = new Viaggio();
            viaggio.setDestinazione(faker.country().capital());
            viaggio.setDataPartenza(dataPartenza);
            viaggio.setDataRitorno(dataRitorno);
            viaggio.setStato(ViaggioStato.IN_PROGRAMMA);

            viaggioRepository.save(viaggio);
        }

        System.out.println("Dipendenti e viaggi generati correttamente");
    }
}
