package com.kenschool.Repository;

import com.kenschool.Model_POJOs.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Integer> {
    //    Roles findByrole_name(String rolename);
    Roles getByRoleName(String role_name);
}
