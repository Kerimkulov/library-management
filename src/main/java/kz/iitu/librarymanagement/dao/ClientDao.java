package kz.iitu.librarymanagement.dao;

import kz.iitu.librarymanagement.entity.Book;
import kz.iitu.librarymanagement.entity.Client;
import kz.iitu.librarymanagement.entity.ClientBook;
import kz.iitu.librarymanagement.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
@Service
public class ClientDao {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> clientList(){
        return clientRepository.findAll();
    }
    public Client getById(Long id)
    {
        return clientRepository.findById(id).get();
    }
    public void newClient(Client client){
        clientRepository.save(client);
        System.out.println("Client was added");
    }
    public void removeClient(Client client){
        clientRepository.delete(client);
    }
}
