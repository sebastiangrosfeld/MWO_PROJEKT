package com.example.api.service;

import com.example.api.dto.GpuDto;

import java.util.List;

public interface GpuService {
    List<GpuDto> getAllGpus();

    GpuDto getGpu(String name);

    void updateGpu(String name, GpuDto gpuDto);

    void createGpu(GpuDto gpuDto);

    void deleteGpu(String name);

    void deleteAllGpus();
}
