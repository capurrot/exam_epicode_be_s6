package it.epicode.exam_epicode_be_s6.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(Long id, String resource) {
        super(resource + " with id " + id + " not found");
    }
}
