package ru.Bogun.spring_project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.Bogun.spring_project.DAO.PersonDAO;
import ru.Bogun.spring_project.models.Person;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PersonDAO personDAO;

    @Autowired
    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }
    @GetMapping()
    public String showAll(Model model){
        model.addAttribute("people", personDAO.showAll());
        return "people/showAll";
    }

    @GetMapping("/{id}")
    public String showByIndex(@PathVariable("id") int id, Model model){
        model.addAttribute("person",personDAO.showByIndex(id));
        return "people/showByIndex";
    }
    @GetMapping("/new")
    public String newPerson(Model model){
        model.addAttribute("person", new Person());
        return "people/new";
    }
    @PostMapping("/new")
    public String createPerson(@ModelAttribute("person") Person person){
        personDAO.createPerson();
        return "redirect:/people";
    }
    @GetMapping("/{id}/editPerson")
    public String editPerson(Model model, @PathVariable("id") int id){
        model.addAttribute("person",personDAO.showByIndex(id));
        return "people/editPeople";
    }
    @PatchMapping("/{id}")
    public String updatePerson(@ModelAttribute("person") Person person,@PathVariable("id") int id){
        personDAO.updatePerson(id,person);
        return "redirect:/people";
    }
    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable("id") int id){
        personDAO.deletePerson(id);
        return "redirect:/people";
    }



}
