package com.medialab.rental.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomAuthenticationProvider implements AuthenticationProvider {
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;

   @Autowired
   CustomAuthenticationProvider(PasswordEncoder passwordEncoder, UserDetailsService userDetailsService) {
       this.passwordEncoder = passwordEncoder;
       this.userDetailsService = userDetailsService;
   }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
       UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
       UserDetails userDetails = userDetailsService.loadUserByUsername(token.getName());

       boolean matches = userDetails != null && passwordEncoder.matches(token.getCredentials().toString(), userDetails.getPassword());
       if(!matches){
           throw new AuthenticationServiceException("Invalid username or password");
       }

       return new UsernamePasswordAuthenticationToken(token.getPrincipal(), token.getCredentials(), userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
