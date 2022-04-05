package com.switchfully.digibuggy.users.admins;

import org.springframework.stereotype.Repository;

@Repository
public class AdminRepository {
    private final AdminDatabase adminDatabase;

    public AdminRepository(AdminDatabase adminDatabase) {
        this.adminDatabase = adminDatabase;
    }

    public Admin get(String username) {
        return adminDatabase.get(username);
    }
}
