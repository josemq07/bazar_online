package com.bazaronline.bazaronline.service;

import com.bazaronline.bazaronline.model.Client;
import com.bazaronline.bazaronline.repository.IClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ClientService implements IClientService{
    @Autowired
    private IClientRepository cliRepo;
    @Override
    public Client getClient(Long id_Clients) {
        return cliRepo.findById(id_Clients).orElse(null);
    }

    @Override
    public List<Client> getClients() {
        return cliRepo.findAll();
    }

    @Override
    public void saveClient(Client client) { cliRepo.save(client); }

    @Override
    public void deleteClient(Long id_client) { cliRepo.deleteById(id_client); }

    @Override
    public void editClient(Long id_clientToEdit, String new_name, String new_surname, String new_dni ) {


            Client existingClient = this.getClient(id_clientToEdit);

            if(existingClient != null) {

                existingClient.setClient_name(new_name);
                existingClient.setClient_surname(new_surname);
                existingClient.setClient_dni(new_dni);

                this.saveClient(existingClient);
            }
        }

    }




