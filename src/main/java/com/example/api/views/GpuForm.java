package com.example.api.views;

import com.example.api.dto.GpuDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GpuForm {
    private String name;
    private Integer videoRamCapacity;

    public GpuForm(GpuDto gpuDto) {
        name = gpuDto.name();
        videoRamCapacity = gpuDto.videoRamCapacity();
    }
}
