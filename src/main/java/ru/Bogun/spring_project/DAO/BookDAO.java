package ru.Bogun.spring_project.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.Bogun.spring_project.models.Book;

import java.util.List;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;
@Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List <Book> showAll(){
    return jdbcTemplate.query("SELECT * FROM Book",new BeanPropertyRowMapper<>(Book.class));
    }

    public Book showByIndex(int id){
    return jdbcTemplate.query("SELECT * FROM Book WHERE id=?", new Object[]{id},
            new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
    }
    public void createBook(Book book){
    jdbcTemplate.update("INSERT INTO Book (title, author, year) VALUES(?, ?, ?)",
            book.getTitle(), book.getAuthor(), book.getYear());
    }
    public void updateBook(int id, Book updatedBook){
    jdbcTemplate.update("UPDATE TABLE Book SET title=?, author=?, year=? WHERE id=?",
            updatedBook.getTitle(),updatedBook.getAuthor(), updatedBook.getYear(), id);
    }
    public void deleteBook(int id){
    jdbcTemplate.update("DELETE FROM Book WHERE id=?", id);
    }
}
