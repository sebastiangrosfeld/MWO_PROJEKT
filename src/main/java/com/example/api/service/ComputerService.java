package com.example.api.service;

import com.example.api.dto.ComputerDto;

import java.util.List;

public interface ComputerService {

    List<ComputerDto> getAllComputers();

    ComputerDto getComputer(String name);

    void updateComputer(String name, ComputerDto computerDto);

    void deleteComputer(String name);

    void createComputer(ComputerDto computerDto);

    void deleteAllComputers();

}
