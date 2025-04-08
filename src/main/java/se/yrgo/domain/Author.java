package se.yrgo.domain;

import jakarta.persistence.*;

import javax.security.auth.Subject;
import java.util.*;

import se.yrgo.domain.Book;

@Entity
public class Author {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    private String name;
    private String nationality;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name="AUTHOR_FK")
    private List<Book> books = new ArrayList<>();



    public Author() {}

    public Author(String name,String nationality) {
        this.name = name;
        this.nationality = nationality;
        this.books = new ArrayList<Book>();

    }

    public void addBookToAuthor(Book book) {
        this.books.add(book);

    }

    public String getName() {
        return name;
    }


    public List<Book> getBooks() {
        return this.books;
    }

    @Override
    public String toString() {
        return "Author: " +
                name +
                ", nationality: " + nationality +
                ", books: " + books;
    }
}