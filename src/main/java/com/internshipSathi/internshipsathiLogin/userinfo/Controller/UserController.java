package com.internshipSathi.internshipsathiLogin.userinfo.Controller;

import com.internshipSathi.internshipsathiLogin.userinfo.model.UserInfo;
import com.internshipSathi.internshipsathiLogin.userinfo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/save")
    public ResponseEntity<String> addUser(@RequestBody UserInfo userInfo){
        return userService.addUser(userInfo);
    }


}
