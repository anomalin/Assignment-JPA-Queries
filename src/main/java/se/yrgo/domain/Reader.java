package se.yrgo.domain;

import jakarta.persistence.*;


import java.util.ArrayList;
import java.util.List;


@Entity
public class Reader {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    private String name;
    private String email;

    @ManyToMany
    private List<Book> books = new ArrayList<>();


    public Reader() {}

    public Reader(String name, String email) {
        this.name = name;
        this.email = email;
        this.books = new ArrayList<Book>();
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void addBooktoReader(Book book) {
        this.books.add(book);
        book.getReaders().add(this);
    }

    public List<Book> getBooks() {
        return this.books;
    }

    @Override
    public String toString() {
        return "Reader: " +
                 name +
                ", email: " + email +
                ", books: " + books;
    }
}

