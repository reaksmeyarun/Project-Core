package com.kh.sbilyhour.core.infrastructure.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class UserAlreadyExitException extends RuntimeException {

    public final String message;

    public UserAlreadyExitException() {
        this.message = "User already register!";
    }

}
