package com.switchfully.digibuggy.books.dtos;

import java.util.Objects;

public class BookOverviewDto {
    private final String isbn;
    private final String title;
    private final String authorFirstname;
    private final String authorLastname;

    public BookOverviewDto(String isbn, String title, String authorFirstname, String authorLastname) {
        this.isbn = isbn;
        this.title = title;
        this.authorFirstname = authorFirstname;
        this.authorLastname = authorLastname;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookOverviewDto)) return false;
        BookOverviewDto that = (BookOverviewDto) o;
        return Objects.equals(getIsbn(), that.getIsbn()) && Objects.equals(getTitle(), that.getTitle()) && Objects.equals(getAuthorFirstname(), that.getAuthorFirstname()) && Objects.equals(getAuthorLastname(), that.getAuthorLastname());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIsbn(), getTitle(), getAuthorFirstname(), getAuthorLastname());
    }
}
