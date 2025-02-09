package com.gundamfactory.infrastructure.controllers.advice;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 1. Validación de Constraint Violations (ya lo tenías)
    @ExceptionHandler({MethodArgumentNotValidException.class, ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleValidationExceptions(Exception ex) {
        Map<String, String> errors = new HashMap<>();

        if (ex instanceof MethodArgumentNotValidException) {
            ((MethodArgumentNotValidException) ex).getBindingResult().getFieldErrors().forEach(error -> {
                errors.put(error.getField(), error.getDefaultMessage());
            });
        } else if (ex instanceof ConstraintViolationException) {
            ((ConstraintViolationException) ex).getConstraintViolations().forEach(violation -> {
                String fieldName = violation.getPropertyPath().toString();
                String errorMessage = violation.getMessage();
                errors.put(fieldName, errorMessage);
            });
        }

        return new ApiError("Error de validación", HttpStatus.BAD_REQUEST.value(), errors);
    }

    // 2. Recurso no encontrado
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError handleEntityNotFoundException(EntityNotFoundException ex) {
        return new ApiError("Recurso no encontrado", HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

    // 3. Violación de integridad de datos (DB)
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiError handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        return new ApiError("Error de integridad de datos", HttpStatus.CONFLICT.value(), ex.getMostSpecificCause().getMessage());
    }

    // 4. Argumento ilegal
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleIllegalArgumentException(IllegalArgumentException ex) {
        return new ApiError("Argumento ilegal", HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    // 5. Formato de mensaje no legible (JSON mal formado)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        return new ApiError("Formato de solicitud incorrecto", HttpStatus.BAD_REQUEST.value(), ex.getMostSpecificCause().getMessage());
    }

    // 6. Error de tipo en argumentos (por ejemplo, pasar un String cuando se espera un número)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        String error = String.format("El parámetro '%s' debe ser del tipo '%s'", ex.getName(), ex.getRequiredType().getSimpleName());
        return new ApiError("Tipo de parámetro incorrecto", HttpStatus.BAD_REQUEST.value(), error);
    }

    // 7. Parámetro faltante en la solicitud
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleMissingServletRequestParameterException(MissingServletRequestParameterException ex) {
        String error = String.format("Falta el parámetro obligatorio: '%s'", ex.getParameterName());
        return new ApiError("Parámetro faltante", HttpStatus.BAD_REQUEST.value(), error);
    }

    // 8. Control de concurrencia optimista (si lo usás)
    @ExceptionHandler(OptimisticLockingFailureException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiError handleOptimisticLockingFailureException(OptimisticLockingFailureException ex) {
        return new ApiError("Conflicto de actualización concurrente", HttpStatus.CONFLICT.value(), ex.getMessage());
    }

    // 9. Manejo de excepciones genéricas (para capturar cualquier 500 no esperado)
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiError handleGenericException(Exception ex) {
        return new ApiError("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
    }
}
