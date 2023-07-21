package com.internshipSathi.internshipsathiLogin.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.internshipSathi.internshipsathiLogin.userinfo.model.UserInfo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter

public class UserPrinciple implements UserDetails {

    private static final long serialVersionUID=1L;

    private final long id;
    private final String firstName;

    private final String middleName;

    private final String lastName;



    private final String email;
    @JsonIgnore
    private final String password;

    private final Collection<? extends GrantedAuthority> authorities;

    public UserPrinciple(long id, String firstName, String middleName, String lastName, String email, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id=id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;

        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }




    public static UserPrinciple build(UserInfo user){
        List<GrantedAuthority> authorities=user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName().name())
                ).collect(Collectors.toList());

    return new UserPrinciple(

            user.getId(),
            user.getFirstName(),
            user.getMiddleName(),
             user.getLastName(),
            user.getEmail(),
            user.getPassword(),
            authorities


    );

    }
}
