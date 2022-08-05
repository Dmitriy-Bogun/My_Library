package ru.Bogun.spring_project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.Bogun.spring_project.DAO.BookDAO;
import ru.Bogun.spring_project.models.Book;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookDAO bookDAO;
@Autowired
    public BookController(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @GetMapping()
    public String showAll(Model model){
    model.addAttribute("books",bookDAO.showAll());
    return "books/showAll";
    }
    @GetMapping("/{id}")
    public String showByIndex(@PathVariable("id") int id, Model model){
    model.addAttribute("book", bookDAO.showByIndex(id));
    return "books/showByIndex";
    }
    @GetMapping("/new")
    public String edit(Model model, Book book){
    model.addAttribute("book", new Book());
    return "books/new";
    }
    @PostMapping("/new")
    public String createBook(@ModelAttribute("book") Book book){
    bookDAO.createBook(book);
    return "redirect:/books";
    }
    @GetMapping("/{id}/edit")
    public String editBook(Model model,@PathVariable("id") int id){
    model.addAttribute("book", bookDAO.showByIndex(id));
    return "books/editBook";
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



}
