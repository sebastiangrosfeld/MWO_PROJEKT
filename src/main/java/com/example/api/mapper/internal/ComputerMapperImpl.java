package com.example.api.mapper.internal;

import com.example.api.dto.ComputerDto;
import com.example.api.mapper.ComputerMapper;
import com.example.api.mapper.GpuMapper;
import com.example.api.mapper.RamMapper;
import com.example.api.model.Computer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ComputerMapperImpl implements ComputerMapper {

    private final RamMapper ramMapper;
    private final GpuMapper gpuMapper;

    @Override
    public ComputerDto mapComputerToComputerDto(Computer computer) {
        return ComputerDto.builder()
                .name(computer.getName())
                .type(computer.getType())
                .cpuName(computer.getCpuName())
                .enclosureName(computer.getEnclosureName())
                .ram(ramMapper.mapRamToRamDto(computer.getRam()))
                .gpu(gpuMapper.mapGpuToGpuDto(computer.getGpu()))
                .hardDiskCapacity(computer.getHardDiskCapacity())
                .powerSupply(computer.getPowerSupply())
                .price(computer.getPrice())
                .build();
    }

    @Override
    public Computer mapComputerDtoToComputer(ComputerDto computerDto) {
        return Computer.builder()
                .name(computerDto.name())
                .type(computerDto.type())
                .cpuName(computerDto.cpuName())
                .enclosureName(computerDto.enclosureName())
                .gpu(gpuMapper.mapGpuDtoToGpu(computerDto.gpu()))
                .hardDiskCapacity(computerDto.hardDiskCapacity())
                .ram(ramMapper.mapRamDtoToRam(computerDto.ram()))
                .powerSupply(computerDto.powerSupply())
                .price(computerDto.price())
                .build();
    }
}
