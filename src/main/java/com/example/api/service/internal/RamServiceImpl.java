package com.example.api.service.internal;

import com.example.api.dto.RamDto;
import com.example.api.mapper.RamMapper;
import com.example.api.model.Ram;
import com.example.api.repository.RamRepository;
import com.example.api.service.RamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
public class RamServiceImpl implements RamService {

    private final RamRepository repository;
    private final RamMapper ramMapper;

    @Override
    public List<RamDto> getAllRams() {
        return repository.findAll().stream().map(ramMapper::mapRamToRamDto).collect(Collectors.toList());
    }
    @Override
    public RamDto getRam(String name) {
        if(name.isEmpty() || name == null)
            throw new IllegalStateException("Name cannot be empty");
        return ramMapper.mapRamToRamDto(findRamByName(name));
    }

    @Override
    public void updateRam(String name, RamDto ramDto) {
        if(name.isEmpty() || name == null)
            throw new IllegalStateException("Name cannot be empty");
        Ram ram = findRamByName(name);
        updateRam(ram, ramDto);
    }

    @Override
    public void createRam(RamDto ramDto) {
        Ram ram = ramMapper.mapRamDtoToRam(ramDto);
        if(repository.existsByName(ram.getName()))
           throw new IllegalStateException("Created Ram already exist");
        repository.save(ram);
    }

    @Override
    public void deleteRam(String name) {
        Ram ram = findRamByName(name);
        repository.delete(ram);
    }

    @Override
    public void deleteAllRams() {
        if( repository.findAll().isEmpty())
            throw new IllegalStateException("There is no Rams in Database");
        repository.deleteAll();
    }

    public Ram findRamByName(String name) {
        return repository.findRamByName(name)
                .orElseThrow(() -> new IllegalStateException("Searched ram not exist"));
    }

    private void updateRam(Ram ram, RamDto ramDto) {
        ram.setName(ramDto.name());
        ram.setRamCapacity(ramDto.ramCapacity());
        ram.setMemoryRate(ramDto.memoryRate());
    }
}
