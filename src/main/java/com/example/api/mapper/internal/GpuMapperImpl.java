package com.example.api.mapper.internal;

import com.example.api.dto.GpuDto;
import com.example.api.mapper.GpuMapper;
import com.example.api.model.Gpu;
import com.example.api.repository.GpuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GpuMapperImpl implements GpuMapper {

    private final GpuRepository gpuRepository;

    @Override
    public Gpu mapGpuDtoToGpu(GpuDto gpuDto) {
        if(gpuRepository.existsByName(gpuDto.name()))
            return gpuRepository.findGpuByName(gpuDto.name()).orElseThrow();
        return Gpu.builder()
                .name(gpuDto.name())
                .vRamCapacity(gpuDto.videoRamCapacity())
                .build();
    }

    @Override
    public GpuDto mapGpuToGpuDto(Gpu gpu) {
        return new GpuDto(
                gpu.getName(),
                gpu.getVRamCapacity()
        );
    }
}
