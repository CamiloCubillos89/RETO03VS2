package com.reto03.grupog6.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.reto03.grupog6.CrudRepository.ScoreCrudRepository;
import com.reto03.grupog6.Entities.Reservation;
import com.reto03.grupog6.Entities.Score;

@Repository
public class ScoreRepository {
    @Autowired
    private ScoreCrudRepository scoreCrudRepository;

    //retorna todas las entidades en BD
    public List<Score> getAll() {
        return (List<Score>) scoreCrudRepository.findAll();
    }

    //guarda la entidad en BD
    public Score addScore(Score score) {
        if (score.getIdScore() == null || score.getIdScore() == 0)
            return scoreCrudRepository.save(score);
        else
            return score;
    }

    //Determina si la calificación existe en BD
    private Score existScore(Integer idScore) {
        Optional<Score> objScore = scoreCrudRepository.findById(idScore);
        Score objScoreRespuesta;

        if (objScore.isEmpty())
            objScoreRespuesta = null;
        else
            objScoreRespuesta = objScore.get();
        
        return objScoreRespuesta;

    }
    
    //actualiza datos
    public Score updScore(Score score) {
        Score objScore;
        Reservation reservation = new Reservation();

        objScore = existScore(score.getIdScore());
        if (objScore == null)
            return score;
        else {
            if (score.getResult() != null)
                objScore.setResult(score.getResult());

            if (score.getDescription() != null)
                objScore.setDescription(score.getDescription());
            
            if(score.getReservation().getIdReservation() != null ){
                reservation.setIdReservation(score.getReservation().getIdReservation());
                objScore.setReservation(reservation);
            }

            return scoreCrudRepository.save(objScore);
        }
    }

    //elimina datos
    public void delScore(Integer idScore) {
        Score objScore;

        objScore = existScore(idScore);
        if (objScore != null)
            scoreCrudRepository.delete(objScore);
    }

    //trae datos
    public Score getScore(Integer idScore) {
        Score objScore;

        objScore = existScore(idScore);
        if (objScore != null)
            return objScore;
        else
            return null;
        
    }
}
