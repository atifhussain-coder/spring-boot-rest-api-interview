package speedhome.interview.boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import speedhome.interview.boot.model.Book;
import speedhome.interview.boot.repository.BookRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public Book addBook(Book book) {
        book.setStatus("AVAILABLE");
        return bookRepository.save(book);
    }

    public Book updateBook(Book book) {
        return bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book borrowBook(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent() && "AVAILABLE".equals(book.get().getStatus())) {
            book.get().setStatus("BORROWED");
            return bookRepository.save(book.get());
        }
        return null;
    }

    public Book returnBook(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent() && "BORROWED".equals(book.get().getStatus())) {
            book.get().setStatus("AVAILABLE");
            return bookRepository.save(book.get());
        }
        return null;
    }
}
