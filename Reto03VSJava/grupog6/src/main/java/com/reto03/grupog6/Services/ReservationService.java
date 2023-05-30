package com.reto03.grupog6.Services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reto03.grupog6.DTO.ClientsReport;
import com.reto03.grupog6.DTO.StatusReport;
import com.reto03.grupog6.Entities.Client;
import com.reto03.grupog6.Entities.Reservation;
import com.reto03.grupog6.Repository.ReservationRepository;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    public Reservation addReservation(Reservation reservation) {
        Boolean bGrabar = true;

        if (reservation.getStartDate() == null)
            bGrabar = false;

        if (reservation.getDevolutionDate() == null)
            bGrabar = false;

        if (reservation.getClient().getIdClient() == null)
            bGrabar = false;  

        if (reservation.getCar().getIdCar() == null)
            bGrabar = false;

        if (reservation.getStatus() == null) {
            reservation.setStatus("created");
        }

        if (bGrabar)
            return reservationRepository.addReservation(reservation);
        else
            return reservation;
    }


    public List<Reservation> getAll() {
        return (List<Reservation>) reservationRepository.getAll();
    }

    public Reservation updReservation(Reservation reservation) {
        return reservationRepository.updReservation(reservation);
    }

    public Reservation getReservation(Integer idReservation) {
        return reservationRepository.getReservation(idReservation);
    }

    public void delReservation(Integer idReservation) {
        reservationRepository.delReservation(idReservation);
    }

    public List<Reservation> getReportReservation(String dateOne, String dateTwo) {
        SimpleDateFormat convertidor = new SimpleDateFormat("yyyy-MM-dd");
        Date duno = new Date();
        Date ddos = new Date();

        try {
            duno = convertidor.parse(dateOne);
            ddos = convertidor.parse(dateTwo);

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (duno.before(ddos)) {
            return reservationRepository.getReportReservation(duno, ddos);
        } else
            return new ArrayList<>();

    }

    //Retorna reporte de estados de reservación
    public StatusReport getStatusReport() {
        List<Reservation> listReservations = reservationRepository.getAll();
        StatusReport statusReport = new StatusReport();
        Integer completas = 0;
        Integer canceladas = 0;

        for (Reservation reservation : listReservations) {
            if (reservation.getStatus().equalsIgnoreCase("completed"))
                completas++;
            if (reservation.getStatus().equalsIgnoreCase("cancelled"))
                canceladas++;
        }
        statusReport.setCancelled(canceladas);
        statusReport.setCompleted(completas);
        return statusReport;
    }

    //Consulta del Reporte de estados de reservación
    public StatusReport getStatusReportQuery() {
        StatusReport statusReport = new StatusReport();
        Integer completas = reservationRepository.getReportStatusbyQuery("completed");
        Integer canceladas = reservationRepository.getReportStatusbyQuery("cancelled");

        statusReport.setCancelled(canceladas);
        statusReport.setCompleted(completas);
        return statusReport;
    }

    //Reporte de clientes
    public List<ClientsReport> getClientsReport() {
        List<Client> clients;
        List<ClientsReport> clientsReport = new ArrayList<ClientsReport>();
        Integer total;

        clients = reservationRepository.getReportTopClients();
        for (Client client : clients) {
            total = client.getReservations().size();
            clientsReport.add(new ClientsReport(total,client));
        }
        return clientsReport;
    }
    
}