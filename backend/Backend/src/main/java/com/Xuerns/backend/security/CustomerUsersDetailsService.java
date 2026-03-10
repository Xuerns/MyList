package com.Xuerns.backend.security;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Xuerns.backend.entity.Users;
import com.Xuerns.backend.repository.UsersRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomerUsersDetailsService implements UserDetailsService {
    
    private UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users user = usersRepository.findByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("User tidak ditemukan");
        }

        return new User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }
}
