package com.switchfully.digibuggy.books.dtos;

public class LendABookDto {
    private final String memberId;
    private final String isbn;

    public LendABookDto(String memberId, String isbn) {
        this.memberId = memberId;
        this.isbn = isbn;
    }

    public String getMemberId() {
        return this.memberId;
    }

    public String getISBN() {
        return this.isbn;
    }
}
