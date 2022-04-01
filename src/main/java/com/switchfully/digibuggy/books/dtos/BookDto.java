package com.switchfully.digibuggy.books.dtos;

import java.util.Objects;

public class BookDto {
    private String isbn;
    private String title;
    private String authorFirstname;
    private String authorLastname;
    private String summary;

    public BookDto(String isbn, String title, String authorFirstname, String authorLastname, String summary) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookDto bookDto = (BookDto) o;
        return Objects.equals(isbn, bookDto.isbn) && Objects.equals(title, bookDto.title) && Objects.equals(authorFirstname, bookDto.authorFirstname) && Objects.equals(authorLastname, bookDto.authorLastname) && Objects.equals(summary, bookDto.summary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn, title, authorFirstname, authorLastname, summary);
    }
}
