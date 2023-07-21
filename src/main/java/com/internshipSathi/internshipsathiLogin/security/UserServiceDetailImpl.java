package com.internshipSathi.internshipsathiLogin.security;

import com.internshipSathi.internshipsathiLogin.userinfo.Repository.UserInfoRepo;
import com.internshipSathi.internshipsathiLogin.userinfo.model.UserInfo;
import com.internshipSathi.internshipsathiLogin.userinfo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserServiceDetailImpl implements UserDetailsService {

    @Autowired
    private UserInfoRepo userInfoRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {


    UserInfo user=userInfoRepo.findByEmail(email)
            .orElseThrow(()->new UsernameNotFoundException("User not found"));

        return UserPrinciple.build(user);
    }
}
