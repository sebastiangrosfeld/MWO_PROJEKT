package com.example.api.dataFaker;

import com.example.api.repository.ComputerRepository;
import com.example.api.repository.GpuRepository;
import com.example.api.repository.RamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Component
@Transactional
@RequiredArgsConstructor
public class DatabaseSeeder implements CommandLineRunner {

    private final ComputerRepository computerRepository;
    private final GpuRepository gpuRepository;
    private final RamRepository ramRepository;
    private final ComputerSeeder computerSeeder;

    @Override
    public void run(String... args) {
        if (computerRepository.findAll().isEmpty()) {
            gpuRepository.deleteAll();
            ramRepository.deleteAll();
            computerSeeder.seed(5);
        }
    }

}
