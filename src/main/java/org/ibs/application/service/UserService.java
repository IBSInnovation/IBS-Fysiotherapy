package org.ibs.application.service;

import org.ibs.data.UserRepository;
import org.ibs.domain.User;
//import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    /*public Optional<User> getUserOptional(OidcUser oidcUser) {
        return userRepository.findByAuth0(oidcUser.getClaims().get("sub").toString());
    }*/
}
