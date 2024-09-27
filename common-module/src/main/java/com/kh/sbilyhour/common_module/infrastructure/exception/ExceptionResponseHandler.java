package com.kh.sbilyhour.common_module.infrastructure.exception;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kh.sbilyhour.common_module.domain.model.ResponseWrapper;
import com.kh.sbilyhour.common_module.domain.status.Status;
import com.kh.sbilyhour.common_module.domain.error.Error;
import com.kh.sbilyhour.common_module.domain.error.HttpError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ExceptionResponseHandler handles various types of exceptions and
 * returns custom error responses in a structured JSON format.
 * This class centralizes exception handling for cleaner code and
 * consistent error responses across the application.
 */
@ControllerAdvice
public class ExceptionResponseHandler {

    private final Logger logger = LoggerFactory.getLogger(ExceptionResponseHandler.class);

    /**
     * Handles general exceptions that are not explicitly caught by other handlers.
     * Provides a generic message for unexpected errors and logs the exception.
     *
     * @param exception the caught exception
     * @return a ResponseEntity with an internal server error response
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseWrapper<HttpError>> handleGeneralException(Exception exception) {
        logError(exception);
        String message = "An unexpected error occurred. Please try again later.";
        return createErrorResponse(Status.ERROR, message, HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    /**
     * Handles data integrity violations (e.g., constraint violations in the database).
     * Provides a specific message for data-related issues and logs the exception.
     *
     * @param exception the caught DataIntegrityViolationException
     * @return a ResponseEntity with an internal server error response
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ResponseWrapper<HttpError>> handleDataIntegrityViolation(DataIntegrityViolationException exception) {
        logError(exception);
        String message = "Data integrity violation. Please ensure all required fields are filled correctly.";
        return createErrorResponse(Status.ERROR, message, HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    /**
     * Handles runtime exceptions that are not specifically handled elsewhere.
     * Uses the exception message as the error message and logs the exception.
     *
     * @param exception the caught RuntimeException
     * @return a ResponseEntity with a not found error response
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ResponseWrapper<HttpError>> handleRuntimeException(RuntimeException exception) {
        logError(exception);
        return createErrorResponse(Status.FAIL, exception.getMessage(), HttpStatus.NOT_FOUND.value());
    }

    /**
     * Handles client-side errors (e.g., HTTP 4xx responses).
     * Parses the error message from the client response body and logs the exception.
     *
     * @param exception the caught HttpClientErrorException
     * @return a ResponseEntity with a client-side error response
     */
    @ExceptionHandler(HttpClientErrorException.class)
    private ResponseEntity<ResponseWrapper<HttpError>> handleHttpClientError(HttpClientErrorException exception) {
        logError(exception);
        String responseBody = exception.getResponseBodyAsString();
        List<Error> errors = createErrorList(getErrorMessage(responseBody));
        return createErrorResponse(Status.FAIL, errors, exception.getStatusCode().value());
    }

    /**
     * Handles illegal argument exceptions that typically arise from invalid method arguments.
     * Uses the exception message as the error message and logs the exception.
     *
     * @param exception the caught IllegalArgumentException
     * @return a ResponseEntity with a bad request error response
     */
    @ExceptionHandler(IllegalArgumentException.class)
    private ResponseEntity<ResponseWrapper<HttpError>> handleIllegalArgument(IllegalArgumentException exception) {
        logError(exception);
        return createErrorResponse(Status.FAIL, exception.getMessage(), HttpStatus.BAD_REQUEST.value());
    }

    /**
     * Handles validation errors from method arguments (e.g., invalid input on request objects).
     * Collects all field validation errors and logs the exception.
     *
     * @param exception the caught MethodArgumentNotValidException
     * @return a ResponseEntity with a bad request error response containing all validation errors
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseWrapper<HttpError>> handleValidationExceptions(MethodArgumentNotValidException exception) {
        logError(exception);
        List<Error> errorMessages = exception.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .filter(message -> message != null && !message.trim().isEmpty())
                .map(Error::new)
                .collect(Collectors.toList());
        return createErrorResponse(Status.FAIL, errorMessages, HttpStatus.BAD_REQUEST.value());
    }

    /**
     * Handles errors when no handler is found for a specific request (e.g., 404 Not Found).
     * Uses the exception message as the error message and logs the exception.
     *
     * @param exception the caught NoHandlerFoundException
     * @return a ResponseEntity with a not found error response
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ResponseWrapper<HttpError>> handleNotFound(NoHandlerFoundException exception) {
        logError(exception);
        return createErrorResponse(Status.FAIL, exception.getMessage(), HttpStatus.NOT_FOUND.value());
    }

    /**
     * Logs the provided exception, including the class and message.
     *
     * @param exception the caught exception
     */
    private void logError(Exception exception) {
        logger.error("{}--> {}", exception.getClass().getSimpleName(), exception.getMessage());
    }

    /**
     * Creates a ResponseEntity with a single error message.
     *
     * @param message the error message
     * @param code    the HTTP status code
     * @return a ResponseEntity with the error message and status code
     */
    private ResponseEntity<ResponseWrapper<HttpError>> createErrorResponse(Status status, String message, Integer code) {
        List<Error> errors = createErrorList(message);
        return createErrorResponse(status, errors, code);
    }

    /**
     * Creates a ResponseEntity with a list of errors.
     *
     * @param errors a list of errors
     * @param code   the HTTP status code
     * @return a ResponseEntity with the error messages and status code
     */
    private ResponseEntity<ResponseWrapper<HttpError>> createErrorResponse(Status status, List<Error> errors, Integer code) {
        HttpError httpError = HttpError.builder()
                .code(code)
                .errors(errors)
                .build();
        ResponseWrapper<HttpError> responseWrapper = ResponseWrapper.<HttpError>builder().status(status).error(httpError).build();
        return ResponseEntity.status(code).body(responseWrapper);
    }

    /**
     * Creates a list with a single error message.
     *
     * @param message the error message
     * @return a list containing a single error
     */
    private List<Error> createErrorList(String message) {
        return Collections.singletonList(new Error(message));
    }

    /**
     * Extracts the error message from a client-side error response body (JSON format).
     *
     * @param responseBody the JSON response body as a string
     * @return the extracted error message, or a default message if parsing fails
     */
    private String getErrorMessage(String responseBody) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(responseBody);
            JsonNode errorNode = rootNode.path("error").path("message");
            return errorNode.isMissingNode() ? "Unknown error occurred" : errorNode.asText();
        } catch (Exception e) {
            return "Error parsing error message: " + e.getMessage();
        }
    }
}