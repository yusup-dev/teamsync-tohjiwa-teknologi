package com.tohjiwa.teamsync.server.service.spring;

import com.tohjiwa.teamsync.server.model.SecurityUser;
import com.tohjiwa.teamsync.server.dao.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JpaUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    public JpaUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        try {
            var user = userRepository.findByUsername(usernameOrEmail);
            if(user.isPresent()) {
                return new SecurityUser(user.get());
            } else {
                user = userRepository.findByEmail(usernameOrEmail);
                if(user.isPresent()) {
                    return new SecurityUser(user.get());
                } else {
                    throw  new UsernameNotFoundException("Username or Email not found : " + usernameOrEmail);
                }
            }
        } catch (Exception e) {
            throw new UsernameNotFoundException("Username ord Email not found : " + usernameOrEmail);
        }
    }
}
