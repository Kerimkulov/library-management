package kz.iitu.librarymanagement.repository;

import kz.iitu.librarymanagement.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Component
public interface BookRepository extends JpaRepository<Book,Long> {
    @Query("SELECT b FROM Book b WHERE b.book_quantity > 0")
    List<Book> availableBookList();
}
