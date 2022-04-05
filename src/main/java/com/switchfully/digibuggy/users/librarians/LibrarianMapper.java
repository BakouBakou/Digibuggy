package com.switchfully.digibuggy.users.librarians;

import com.switchfully.digibuggy.users.librarians.dtos.LibrarianDto;
import com.switchfully.digibuggy.users.librarians.dtos.RegisterLibrarianDto;
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
