package com.switchfully.digibuggy.users.admins;

import com.switchfully.digibuggy.users.librarians.*;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    private final LibrarianMapper librarianMapper;
    private final LibrarianRepository librarianRepository;

    public AdminService(LibrarianMapper librarianMapper, LibrarianRepository librarianRepository) {
        this.librarianMapper = librarianMapper;
        this.librarianRepository = librarianRepository;
    }

    public LibrarianDto registerLibrarian(RegisterLibrarianDto registerLibrarianDto) {

        Librarian librarian = librarianMapper.dtoToLibrarian(registerLibrarianDto);
        Librarian registeredLibrarian = librarianRepository.registerLibrarian(librarian);

        return librarianMapper.librarianToDto(registeredLibrarian);
    }
}
