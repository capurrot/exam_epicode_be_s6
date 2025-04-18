package it.epicode.exam_epicode_be_s6.dipendenti;

import it.epicode.exam_epicode_be_s6.cloudinary.CloudinaryService;
import it.epicode.exam_epicode_be_s6.exceptions.DuplicateUsernameException;
import it.epicode.exam_epicode_be_s6.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class DipendenteService {

    @Autowired
    private DipendenteRepository dipendenteRepository;
    @Autowired
    private CloudinaryService cloudinaryService;

    public Dipendente save(Dipendente dipendente) {
        if (dipendenteRepository.existsByUsername(dipendente.getUsername())) {
            throw new DuplicateUsernameException("Lo username '" + dipendente.getUsername() + "' è già in uso.");
        } else if (dipendenteRepository.existsByEmail(dipendente.getEmail())) {
            throw new DuplicateUsernameException("L'email '" + dipendente.getEmail() + "' è già in uso.");
        }
        return dipendenteRepository.save(dipendente);
    }
    public Page<Dipendente> findAllPaged(PageRequest pageable) {
        return dipendenteRepository.findAll((Pageable) pageable);
    }

    public void delete(Long id) {
        dipendenteRepository.deleteById(id);
    }

    public Dipendente findById(Long id) {
        return dipendenteRepository.findById(id).orElseThrow(() -> new NotFoundException(id, "Dipendente"));
    }

    public Dipendente findByUsername(String username) {
        return dipendenteRepository.findByUsername(username).orElse(null);
    }

    public Dipendente findByEmail(String email) {
        return dipendenteRepository.findByEmail(email).orElse(null);
    }

    public void uploadImage(Long id, MultipartFile file) {
        Dipendente dipendente = dipendenteRepository.findById(id).orElseThrow(() -> new RuntimeException("Dipendente non trovato"));
        dipendente.setAvatar(cloudinaryService.uploadImage(file));
        dipendenteRepository.save(dipendente);
    }
}