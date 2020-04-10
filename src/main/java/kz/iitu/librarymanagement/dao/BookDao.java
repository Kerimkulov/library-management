package kz.iitu.librarymanagement.dao;

import kz.iitu.librarymanagement.entity.Book;
import kz.iitu.librarymanagement.entity.Genre;
import kz.iitu.librarymanagement.entity.Library;
import kz.iitu.librarymanagement.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
@Service
public class BookDao {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> bookList(){
        return bookRepository.findAll();
    }
    public List<Book> availableBookList(){
        return bookRepository.availableBookList();
    }
    public Book getById(Long id){
        return bookRepository.findById(id).get();
    }
    public void newBook(Book book){
        bookRepository.save(book);
    }
    public void removeBook(Book book){
        bookRepository.delete(book);
    }

}
