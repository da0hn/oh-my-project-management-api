package dev.ghonda.project.management.security.exceptions;

import java.io.Serial;

public class RefreshTokenInvalidException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1537260787416102201L;

    public RefreshTokenInvalidException(final String message) {
        super(message);
    }

    public RefreshTokenInvalidException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
