package com.reto03.grupog6.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.reto03.grupog6.CrudRepository.GamaCrudRepository;
import com.reto03.grupog6.Entities.Gama;

@Repository//Spring lo gestiona como un componente para llamar metodos, acciones, funciones necesarias para BD
public class GamaRepository {
    @Autowired//Automaticamente Java usa la memoria que requiere
    private GamaCrudRepository gamaCrudRepository;

    //retorna todas las entidades en BD
    public List<Gama> getAll(){
        return (List<Gama>)gamaCrudRepository.findAll();
    }

    //guarda la entidad en BD
    public Gama addGama(Gama gama){
        if (gama.getIdGama() == null ||(gama.getIdGama() == 0))
            return gamaCrudRepository.save(gama);
        else
            return gama;
    }

    //Determina si el Gama existe en BD
    private Gama existGama(Integer idGama){
        Optional<Gama> objGama = gamaCrudRepository.findById(idGama);
        Gama objGamaRespuesta;

        System.out.println("Entra a Repo-Gama " + idGama);

        if(objGama.isEmpty()){
            System.out.println("Entra a Repo-Gama Null" + idGama);
            objGamaRespuesta = null;
        }
        else{
            System.out.println("Entra a Repo-Gama No Null" + idGama);
            objGamaRespuesta = objGama.get();
        }
            return objGamaRespuesta;
    }

    //Actualiza datos
    public Gama updGama(Gama gama){
        Gama objGama;

        objGama = existGama(gama.getIdGama());

        if (objGama==null)
            return gama;
        else{
            if (gama.getName() != null)
                objGama.setName(gama.getName());
            if (gama.getDescription() != null)
                objGama.setDescription(gama.getDescription());

            return gamaCrudRepository.save(gama);
            }
    }

    //Elimina datos
    public void delGama(Integer idGama){
        Gama objGama;

        objGama = existGama(idGama);
        if (objGama!=null)
            gamaCrudRepository.delete(objGama); 
    }

    //Trae datos
    public Gama getGama(Integer idGama){
        Gama objGama;

        objGama = existGama(idGama);
        if (objGama==null)
            return objGama; 
        else 
            return null;
    }


}
