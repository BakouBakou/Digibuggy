package com.switchfully.digibuggy.security;

import com.switchfully.digibuggy.security.exception.UnauthorizatedException;
import com.switchfully.digibuggy.security.exception.UnknownUserException;
import com.switchfully.digibuggy.security.exception.WrongPasswordException;
import com.switchfully.digibuggy.users.admins.Admin;
import com.switchfully.digibuggy.users.admins.AdminDatabase;
import com.switchfully.digibuggy.users.admins.AdminRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class SecurityService {

    private final AdminRepository adminRepository;
    private final Logger logger = LoggerFactory.getLogger(SecurityService.class);

    public SecurityService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public void validateAuthorization(String authorization, Feature feature) {
        UsernamePassword usernamePassword = getUsernamePassword(authorization);

        logger.info("Start validation for: " + usernamePassword.getUsername());

        User user = getUser(usernamePassword.getUsername());
        if(user == null) {
            logger.error("Unknown user" + usernamePassword.getUsername());
            throw new UnknownUserException();
        }
        if(!user.doesPasswordMatch(usernamePassword.getPassword())) {
            logger.error("Password does not match for user " + usernamePassword.getUsername());
            throw new WrongPasswordException();
        }
        if(!user.canHaveAccessTo(feature)) {
            logger.error("User " + usernamePassword.getUsername() + " does not have access to " + Feature.REGISTER_LIBRARIAN);
            throw new UnauthorizatedException();
        }
        logger.info("Validation successful for: " + usernamePassword.getUsername());
    }

    private User getUser(String username) {
        Admin admin = adminRepository.get(username);

        if (admin == null)  return null;

        return new User(admin.getUsername(), admin.getPassword(), Role.ADMIN);
    }

    private UsernamePassword getUsernamePassword(String authorization) {
        String decodedUsernameAndPassword = new String(Base64.getDecoder().decode(authorization.substring("Basic ".length())));
        String username = decodedUsernameAndPassword.substring(0, decodedUsernameAndPassword.indexOf(":"));
        String password = decodedUsernameAndPassword.substring(decodedUsernameAndPassword.indexOf(":") + 1);
        return new UsernamePassword(username, password);
    }

}
