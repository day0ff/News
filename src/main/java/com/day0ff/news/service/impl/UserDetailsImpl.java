package com.day0ff.news.service.impl;

import com.day0ff.news.entity.Roles;
import com.day0ff.news.entity.Users;
import com.day0ff.news.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(value = "userDetailsImpl")
public class UserDetailsImpl implements UserDetailsService{
    @Autowired
    private UsersRepository usersRepository;

    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Users users = usersRepository.findByUserName(userName);
        if (users == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        if(!users.getEnabled()){
            throw new UsernameNotFoundException("User is disabled.");
        }
        return new org.springframework.security.core.userdetails.User(
                users.getUserName(), users.getPassword(), getAuthority(users.getRoles()));
    }

    private List getAuthority(List<Roles> roles) {
        List<GrantedAuthority> grantedAuthoritiesList = new ArrayList<>();
        for (Roles role: roles) {
            grantedAuthoritiesList.add(new SimpleGrantedAuthority(role.getRole()));
        }
        return grantedAuthoritiesList;
    }

}
