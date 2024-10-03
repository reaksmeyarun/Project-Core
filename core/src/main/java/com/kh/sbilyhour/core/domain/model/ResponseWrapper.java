package com.kh.sbilyhour.core.domain.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.kh.sbilyhour.core.domain.error.HttpError;
import com.kh.sbilyhour.core.domain.status.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * Wrapper class for API responses.
 * <p>
 * This class encapsulates the response data and any potential errors
 * that may occur during API processing. It includes a status field
 * indicating the result of the operation.
 * </P>
 * @param <T> the type of the response data
 */
@Data
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"status", "error"}) // Ensure status is always first
public class ResponseWrapper<T> {

    /**
     * The response data of type T.
     * This field can be any data that the API may return.
     */
    private T data;

    /**
     * The error information, if any.
     * This field will hold error details if an error occurred
     * during processing, otherwise it will be null.
     */
    private HttpError error;

    /**
     * The status of the response.
     * This field indicates the success or failure of the operation.
     * It defaults to {@link Status#SUCCESS} if not specified.
     */
    @Builder.Default
    private Status status = Status.SUCCESS;

}