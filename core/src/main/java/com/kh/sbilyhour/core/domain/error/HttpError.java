package com.kh.sbilyhour.core.domain.error;

import lombok.*;

import java.util.List;

/**
 * Represents an HTTP error response.
 * <p>
 * This class encapsulates the details of an error that occurs during
 * HTTP processing, including an error code and a list of specific errors.
 * </P>
 */
@Builder
@Data
public class HttpError {

    /**
     * The HTTP error code.
     * This field indicates the specific error type (e.g., 404 for Not Found).
     */
    private int code;

    /**
     * A list of detailed error messages.
     * This field holds individual error details that provide more context
     * about what went wrong during the request processing.
     */
    private List<ApiError> errors;


    public HttpError(int code, List<ApiError> errors) {
        this.code = code;
        this.errors = errors;
    }
}
