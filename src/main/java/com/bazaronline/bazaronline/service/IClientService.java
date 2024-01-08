package com.bazaronline.bazaronline.service;

import com.bazaronline.bazaronline.model.Client;
import com.bazaronline.bazaronline.model.Product;

import java.util.List;

public interface IClientService {

    public Client getClient(Long id_Clients);

    public List<Client> getClients();

    public void saveClient(Client client);

    public void deleteClient(Long id_client);

        public void editClient(Long id_clientToEdit, String new_name, String new_surname, String new_dni );
}