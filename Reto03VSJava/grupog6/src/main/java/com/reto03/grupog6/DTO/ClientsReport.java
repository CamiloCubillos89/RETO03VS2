package com.reto03.grupog6.DTO;

import java.io.Serializable;

import com.reto03.grupog6.Entities.Client;

public class ClientsReport implements Serializable {
    private Integer total;
    private Client client; 
    
    public ClientsReport() {
    }
    public ClientsReport(Integer total, Client client) {
        this.total = total;
            this.client = client;
    }
    public Integer getTotal() {
        return total;
    }
    public void setTotal(Integer total) {
        this.total = total;
    }
    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }  
    
}
