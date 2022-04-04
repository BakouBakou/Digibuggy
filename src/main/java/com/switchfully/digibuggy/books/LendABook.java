package com.switchfully.digibuggy.books;

import java.time.LocalDate;

public class LendABook {
    private String lendingId;
    private LocalDate date;

    public LendABook(String lendingId, LocalDate date) {
        this.lendingId = lendingId;
        this.date = date;
    }
}
