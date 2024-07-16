package com.example.fibonacci.provider;

import com.example.fibonacci.domain.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SerieRepository extends JpaRepository<Serie, Long> {
    //libreria de jparepository para el crud
}
