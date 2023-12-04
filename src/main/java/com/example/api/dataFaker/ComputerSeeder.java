package com.example.api.dataFaker;

import com.example.api.model.Computer;
import com.example.api.model.Gpu;
import com.example.api.model.Ram;
import com.example.api.repository.ComputerRepository;
import com.example.api.repository.GpuRepository;
import com.example.api.repository.RamRepository;
import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class ComputerSeeder implements Seeder{

    private final ComputerRepository computerRepository;
    private final RamRepository ramRepository;
    private final GpuRepository gpuRepository;
    private final Faker faker;

    @Override
    public void seed(int objectsToSeed) {
        final Set<Computer> computers = new HashSet<>(objectsToSeed);
        List<Gpu> gpus = generateGpus(objectsToSeed);
        List<Ram> rams = generateRams(objectsToSeed);
        while (objectsToSeed > 0) {
            Computer computer = createComputer(gpus.remove(0), rams.remove(0));
            if (!computers.contains(computer)) {
                objectsToSeed--;
                computerRepository.save(computer);
                computers.add(computer);
            }
        }
    }

    private Computer createComputer(Gpu gpu, Ram ram) {
        return Computer.builder()
                .name(faker.pokemon().name().replaceAll("\\s", ""))
                .type(faker.food().fruit().replaceAll("\\s", ""))
                .hardDiskCapacity(faker.number().numberBetween(100,3000))
                .enclosureName(faker.food().vegetable().replaceAll("\\s", ""))
                .cpuName(faker.superhero().name().replaceAll("\\s", ""))
                .gpu(gpu)
                .powerSupply(faker.number().numberBetween(100,900))
                .ram(ram)
                .price(faker.number().numberBetween(2000,5000))
                .build();
    }

    private List<Gpu> generateGpus(int size) {
        final List<Gpu> gpus = new ArrayList<>(size);
        while(size > 0) {
            Gpu gpu = createGpu();
            if (!gpus.contains(gpu)) {
                size--;
                gpuRepository.save(gpu);
                gpus.add(gpu);
            }
        }
        return gpus;
    }

    private List<Ram> generateRams(int size) {
        final List<Ram> rams = new ArrayList<>(size);
        while(size > 0) {
            Ram ram = createRam();
            if (!rams.contains(ram)) {
                size--;
                ramRepository.save(ram);
                rams.add(ram);
            }
        }
        return rams;
    }

    private Gpu createGpu() {
        return Gpu.builder()
                .name(faker.dragonBall().character().replaceAll("\\s", ""))
                .vRamCapacity(faker.number().numberBetween(2,24))
                .build();
    }

    private Ram createRam() {
        return Ram.builder()
                .name(faker.food().sushi().replaceAll("\\s", ""))
                .ramCapacity(faker.number().numberBetween(2,128))
                .memoryRate(faker.number().numberBetween(1300,4500))
                .build();
    }
}
