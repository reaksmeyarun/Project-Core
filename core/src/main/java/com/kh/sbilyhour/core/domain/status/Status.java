package com.kh.sbilyhour.core.domain.status;

import lombok.Getter;

/**
 * Enum representing the different statuses for operation outcomes.
 *
 * <ul>
 *   <li><b>SUCCESS</b> - Indicates the operation was completed successfully.</li>
 *   <li><b>FAIL</b> - Indicates the operation failed due to a specific issue.</li>
 *   <li><b>ERROR</b> - Indicates an error occurred during the operation, typically an unexpected issue.</li>
 * </ul>
 */
@Getter
public enum Status {

    /**
     * Indicates that the operation was completed successfully.
     */
    SUCCESS,

    /**
     * Indicates that the operation failed due to a known issue.
     */
    FAIL,

    /**
     * Indicates that an unexpected error occurred during the operation.
     */
    ERROR;

}
