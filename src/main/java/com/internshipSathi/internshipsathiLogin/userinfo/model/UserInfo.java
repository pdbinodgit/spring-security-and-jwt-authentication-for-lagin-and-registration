package com.internshipSathi.internshipsathiLogin.userinfo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class UserInfo {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String middleName;
    private String lastName;

    private String email;
    private String password;

    @ManyToMany( fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "user_has_role",joinColumns = @JoinColumn(name="user_info_id"),inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles=new HashSet<>();


}
