package com.reto03.grupog6.CrudRepository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.reto03.grupog6.Entities.Reservation;

public interface ReservationCrudRepository extends CrudRepository<Reservation, Integer> {
    
    public List<Reservation> findAllByStartDateAfterAndStartDateBefore(Date dateOne, Date dateTwo);
    
    /*@Query(value="Select count(*) From Reservaciones where status = ?",nativeQuery=true)
    public Integer CountByStatus(String status);*/

    //Obtener los estados de las reservaciones
    @Query(value="select count(*) from reservations where status = ?", nativeQuery = true)
    public Integer CountByStatus(String status);

    //Retornar la lista de clientes para reservaciones en "completed"
    @Query(value=   "SELECT c.idClient FROM clients c " +
                    "INNER JOIN reservations r ON c.idClient = r.idClient " +
                    "WHERE r.status = 'completed' " +
                    "GROUP BY c.idClient " +
                    "ORDER BY COUNT(r.idReservation) DESC", nativeQuery = true)
    public List<Object[]> OrderedByCompleted();

} 