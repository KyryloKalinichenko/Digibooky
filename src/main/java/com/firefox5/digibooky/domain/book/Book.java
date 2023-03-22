package com.firefox5.digibooky.domain.book;



public class Book {
    private String title;
    private final String isbn;
    private final int id;
    private static int counter;
    private Author author;
    private String smallSummary;
    private boolean available;

    public Book(String title, String isbn, Author author, String smallSummary, boolean available) {
        this.title = title;
        this.isbn = isbn;
        id = counter++;
        this.author = author;
        this.smallSummary = smallSummary;
        this.available = available;
    }

    public String getTitle() {
        return title;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getId() {
        return id;
    }

    public static int getCounter() {
        return counter;
    }

    public Author getAuthor() {
        return author;
    }

    public String getSmallSummary() {
        return smallSummary;
    }

    public boolean getAvailability(){
        return available;
    }


    public void setAuthor(Author author) {
        this.author = author;
    }

    public void setSmallSummary(String smallSummary) {
        this.smallSummary = smallSummary;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public void setAvailability(boolean availability){
        this.available = availability;
    }
}
