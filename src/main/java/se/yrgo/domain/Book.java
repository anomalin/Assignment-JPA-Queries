package se.yrgo.domain;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity

public class Book
{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String title;
    private String genre;
    private String publicationYear;

    @ManyToMany(mappedBy="books")
    private List<Reader> readers;


    public Book() {}

    public Book(String title, String genre, String publicationYear) {
        this.title = title;
        this.genre = genre;
        this.publicationYear = publicationYear;
        this.readers = new ArrayList<Reader>();
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public int getId() {
        return id;
    }

    public String getPublicationYear() {
        return publicationYear;
    }

    public List<Reader> getReaders() {
        return readers;
    }

    @Override
    public String toString() {
        return "Book: " +
                  title  +
                ", genre: " + genre +
                ", publicationyear: " + publicationYear;
    }
}
