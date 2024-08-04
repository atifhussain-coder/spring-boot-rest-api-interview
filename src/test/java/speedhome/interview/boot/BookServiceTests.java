package speedhome.interview.boot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import speedhome.interview.boot.model.Book;
import speedhome.interview.boot.repository.BookRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import speedhome.interview.boot.service.BookService;

public class BookServiceTests {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddBook() {
        Book book = new Book();
        book.setTitle("Test Book");
        book.setAuthor("Author");
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        Book result = bookService.addBook(book);

        assertEquals("AVAILABLE", result.getStatus());
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    public void testUpdateBook() {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("Updated Book");
        book.setAuthor("Author");
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        Book result = bookService.updateBook(book);

        assertEquals(book, result);
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    public void testDeleteBook() {
        Long bookId = 1L;

        bookService.deleteBook(bookId);

        verify(bookRepository, times(1)).deleteById(bookId);
    }

    @Test
    public void testGetBookById() {
        Long bookId = 1L;
        Book book = new Book();
        book.setId(bookId);
        book.setTitle("Test Book");
        book.setAuthor("Author");
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));

        Optional<Book> result = bookService.getBookById(bookId);

        assertTrue(result.isPresent());
        assertEquals(book, result.get());
        verify(bookRepository, times(1)).findById(bookId);
    }

    @Test
    public void testGetAllBooks() {
        Book book1 = new Book();
        book1.setTitle("Book 1");
        book1.setAuthor("Author 1");

        Book book2 = new Book();
        book2.setTitle("Book 2");
        book2.setAuthor("Author 2");

        List<Book> books = Arrays.asList(book1, book2);
        when(bookRepository.findAll()).thenReturn(books);

        List<Book> result = bookService.getAllBooks();

        assertEquals(2, result.size());
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    public void testBorrowBook() {
        Long bookId = 1L;
        Book book = new Book();
        book.setId(bookId);
        book.setStatus("AVAILABLE");
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        Book result = bookService.borrowBook(bookId);

        assertNotNull(result);
        assertEquals("BORROWED", result.getStatus());
        verify(bookRepository, times(1)).findById(bookId);
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    public void testBorrowBookNotAvailable() {
        Long bookId = 1L;
        Book book = new Book();
        book.setId(bookId);
        book.setStatus("BORROWED");
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));

        Book result = bookService.borrowBook(bookId);

        assertNull(result);
        verify(bookRepository, times(1)).findById(bookId);
        verify(bookRepository, times(0)).save(any(Book.class));
    }

    @Test
    public void testReturnBook() {
        Long bookId = 1L;
        Book book = new Book();
        book.setId(bookId);
        book.setStatus("BORROWED");
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        Book result = bookService.returnBook(bookId);

        assertNotNull(result);
        assertEquals("AVAILABLE", result.getStatus());
        verify(bookRepository, times(1)).findById(bookId);
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    public void testReturnBookNotBorrowed() {
        Long bookId = 1L;
        Book book = new Book();
        book.setId(bookId);
        book.setStatus("AVAILABLE");
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));

        Book result = bookService.returnBook(bookId);

        assertNull(result);
        verify(bookRepository, times(1)).findById(bookId);
        verify(bookRepository, times(0)).save(any(Book.class));
    }
}
