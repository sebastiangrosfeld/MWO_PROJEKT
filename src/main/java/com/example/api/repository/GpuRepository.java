package com.example.api.repository;

import com.example.api.model.Gpu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GpuRepository extends JpaRepository<Gpu, Long> {

    Optional<Gpu> findGpuByName(String name);

    boolean existsByName(String name);
}
