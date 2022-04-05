package com.switchfully.digibuggy.users.librarians;

import org.springframework.stereotype.Component;

@Component
public class LibrarianMapper {
    public LibrarianDto librarianToDto(Librarian librarian) {
        return new LibrarianDto(librarian.getID(), librarian.getFirstName(), librarian.getLastName(), librarian.getEmailAddress());
    }

    public Librarian dtoToLibrarian(RegisterLibrarianDto registerLibrarianDto) {
        return new Librarian(registerLibrarianDto.getFirstName(), registerLibrarianDto.getLastName(), registerLibrarianDto.getEmailAddress());
    }
}
