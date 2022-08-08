package ru.Bogun.spring_project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.Bogun.spring_project.DAO.BookDAO;
import ru.Bogun.spring_project.DAO.PersonDAO;
import ru.Bogun.spring_project.models.Book;
import ru.Bogun.spring_project.models.Person;

import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookDAO bookDAO;
    private final PersonDAO personDAO;
@Autowired
    public BookController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
    this.personDAO = personDAO;
}

    @GetMapping()
    public String index(Model model){
    model.addAttribute("books",bookDAO.index());
    return "books/index";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person) {
        model.addAttribute("book", bookDAO.show(id));

        Optional<Person> bookOwner = bookDAO.getBookOwner(id);

        if (bookOwner.isPresent())
            model.addAttribute("owner", bookOwner.get());
        else
            model.addAttribute("people", personDAO.index());

        return "books/show";
    }
    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book){
    //model.addAttribute("book", new Book());
    return "books/new";
    }
    @PostMapping()
    public String createBook(@ModelAttribute("book") Book Book){
    bookDAO.createBook(Book);
    return "redirect:/books";
    }
    @GetMapping("/{id}/edit")
    public String editBook(Model model,@PathVariable("id") int id){
    model.addAttribute("book", bookDAO.show(id));
    return "books/edit";
    }
    @PatchMapping("/{id}")
    public String updateBook(@ModelAttribute("book") Book book, @PathVariable("id") int id){
    bookDAO.updateBook(id,book);
    return "redirect:/books";
    }
    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") int id){
    bookDAO.deleteBook(id);
    return "redirect:/books";
    }
    @PatchMapping("/{id}/release")
    public String release(@PathVariable("id") int id) {
        bookDAO.release(id);
        return "redirect:/books/" + id;
    }

    @PatchMapping("/{id}/assign")
    public String assign(@PathVariable("id") int id, @ModelAttribute("person") Person selectedPerson) {
        bookDAO.assign(id, selectedPerson);
        return "redirect:/books/" + id;
    }





}
