package guru.springframework.spring5webapp.controllers;

import guru.springframework.spring5webapp.repositories.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BookController {

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    //chcemy to zmapowac dla specyficznego URL
    //jesli wejdziemy na ten URL ta metoda zostanie odpalona
    @RequestMapping("/books")
    public String getBooks(Model model){
        //jako argument przekazujemy model - to zostanie zwrocone do View

        model.addAttribute("books", bookRepository.findAll());
        //atrybut do modelu

        return "books";
    }
}
