package speedhome.interview.boot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import speedhome.interview.boot.model.Book;

@DataJpaTest
public class BookTest {

    @Autowired
    private TestEntityManager entityManager;

    private Book book;

    @BeforeEach
    public void setUp() {
        book = new Book("Test Title", "Test Author", "AVAILABLE");
    }

    @Test
    public void testPersist() {
        Book savedBook = entityManager.persistFlushFind(book);

        assertNotNull(savedBook.getId());
        assertNotNull(savedBook.getCreatedAt());
        assertNull(savedBook.getUpdatedAt());
        assertEquals("Test Title", savedBook.getTitle());
        assertEquals("Test Author", savedBook.getAuthor());
        assertEquals("AVAILABLE", savedBook.getStatus());
    }

    @Test
    public void testUpdate() {
        Book savedBook = entityManager.persistFlushFind(book);

        // Update book details
        savedBook.setTitle("Updated Title");
        savedBook.setAuthor("Updated Author");
        savedBook.setStatus("BORROWED");
        savedBook = entityManager.persistFlushFind(savedBook);

        assertNotNull(savedBook.getUpdatedAt());
        assertEquals("Updated Title", savedBook.getTitle());
        assertEquals("Updated Author", savedBook.getAuthor());
        assertEquals("BORROWED", savedBook.getStatus());
    }
}

