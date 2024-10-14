package com.kh.sbilyhour.core.domain.error;

import lombok.Data;

/**
 * Represents a detailed error message.
 * <p>
 * This class encapsulates the information about an error that occurred
 * during processing, providing a message that describes the error.
 * </P>
 */
@Data
public class ApiError {

    /**
     * The error message.
     * This field contains a descriptive message about the error that occurred,
     * providing context to the user or developer regarding the issue.
     */
    private String message;

    public ApiError(String message) {
        this.message = message;
    }
}