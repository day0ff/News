package com.day0ff.news.service.impl;

import com.day0ff.news.entity.UserRoles;
import com.day0ff.news.entity.Users;
import com.day0ff.news.repository.UserRolesRepository;
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

    @Autowired
    private UserRolesRepository userRolesRepository;

    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Users users = usersRepository.findByUserName(userName);
        if (users == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(
                users.getUserName(), users.getPassword(), getAuthority(users.getUserName()));
    }

    private List getAuthority(String userName) {
        List<GrantedAuthority> roles = new ArrayList<>();
        List<UserRoles> userRolesList = userRolesRepository.findByUsersUserName(userName);
        for (UserRoles userRoles : userRolesList) {
            roles.add(new SimpleGrantedAuthority(userRoles.getRole()));
        }
        return roles;
    }

}
