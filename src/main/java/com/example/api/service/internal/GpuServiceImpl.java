package com.example.api.service.internal;

import com.example.api.dto.GpuDto;
import com.example.api.dto.RamDto;
import com.example.api.mapper.GpuMapper;
import com.example.api.model.Gpu;
import com.example.api.model.Ram;
import com.example.api.repository.GpuRepository;
import com.example.api.service.GpuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
public class GpuServiceImpl implements GpuService {

    private final GpuRepository gpuRepository;
    private final GpuMapper gpuMapper;

    @Override
    public List<GpuDto> getAllGpus() {
        return gpuRepository.findAll().stream().map(gpuMapper::mapGpuToGpuDto).collect(Collectors.toList());
    }

    @Override
    public GpuDto getGpu(String name) {
        if (name.isEmpty() || name == null)
            throw new IllegalArgumentException("name cannot be null");
        return gpuMapper.mapGpuToGpuDto(findGpuByName(name));
    }

    @Override
    public void updateGpu(String name, GpuDto gpuDto) {
        if (name.isEmpty() || name == null)
            throw new IllegalArgumentException("name cannot be null");
        Gpu gpu = findGpuByName(name);
        updateGpu(gpu, gpuDto);
        gpuRepository.save(gpu);
    }

    @Override
    public void createGpu(GpuDto gpuDto) {
        Gpu gpu = gpuMapper.mapGpuDtoToGpu(gpuDto);
        if(gpuRepository.existsByName(gpu.getName()))
            throw new IllegalStateException("Created Gpu already exists");
        gpuRepository.save(gpu);
    }

    @Override
    public void deleteGpu(String name) {
        Gpu gpu = findGpuByName(name);
        gpuRepository.delete(gpu);
    }

    @Override
    public void deleteAllGpus() {
        if( gpuRepository.findAll().isEmpty())
            throw new IllegalStateException("There is no Gpus in Database");
        gpuRepository.deleteAll();

    }

    public Gpu findGpuByName(String name) {
        return gpuRepository.findGpuByName(name)
                .orElseThrow(() -> new IllegalStateException("Searched gpu not exist"));
    }

    private void updateGpu(Gpu gpu, GpuDto gpuDto) {
        gpu.setName(gpuDto.name());
        gpu.setVRamCapacity(gpuDto.videoRamCapacity());
    }
}
