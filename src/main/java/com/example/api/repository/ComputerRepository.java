package com.example.api.repository;

import com.example.api.model.Computer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ComputerRepository extends JpaRepository<Computer, Long> {

    Optional<Computer> findComputerByName(String name);
    boolean existsByName(String name);
}
