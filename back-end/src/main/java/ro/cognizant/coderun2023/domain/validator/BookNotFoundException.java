package ro.cognizant.coderun2023.domain.validator;

public class BookNotFoundException extends RuntimeException{
    public BookNotFoundException(Long id) {
        super("Could not find book " + id);
    }
}
