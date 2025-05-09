package com.hcl.academy.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class GenericResponse<T> {
    @NotNull
    private Boolean status;
    private String message;
    private String errorType;
    @Builder.Default
    private Long timestamp = System.currentTimeMillis();
    private T data;

    public static <T> GenericResponse<T> success(T data) {
        return GenericResponse.<T>builder()
                .message("Response Success")
                .data(data)
                .errorType("NONE")
                .status(true)
                .build();
    }

    public static <T> GenericResponse<T> success(String message, T data) {
        return GenericResponse.<T>builder()
                .message(message)
                .data(data)
                .errorType("NONE")
                .status(true)
                .build();
    }

    public static <T> GenericResponse<T> error(String errorType, String message) {
        return GenericResponse.<T>builder()
                .message(message)
                .errorType(errorType)
                .status(false)
                .build();
    }
}
