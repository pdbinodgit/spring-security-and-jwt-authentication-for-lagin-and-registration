package com.internshipSathi.internshipsathiLogin.userinfo.Repository;

import com.internshipSathi.internshipsathiLogin.userinfo.model.Role;
import com.internshipSathi.internshipsathiLogin.userinfo.model.RoleName;
import com.internshipSathi.internshipsathiLogin.userinfo.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserInfoRepo extends JpaRepository<UserInfo,Long> {

    public Optional<UserInfo> findByEmail(String email);


}
