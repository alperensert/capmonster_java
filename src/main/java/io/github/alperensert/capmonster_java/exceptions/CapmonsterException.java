package io.github.alperensert.capmonster_java.exceptions;

public class CapmonsterException extends RuntimeException {
    public String CODE;
    public String DESCRIPTION;
    public CapmonsterException(String code, String description) {
        super("[" + code + "] " + description);
        this.DESCRIPTION = description;
        this.CODE = code;
    }
}