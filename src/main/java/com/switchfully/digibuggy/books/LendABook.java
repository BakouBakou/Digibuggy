package com.switchfully.digibuggy.books;

import java.util.UUID;

public class LendABook {
    private final String memberId;
    private final String isbn;
    private final String lendingId;
    private final LocalDate dueDate;
//    private LocalDate date;


    public LendABook(String memberId, String isbn) {
        this.memberId = memberId;
        this.isbn = isbn;
        this.lendingId = UUID.randomUUID().toString();
        this.dueDate = LocalDate.now().plusWeeks(3);
    }


    public String getLendingId() {
        return lendingId;
    }
}
