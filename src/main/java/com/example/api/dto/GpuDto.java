package com.example.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record GpuDto(

        @NotBlank(message = "Name cannot be blank")
        @Size(max = 255, message = "Name cannot exceed 255 characters")
        String name,

        @Positive
        @NotNull
        Integer videoRamCapacity
) {
}
