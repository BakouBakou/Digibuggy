package com.switchfully.digibuggy.users.admins;

import com.switchfully.digibuggy.security.UsernamePassword;
import com.switchfully.digibuggy.users.librarians.Librarian;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class AdminDatabase {
    private final ConcurrentHashMap<String, Admin> admins = new ConcurrentHashMap<>();

    public AdminDatabase() {
        Admin admin = new Admin("Bugs","Bunny", "bugs@bunny.com", new UsernamePassword("Bugs","Bunny"));
        admins.put(admin.getUsername(),admin);

    }

    public Admin get(String username){
        return admins.get(username);
    }
}
