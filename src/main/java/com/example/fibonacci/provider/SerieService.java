package com.example.fibonacci.provider;

import com.example.fibonacci.domain.model.Serie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SerieService {
    @Autowired
    private SerieRepository serieRepository;

    public Optional<Serie> getSerieById(Long accountId) {
        return  serieRepository.findById(accountId);
    }

    public List<Serie> getSeries() {
        return serieRepository.findAll();
    }

    public Serie saveSerie(Serie serie){
        return serieRepository.save(serie);
    }
}
