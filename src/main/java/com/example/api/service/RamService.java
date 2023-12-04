package com.example.api.service;

import com.example.api.dto.RamDto;

import java.util.List;

public interface RamService {
    List<RamDto> getAllRams();

    RamDto getRam(String name);

    void updateRam(String name, RamDto ramDto);

    void createRam(RamDto ramDto);

    void deleteRam(String name);

    void deleteAllRams();
}
