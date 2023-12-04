package com.example.api.mapper.internal;

import com.example.api.dto.RamDto;
import com.example.api.mapper.RamMapper;
import com.example.api.model.Ram;
import com.example.api.repository.RamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RamMapperImpl implements RamMapper {

    private final RamRepository ramRepository;

    @Override
    public Ram mapRamDtoToRam(RamDto ramDto) {
        if(ramRepository.existsByName(ramDto.name()))
            return ramRepository.findRamByName(ramDto.name()).orElseThrow();
        return Ram.builder()
                .name(ramDto.name())
                .ramCapacity(ramDto.ramCapacity())
                .memoryRate(ramDto.memoryRate())
                .build();
    }

    @Override
    public RamDto mapRamToRamDto(Ram ram) {
        return new RamDto(
                ram.getName(),
                ram.getRamCapacity(),
                ram.getMemoryRate()
        );
    }
}
