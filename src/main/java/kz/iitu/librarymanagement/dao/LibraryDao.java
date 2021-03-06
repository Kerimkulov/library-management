package kz.iitu.librarymanagement.dao;

import kz.iitu.librarymanagement.entity.Client;
import kz.iitu.librarymanagement.entity.Library;
import kz.iitu.librarymanagement.repository.ClientRepository;
import kz.iitu.librarymanagement.repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
@Service
public class LibraryDao {
    @Autowired
    private LibraryRepository libraryRepository;

    public List<Library> getAll(){
        return libraryRepository.findAll();
    }
    public Library getById(Long id){
        return libraryRepository.findById(id).get();
    }
    public void newLibrary(Library library){
        libraryRepository.save(library);
    }
    public void removeLibrary(Library library){
        libraryRepository.delete(library);
    }

}
