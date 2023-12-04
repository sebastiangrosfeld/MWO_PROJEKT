package com.example.api.mapper;

import com.example.api.dto.GpuDto;
import com.example.api.model.Gpu;

public interface GpuMapper {

    Gpu mapGpuDtoToGpu(GpuDto gpuDto);

    GpuDto mapGpuToGpuDto(Gpu gpu);
}
