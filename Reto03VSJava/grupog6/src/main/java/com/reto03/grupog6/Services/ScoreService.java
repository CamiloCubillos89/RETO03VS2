package com.reto03.grupog6.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reto03.grupog6.Entities.Score;
import com.reto03.grupog6.Repository.ScoreRepository;

@Service
public class ScoreService {
    @Autowired
    private ScoreRepository scoreRepository;

    public Score addScore(Score score) {
        Boolean bGrabar = true;

        if(score.getResult() == null)
            bGrabar = false;
        
        if(score.getDescription() == null)
            bGrabar = false;

        if(score.getReservation().getIdReservation() == null)
            bGrabar = false;
        
        if (bGrabar)
            return scoreRepository.addScore(score);
        else
            return score;

    }


    public List<Score> getAll() {
        return (List<Score>) scoreRepository.getAll();
    }

    public Score updScore(Score score) {
        return scoreRepository.updScore(score);
    }

    public Score getScore(Integer idScore) {
        return scoreRepository.getScore(idScore);
    }

    public void delScore(Integer idScore) {
        scoreRepository.delScore(idScore);
    }
    
}
