package com.internshipSathi.internshipsathiLogin.userinfo.service;

import com.internshipSathi.internshipsathiLogin.userinfo.Repository.RoleRepo;
import com.internshipSathi.internshipsathiLogin.userinfo.Repository.UserInfoRepo;
import com.internshipSathi.internshipsathiLogin.userinfo.model.Role;
import com.internshipSathi.internshipsathiLogin.userinfo.model.RoleName;
import com.internshipSathi.internshipsathiLogin.userinfo.model.UserInfo;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class UserService {

@Autowired
private UserInfoRepo userInfoRepo;

@Autowired
private RoleRepo roleRepo;

@Autowired
PasswordEncoder passwordEncoder;



public ResponseEntity<String> addUser(UserInfo  user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userInfoRepo.save(user);
        return ResponseEntity.ok("User save successfully.");
}


@PostConstruct
private void saveUser(){

        Optional<Role> optional=roleRepo.findByRoleName(RoleName.SUPER_ADMIN);
        if (!optional.isPresent()) {
                UserInfo userInfo = new UserInfo();

                userInfo.setFirstName("Anil");
                userInfo.setMiddleName("");
                userInfo.setLastName("Shrestha");
                userInfo.setEmail("anil@gmail.com");
                userInfo.setPassword(passwordEncoder.encode("anil@1234"));

                Set<Role> roleList = new HashSet<>();

                Role role = new Role();

                role.setRoleName(RoleName.SUPER_ADMIN);
                roleList.add(role);


                userInfo.setRoles(roleList);
                userInfoRepo.save(userInfo);


        }
    }



}
