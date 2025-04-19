package it.epicode.exam_epicode_be_s6.viaggi;

import it.epicode.exam_epicode_be_s6.commons.CommonResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/viaggi")
public class ViaggioController {

    @Autowired
    private ViaggioService viaggioService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommonResponse save(@RequestBody @Valid Viaggio viaggio) {
        viaggioService.save(viaggio);
        return new CommonResponse(viaggio.getId());
    }
    @GetMapping
    public Page<Viaggio> getAllPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        PageRequest pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return viaggioService.findAllPaged(pageable);
    }
    @GetMapping("/{id}")
    public Viaggio getViaggioById(@PathVariable Long id) {
        return viaggioService.findById(id);
    }
    @PutMapping("/{id}")
    public Viaggio updateViaggio(@PathVariable Long id, @RequestBody @Valid Viaggio updatedViaggio) {
        Viaggio existing = viaggioService.findById(id);
        if (existing != null) {
            existing.setDestinazione(updatedViaggio.getDestinazione());
            existing.setStato(updatedViaggio.getStato());
            return existing;
        } else {
            return null;
        }
    }
    @DeleteMapping("/{id}")
    public void deleteViaggio(@PathVariable Long id) {
        viaggioService.delete(id);
    }
    @PatchMapping("/{id}")
    public Viaggio updateStatoViaggio(@PathVariable Long id, @RequestBody @Valid Viaggio updatedViaggio) {
        Viaggio existing = viaggioService.findById(id);
        if (existing != null) {
            existing.setStato(updatedViaggio.getStato());
            viaggioService.save(existing);
            return existing;
        } else {
            return null;
        }
    }
}
