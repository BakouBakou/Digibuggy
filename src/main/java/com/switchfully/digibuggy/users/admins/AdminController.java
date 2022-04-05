package com.switchfully.digibuggy.users.admins;

import com.switchfully.digibuggy.users.librarians.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/admin")
public class AdminController {

    @Autowired
    private LibrarianDatabase librarianDatabase;

    @PostMapping(path = "/librarians", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public LibrarianDto registerLibrarian(@RequestBody RegisterLibrarianDto registerLibrarianDto) {

        Librarian librarian = new Librarian(registerLibrarianDto.getFirstName(), registerLibrarianDto.getLastName(), registerLibrarianDto.getEmailAddress());
        LibrarianMapper mapper = new LibrarianMapper();
        LibrarianDto librarianDto = mapper.librarianToDto(librarianDatabase.save(librarian));
        return librarianDto;
    }

}
