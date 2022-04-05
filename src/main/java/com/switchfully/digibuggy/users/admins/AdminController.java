package com.switchfully.digibuggy.users.admins;


import com.switchfully.digibuggy.security.Feature;
import com.switchfully.digibuggy.security.SecurityService;
import com.switchfully.digibuggy.users.librarians.dtos.LibrarianDto;
import com.switchfully.digibuggy.users.librarians.dtos.RegisterLibrarianDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/admin")
public class AdminController {

    private final AdminService adminService;

    private final SecurityService securityService;

    public AdminController(AdminService adminService, SecurityService securityService) {
        this.adminService = adminService;
        this.securityService = securityService;
    }

    @PostMapping(path = "/librarians", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public LibrarianDto registerLibrarian(@RequestBody RegisterLibrarianDto registerLibrarianDto) {


        return adminService.registerLibrarian(registerLibrarianDto);
    }

}
