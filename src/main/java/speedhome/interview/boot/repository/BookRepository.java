package speedhome.interview.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import speedhome.interview.boot.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
