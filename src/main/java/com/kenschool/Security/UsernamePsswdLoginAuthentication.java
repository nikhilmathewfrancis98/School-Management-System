package com.kenschool.Security;

import com.kenschool.Model_POJOs.Roles;
import com.kenschool.Repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
@Slf4j
public class UsernamePsswdLoginAuthentication implements AuthenticationProvider {
    @Autowired(required = false)
    PersonRepository personRepository;
    @Autowired(required = false)
    PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        var email = authentication.getName();
        var psswd = authentication.getCredentials().toString();
        var person = personRepository.getByemail(email);
        log.info(email + " " + psswd);
        if (null != person && person.getPersonId() > 0 && passwordEncoder.matches(psswd, person.getPwd())) {
            // Explore this UsernamePasswordAuthenticationToken and the getGrandedAuthorities parameter in UsernamePasswordAuthenticationToken
            return new UsernamePasswordAuthenticationToken(email, null, getGrandedAuthorities(person.getRoles()));
        } else {
            throw new BadCredentialsException("Invalid Credentials");
        }
    }

    private Collection<? extends GrantedAuthority> getGrandedAuthorities(Roles roles) {
        // We are returning the list of roles because a user can have more than one roles
        List<GrantedAuthority> grandedRoles = new ArrayList<>();
        // We added the ROLE_ prefix coz the spring security will always add roles like ROLE_ADMIN either add this directly in the db like this or do the java code for this
        grandedRoles.add(new SimpleGrantedAuthority("ROLE_" + roles.getRoleName()));
        return grandedRoles;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
