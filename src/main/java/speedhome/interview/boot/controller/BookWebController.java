package speedhome.interview.boot.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import speedhome.interview.boot.model.Book;
import speedhome.interview.boot.model.Member;
import speedhome.interview.boot.service.BookService;

import java.util.List;

@Controller
@RequestMapping("/web/books")
public class BookWebController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public String listBooks(Model model) {
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "book_list"; // Thymeleaf template name (book_list.jsp)
    }

    @GetMapping("/new")
    public String createBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "book-form";
    }

    @PostMapping
    public String saveBook(@ModelAttribute("book") Book book) {
        bookService.addBook(book);
        return "redirect:/web/books";
    }

    @GetMapping("/edit/{id}")
    public String editBookForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("book", bookService.getBookById(id).orElse(new Book()));
        return "book-form";
    }

    @PostMapping("/update")
    public String updateMember(@ModelAttribute("book") Book book) {
        bookService.updateBook(book);
        return "redirect:/web/books";
    }

    @PostMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return "redirect:/web/books";
    }
}
