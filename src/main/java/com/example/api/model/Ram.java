package com.example.api.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ram {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @EqualsAndHashCode.Exclude
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    @EqualsAndHashCode.Exclude
    private Integer ramCapacity;

    @Column(nullable = false)
    @EqualsAndHashCode.Exclude
    private Integer memoryRate;
}
