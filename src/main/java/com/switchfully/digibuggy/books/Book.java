package com.switchfully.digibuggy.books;

public class Book {
    private final String isbn;
    private final String title;
    private final String authorFirstname;
    private final String authorLastname;
    private final String summary;

    public Book(String isbn, String title, String authorFirstname, String authorLastname, String summary) {
        this.isbn = isbn;
        this.title = title;
        this.authorFirstname = authorFirstname;
        this.authorLastname = authorLastname;
        this.summary = summary;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthorFirstname() {
        return authorFirstname;
    }

    public String getAuthorLastname() {
        return authorLastname;
    }

    public String getSummary() {
        return summary;
    }
}
