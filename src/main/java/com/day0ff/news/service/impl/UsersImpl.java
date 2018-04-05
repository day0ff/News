package com.day0ff.news.service.impl;

import com.day0ff.news.entity.UserRoles;
import com.day0ff.news.entity.Users;
import com.day0ff.news.repository.UserRolesRepository;
import com.day0ff.news.repository.UsersRepository;
import com.day0ff.news.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Service(value = "usersServiceImpl")
public class UsersImpl implements UserDetailsService, UsersService {
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private UserRolesRepository userRolesRepository;

    @Override
    public List<Users> findAll() {
        return usersRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Users findByNameAndPassword(String userName, String password) {
        return usersRepository.findByUserNameAndPassword(userName, password);
    }

    @Transactional(readOnly = true)
    @Override
    public Users findByName(String userName) {
        return usersRepository.findByUserName(userName);
    }


    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Users users = findByName(userName);
        if (users == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(
                users.getUserName(), users.getPassword(), getAuthority(users));
    }

    private List getAuthority(Users users) {
        List<GrantedAuthority> roles = new ArrayList<>();
        /*for (UserRoles userRoles : users.getUserRolesList()) {
            roles.add(new SimpleGrantedAuthority(userRoles.getRole()));
        }*/
        List<UserRoles> userRolesList = userRolesRepository.findByUsersUserName(users.getUserName());
        for (UserRoles userRoles : userRolesList) {
            roles.add(new SimpleGrantedAuthority(userRoles.getRole()));
        }
        return roles;
    }
}
