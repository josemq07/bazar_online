package com.bazaronline.bazaronline.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id_client;

    private String client_name;

    private String client_surname;

    private String client_dni;




    public Client() {
    }


    public Client(Long id_client, String client_name, String client_surname, String client_dni) {
        this.id_client = id_client;
        this.client_name = client_name;
        this.client_surname = client_surname;
        this.client_dni = client_dni;
    }

    public Long getId_client() {
        return id_client;
    }

    public void setId_client(Long id_client) {
        this.id_client = id_client;
    }

    public String getClient_name() {
        return client_name;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }

    public String getClient_surname() {
        return client_surname;
    }

    public void setClient_surname(String client_surname) {
        this.client_surname = client_surname;
    }

    public String getClient_dni() {
        return client_dni;
    }

    public void setClient_dni(String client_dni) {
        this.client_dni = client_dni;
    }
}