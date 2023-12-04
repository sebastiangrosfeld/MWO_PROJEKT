package com.example.api.mapper;

import com.example.api.dto.ComputerDto;
import com.example.api.model.Computer;

public interface ComputerMapper {

    ComputerDto mapComputerToComputerDto(Computer computer);
    Computer mapComputerDtoToComputer(ComputerDto computerDto);
}
