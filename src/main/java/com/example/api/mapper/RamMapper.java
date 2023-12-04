package com.example.api.mapper;

import com.example.api.dto.RamDto;
import com.example.api.model.Ram;

public interface RamMapper {

    Ram mapRamDtoToRam(RamDto ramDto);

    RamDto mapRamToRamDto(Ram ram);
}
