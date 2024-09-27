package com.kh.sbilyhour.common_module.domain.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.kh.sbilyhour.common_module.domain.error.HttpError;
import com.kh.sbilyhour.common_module.domain.status.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "status", "error" }) // Ensure status is always first
public class ResponseWrapper<T> {

    private T data;

    private HttpError error;

    @Builder.Default
    private Status status = Status.SUCCESS;

}
