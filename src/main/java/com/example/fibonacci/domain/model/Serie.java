package com.example.fibonacci.domain.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "serie")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Serie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String serie;

    private String horaActual;


}
