package com.example.api.repository;

import com.example.api.model.Ram;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RamRepository extends JpaRepository<Ram, Long> {

    Optional<Ram> findRamByName(String name);

    boolean existsByName(String name);
}
