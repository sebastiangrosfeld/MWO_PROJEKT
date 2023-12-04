package com.example.api.views;

import com.example.api.dto.RamDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RamForm {
    private String name;
    private Integer ramCapacity;
    private Integer memoryRate;

    public RamForm(RamDto ramDto) {
        name = ramDto.name();
        ramCapacity = ramDto.ramCapacity();
        memoryRate = ramDto.memoryRate();
    }
}
