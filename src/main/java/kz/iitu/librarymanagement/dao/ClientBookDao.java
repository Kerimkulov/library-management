package kz.iitu.librarymanagement.dao;

import kz.iitu.librarymanagement.entity.Book;
import kz.iitu.librarymanagement.entity.Client;
import kz.iitu.librarymanagement.entity.ClientBook;
import kz.iitu.librarymanagement.event.BringBookDateEvent;
import kz.iitu.librarymanagement.repository.BookRepository;
import kz.iitu.librarymanagement.repository.ClientBookRepository;
import kz.iitu.librarymanagement.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Component
@Service
public class ClientBookDao implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private ClientBookRepository clientBookRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ClientRepository clientRepository;

    public List<ClientBook> clientBookList(){
        return clientBookRepository.findAll();
    }
    public ClientBook getById(Long id){
        return clientBookRepository.findById(id).get();
    }
    public void takeBook(Book book, Client client){
        Date date = new Date();
        LocalDate bringDate = LocalDate.now().plusMonths(3);
        if(book.getBook_quantity() == 0 ){
            System.out.println("Quantity = 0");
        }
        else {
            ClientBook clientBook = new ClientBook();
            clientBook.setBook(book);
            clientBook.setClient(client);
            clientBook.setTakeDate(date);
            clientBook.setBringDate(bringDate);
            book.decreaseBookQuantity();
            bookRepository.save(book);
            clientBookRepository.save(clientBook);
            this.eventPublisher.publishEvent(new BringBookDateEvent(this,clientBook));
        }

    }
    public void bringBook(Book book, Client client){

        for(ClientBook clientBook:clientBookRepository.findAll()){
            if(clientBook.getClient().getId() == client.getId() ) {
                if (clientBook.getBook().getId() == book.getId()) {
                    client.bringBook(book);
                    book.increaseBookQuantity();
                    clientRepository.save(client);
                    bookRepository.save(book);
                    clientBookRepository.delete(clientBook);
                    System.out.println("Book was returned");
                }
                else {
                    System.out.println("No book");
                }
            }
            else {
                System.out.println("No Client");
            }
        }


    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.eventPublisher = applicationEventPublisher;
    }
}
