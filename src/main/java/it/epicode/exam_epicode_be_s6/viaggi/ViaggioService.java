package it.epicode.exam_epicode_be_s6.viaggi;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ViaggioService {

    @Autowired
    private final ViaggioRepository viaggioRepository;

    public ViaggioService(ViaggioRepository viaggioRepository) {
        this.viaggioRepository = viaggioRepository;
    }

    public Viaggio save(Viaggio viaggio) {
        return viaggioRepository.save(viaggio);
    }

    public Page<Viaggio> findAllPaged(PageRequest pageable) {
        return viaggioRepository.findAll((Pageable) pageable);
    }

    public void delete(Long id) {
        viaggioRepository.deleteById(id);
    }

    public Viaggio findById(Long idViaggio) {
        return viaggioRepository.findById(idViaggio).orElse(null);
    }
}