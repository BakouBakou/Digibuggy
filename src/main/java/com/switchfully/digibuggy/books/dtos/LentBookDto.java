package com.switchfully.digibuggy.books.dtos;

import java.time.LocalDate;
import java.util.UUID;

public class LentBookDto {
    private final String memberId;
    private final String isbn;
    private final String lendingId;
    private final LocalDate dueDate;

    public LentBookDto (String memberId, String isbn, String lendingId, LocalDate dueDate) {
        this.memberId = memberId;
        this.isbn = isbn;
        this.lendingId = lendingId;
        this.dueDate = dueDate;
    }


    public String getLendingId() {
        return lendingId;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getMemberId() {
        return memberId;
    }
}
