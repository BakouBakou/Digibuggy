package com.switchfully.digibuggy.users.librarians;

import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class LibrarianDatabase {
    private  final ConcurrentHashMap<String, Librarian> librarians = new ConcurrentHashMap<>();

    public Librarian save(Librarian librarian) {
        librarians.put(librarian.getID(), librarian);
        return librarian;
    }


}
