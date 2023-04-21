package ro.cognizant.coderun2023.repo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ro.cognizant.coderun2023.domain.Book;
import ro.cognizant.coderun2023.domain.validator.BookNotFoundException;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
public class BookController {
    private final BookRepo repo;

    public BookController(BookRepo repo) {
        this.repo = repo;
    }

    @GetMapping("/books")
    List<Book> getAll(){
        return repo.findAll();
    }

    @PostMapping("/books")
    Book addBook(@RequestBody Long id, String bookName, String author, List<String> publisherList){
        Book bookToSave = new Book(id, bookName, author, publisherList);
        return repo.save(bookToSave);
    }

    @GetMapping("/books/{id}")
    Book getBookById(Long id){
        return repo.findById(id).orElseThrow(() -> new BookNotFoundException(id));
    }

    @GetMapping("/books/search")
    List<Book> searchBook(String name){
        List<Book> books = new ArrayList<>();
        for(Book b: repo.findAll()){
            if(Objects.equals(b.getBookName(), name) || Objects.equals(b.getAuthor(), name) || publisherInList(name, b.getPublisherList()))
                books.add(b);
        }
        return books;
    }

    boolean publisherInList(String name, List<String> publishers){
        for(String publisher: publishers){
            if(Objects.equals(publisher, name))
                return true;
        }
        return false;
    }


}
