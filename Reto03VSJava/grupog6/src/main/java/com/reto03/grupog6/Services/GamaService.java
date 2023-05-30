package com.reto03.grupog6.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reto03.grupog6.Entities.Gama;
import com.reto03.grupog6.Repository.GamaRepository;

@Service
public class GamaService {
    @Autowired
    private GamaRepository gamaRepository;

    public Gama addGama(Gama gama) {
        Boolean bGrabar = true;

        if (gama.getName() == null)
            bGrabar = false;
        if (gama.getDescription() == null)
            bGrabar = false;

        if (bGrabar)
            return gamaRepository.addGama(gama);
        else
            return gama;

    }


    public List<Gama> getAll() {
        return (List<Gama>) gamaRepository.getAll();
    }

    public Gama updGama(Gama gama) {
        return gamaRepository.updGama(gama);
    }

    public Gama getGama(Integer idGama) {
        return gamaRepository.getGama(idGama);
    }

    public void delGama(Integer idGama) {
        gamaRepository.delGama(idGama);
    }
    
}
