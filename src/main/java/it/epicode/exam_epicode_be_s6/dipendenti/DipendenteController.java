package it.epicode.exam_epicode_be_s6.dipendenti;

import it.epicode.exam_epicode_be_s6.commons.CommonResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/dipendenti")
public class DipendenteController {

    @Autowired
    private DipendenteService dipendenteService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommonResponse save(@RequestBody @Valid Dipendente dipendente) {
        dipendenteService.save(dipendente);
        return new CommonResponse(dipendente.getId());
    }

    @GetMapping
    public Page<Dipendente> getAllPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        PageRequest pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return dipendenteService.findAllPaged(pageable);
    }

    @GetMapping("/{id}")
    public Dipendente getById(@PathVariable Long id) {
        return dipendenteService.findById(id);
    }

    @PutMapping("/{id}")
    public Dipendente update(@PathVariable Long id, @RequestBody @Valid Dipendente updatedDipendente) {
        Dipendente existing = dipendenteService.findById(id);
        if (existing != null) {
            existing.setNome(updatedDipendente.getNome());
            existing.setCognome(updatedDipendente.getCognome());
            existing.setEmail(updatedDipendente.getEmail());
            existing.setUsername(updatedDipendente.getUsername());
            return dipendenteService.save(existing);
        } else {
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        dipendenteService.delete(id);
    }
    @GetMapping("/username/{username}")
    public Dipendente getByUsername(@PathVariable String username) {
        return dipendenteService.findByUsername(username);
    }
    @GetMapping("/email/{email}")
    public Dipendente getByEmail(@PathVariable String email) {
        return dipendenteService.findByEmail(email);
    }

    @PatchMapping(value = "/{id}/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Dipendente uploadImage(@PathVariable Long id, @RequestParam MultipartFile file) {
        dipendenteService.uploadImage(id, file);
        return dipendenteService.findById(id);
    }
}

