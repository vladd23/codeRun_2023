package ro.cognizant.coderun2023.repo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ro.cognizant.coderun2023.domain.Book;
import ro.cognizant.coderun2023.domain.Order;
import ro.cognizant.coderun2023.domain.validator.BookNotFoundException;

import java.util.*;
import java.util.stream.Collectors;

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
            if(Objects.equals(b.getBookName(), name) || Objects.equals(b.getAuthor(), name) || publisherList(name, b.getPublisherList()))
                books.add(b);
        }
        return books;
    }

    boolean publisherList(String name, List<String> publishers){
        for(String publisher: publishers){
            if(Objects.equals(publisher, name))
                return true;
        }
        return false;
    }

    String publisherInList(String name){
        List<Book> publishers = searchBook(name);
        for(Book publisher: publishers)
            if(publisherList(name, Collections.singletonList(publisher.getBookName())))
                for(String p: publisher.getPublisherList()){
                    if(Objects.equals(p, name))
                        return name;
        }
        return null;
    }

    List<Book> sortByName(String name, Order ord) {
        List<Book> colList = searchBook(name);
        if(ord == Order.ASC)
            return colList.stream()
                .sorted(Comparator.comparing(Book::getBookName))
                .collect(Collectors.toList());
        else if(ord == Order.DESC)
            return colList.stream()
                    .sorted(Comparator.comparing(Book::getBookName).reversed())
                    .collect(Collectors.toList());
        return null;
    }

    List<Book> sortByAuthor(String name, Order ord) {
        List<Book> colList = searchBook(name);

        if(ord == Order.ASC)
            return colList.stream()
                .sorted(Comparator.comparing(Book::getAuthor))
                .collect(Collectors.toList());
        else if(ord == Order.DESC)
            return colList.stream()
                    .sorted(Comparator.comparing(Book::getAuthor).reversed())
                    .collect(Collectors.toList());
        return null;
    }
//    List<Book> sortByPublisher(String name, Order ord) {
//        List<Book> colList = searchBook(name);
//
//        if(ord == Order.ASC)
//            return colList.stream()
//                .sorted(Comparator.comparing(publisherInList(name)))
//                .collect(Collectors.toList());
//        else if(ord == Order.DESC)
//            return colList.stream()
//                    .sorted(Comparator.comparing(publisherInList(name)).reversed())
//                    .collect(Collectors.toList());
//
//    }

}
