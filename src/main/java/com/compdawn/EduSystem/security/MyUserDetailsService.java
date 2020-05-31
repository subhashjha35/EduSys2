/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.compdawn.EduSystem.security;

import com.compdawn.EduSystem.entities.Teacher;
import com.compdawn.EduSystem.repositories.TeacherRepository;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private AuthenticatedUser authenticatedUser;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Teacher> oTeacher = teacherRepository.findByUsername(username);
        if (!oTeacher.isPresent()) {
            throw new UsernameNotFoundException(username);
        }
        Teacher teacher = oTeacher.get();
        authenticatedUser.setTeacher(teacher);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(teacher.getRole().toString()));

        return new org.springframework.security.core.userdetails.User(teacher.getUsername(), teacher.getPassword(), grantedAuthorities);
    }
}
