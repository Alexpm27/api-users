package com.example.securitywithjwt.security.credentials;

import com.example.securitywithjwt.persistence.models.User;
import com.example.securitywithjwt.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CredentialDetailsServiceImpls implements UserDetailsService {

    @Autowired
    private IUserService service;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = service.get(username);
        return new CredentialDetailsImpl(user);
    }

}
