package ro.cognizant.coderun2023.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.cognizant.coderun2023.domain.Book;
public interface BookRepo extends JpaRepository<Book, Long> {

}
