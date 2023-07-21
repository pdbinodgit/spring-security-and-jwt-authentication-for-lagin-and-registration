package com.internshipSathi.internshipsathiLogin.userinfo.Repository;

import com.internshipSathi.internshipsathiLogin.userinfo.model.Role;
import com.internshipSathi.internshipsathiLogin.userinfo.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepo extends JpaRepository<Role,Long> {
    Optional<Role> findByRoleName(RoleName admin);
}
