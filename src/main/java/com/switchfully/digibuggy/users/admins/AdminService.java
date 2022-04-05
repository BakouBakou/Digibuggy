package com.switchfully.digibuggy.users.admins;

import com.switchfully.digibuggy.users.librarians.*;
import com.switchfully.digibuggy.users.librarians.dtos.LibrarianDto;
import com.switchfully.digibuggy.users.librarians.dtos.RegisterLibrarianDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    private final LibrarianMapper librarianMapper;
    private final LibrarianRepository librarianRepository;
    private final Logger logger = LoggerFactory.getLogger(AdminService.class);

    public AdminService(LibrarianMapper librarianMapper, LibrarianRepository librarianRepository) {
        this.librarianMapper = librarianMapper;
        this.librarianRepository = librarianRepository;
    }

    public LibrarianDto registerLibrarian(RegisterLibrarianDto registerLibrarianDto) {
        logger.info("Started registration librarian");

        Librarian librarian = librarianMapper.dtoToLibrarian(registerLibrarianDto);
        Librarian registeredLibrarian = librarianRepository.registerLibrarian(librarian);

        logger.info("Finished registration librarian");

        return librarianMapper.librarianToDto(registeredLibrarian);
    }
}
