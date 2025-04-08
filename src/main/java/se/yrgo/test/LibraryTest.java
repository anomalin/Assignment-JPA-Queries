package se.yrgo.test;


import jakarta.persistence.*;
import se.yrgo.domain.Author;
import se.yrgo.domain.Book;
import se.yrgo.domain.Reader;

import java.util.List;

public class LibraryTest {

public static EntityManagerFactory emf = Persistence.createEntityManagerFactory("databaseConfig");

    public static void main(String[] args) {

     EntityManager em = emf.createEntityManager();
     EntityTransaction tx = em.getTransaction();
     tx.begin();


     //Uppgift 1:
    createData();

    //Uppgift 2:
     Query q1 = em.createQuery("select author.books from Author as author where author.name='Karl Ove Knausgård'");
     List<Book> booksFromKO = q1.getResultList();
    for(Book book : booksFromKO) {
     System.out.println(book);
    }
    //Uppgift 3:
    Book heartists = em.find(Book.class, 2);
    Query q2 = em.createQuery(" from Reader reader where :title member of reader.books").setParameter("title", heartists);
    List<Reader> heartistReaders = q2.getResultList();
    for (Reader reader : heartistReaders) {
     System.out.println(reader);
    }

    //Uppgift 4:
     List<Author> resultList = em.createQuery("select distinct a from Author a join a.books b join b.readers r ").getResultList();
    for (Author author : resultList) {
     System.out.println(author);
    }

    //Uppgift 5:
     List<Object[]> results = em.createQuery("select a.name, count(b) from Author a join a.books b group by a.name").getResultList();
    for (Object[] result : results) {
     System.out.println(result[0] + " - " + result[1] + " books");
    }

     //Uppgift 6:
    List<Book> bookByGenre = em.createNamedQuery("searchByGenre", Book.class).setParameter("genre", "Fiction").getResultList();
    for (Book book : bookByGenre) {
        System.out.println(book);
    }

     tx.commit();
     em.close();

    }

    public static void createData() {
     EntityManager em = emf.createEntityManager();
     EntityTransaction tx = em.getTransaction();
     tx.begin();
     Author aut1 = new Author("Johanna Pietikäinen", "Swedish");
     Author aut2 = new Author("Sally Rooney", "Irish");
     Author aut3 = new Author("Karl Ove Knausgård", "Norweigan");

     Book book1 = new Book("Intermezzo", "Fiction", "2024");
     Book book2 = new Book("Conversations with Friends", "Fiction", "2017");
     Book book3 = new Book("Heartists", "Biography", "2019");
     Book book4 = new Book("Min kamp 1", "Autobiography", "2010");
     Book book5 = new Book("Morgonstjärnan", "Fiction", "2020");

     aut1.addBookToAuthor(book3);
     aut2.addBookToAuthor(book1);
     aut2.addBookToAuthor(book2);
     aut3.addBookToAuthor(book4);
     aut3.addBookToAuthor(book5);

     Reader reader1 = new Reader("Malin Sundberg", "anomalins@gmail.com");
     Reader reader2 = new Reader("Samuel Petersson", "hello@samuelpetersson.com");
     Reader reader3 = new Reader("Elisabeth Snork", "frusnork@snork.se");

     reader1.addBooktoReader(book3);
     reader1.addBooktoReader(book2);
     reader2.addBooktoReader(book4);
     reader2.addBooktoReader(book5);
     reader3.addBooktoReader(book3);
     reader3.addBooktoReader(book5);

     em.persist(aut1);
     em.persist(aut2);
     em.persist(aut3);
     em.persist(reader1);
     em.persist(reader2);
     em.persist(reader3);

     tx.commit();
     em.close();
    }


}

