package com.kenschool.Services;

import com.kenschool.Constants.KenSchoolConstants;
import com.kenschool.Model_POJOs.PersonPojo;
import com.kenschool.Model_POJOs.Roles;
import com.kenschool.Repository.PersonRepository;
import com.kenschool.Repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    @Autowired(required = false)
    RolesRepository rolesRepository;
    @Autowired(required = false)
    PersonRepository personRepository;
    @Autowired(required = false)
    PasswordEncoder passwordEncoder;

    public boolean CreateNewUser(PersonPojo personPojo) {
        boolean isSaved = false;
        Roles roles = rolesRepository.getByRoleName(KenSchoolConstants.STUDENT_ROLE);
        personPojo.setRoles(roles);
        personPojo.setPwd(passwordEncoder.encode(personPojo.getPwd()));
        personPojo = personRepository.save(personPojo);
        if (null != personPojo && personPojo.getPersonId() > 0) {
            isSaved = true;
        } else {
            return false;
        }
        return isSaved;
    }
}
