package com.switchfully.digibuggy.users.librarians;

public class LibrarianMapper {
    public LibrarianDto librarianToDto(Librarian librarian) {
        return new LibrarianDto(librarian.getID(), librarian.getFirstName(), librarian.getLastName(), librarian.getEmailAddress());
    }
}
