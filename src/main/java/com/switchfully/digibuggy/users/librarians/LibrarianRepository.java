package com.switchfully.digibuggy.users.librarians;

import org.springframework.stereotype.Repository;

@Repository
public class LibrarianRepository {
    private final LibrarianDatabase librarianDatabase;

    public LibrarianRepository(LibrarianDatabase librarianDatabase) {
        this.librarianDatabase = librarianDatabase;
    }

    public Librarian registerLibrarian(Librarian librarian) {
        return librarianDatabase.save(librarian);
    }
}
