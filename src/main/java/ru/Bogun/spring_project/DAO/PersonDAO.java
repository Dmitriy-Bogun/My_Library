package ru.Bogun.spring_project.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.Bogun.spring_project.models.Person;

import java.util.List;

@Component
public class PersonDAO {
 private final JdbcTemplate jdbcTemplate;
    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List <Person> index(){
        return jdbcTemplate.query("SELECT * FROM information_schema.person", new BeanPropertyRowMapper<>(Person.class));
    }
    public Person show(int id){
        return jdbcTemplate.query("SELECT * FROM information_schema.Person WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }
    public void create(Person person){
        jdbcTemplate.update("INSERT INTO Person (full_name, age) VALUES(?, ?)",person.getFullName(),person.getAge());
    }

    public void update(int id, Person updatedPerson){
        jdbcTemplate.update("UPDATE TABLE Person SET full_name=?, age=? WHERE id=?",
                updatedPerson.getFullName(), updatedPerson.getAge(),id);
    }
    public void delete(int id){
        jdbcTemplate.update("DELETE FROM Person WHERE id=?",id);
    }

}
