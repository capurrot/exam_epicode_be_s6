package it.epicode.exam_epicode_be_s6.exceptions;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String e) {
        super(e);
    }
}
