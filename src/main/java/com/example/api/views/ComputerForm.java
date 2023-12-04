package com.example.api.views;

import com.example.api.dto.ComputerDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ComputerForm {
    String name;
    String type;
    String enclosureName;
    String cpuName;
    String ramName;
    Integer hardDiskCapacity;
    String gpuName;
    Integer powerSupply;
    Integer price;

    public ComputerForm(ComputerDto computerDto) {
        name = computerDto.name();
        type = computerDto.type();
        enclosureName = computerDto.enclosureName();
        cpuName = computerDto.cpuName();
        ramName = computerDto.ram().name();
        hardDiskCapacity = computerDto.hardDiskCapacity();
        gpuName =computerDto.gpu().name();
        powerSupply = computerDto.powerSupply();
        price = computerDto.price();
    }
}
