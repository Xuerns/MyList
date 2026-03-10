/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Xuerns.backend.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Xuerns.backend.dto.LoginDto;
import com.Xuerns.backend.dto.UsersDto;
import com.Xuerns.backend.mapper.UsersMapper;
import com.Xuerns.backend.repository.UsersRepository;
import com.Xuerns.backend.security.JwtUtil;
import com.Xuerns.backend.service.UsersService;
import com.Xuerns.backend.entity.Users;
import com.Xuerns.backend.exception.ResourceNotFoundException;

import lombok.AllArgsConstructor;
/**
 *
 * @author lenovo
 */
@Service
@AllArgsConstructor
public class UsersServiceImpl implements UsersService {
    
    private UsersRepository usersRepository;
    private PasswordEncoder passwordEncoder;
    private JwtUtil jwtUtil;

    @Override
    public UsersDto registerUsers(UsersDto usersDto) {

        // Cek apakah akun / email sudah terdaftar
        Users existingUsers = usersRepository.findByEmail(usersDto.getEmail());
        if (existingUsers != null) {
            throw new RuntimeException("Email Sudah terdaftar");
        }

        // Hash password sebelum disimpan
        String hashedPassword = passwordEncoder.encode(usersDto.getPassword());
        usersDto.setPassword(hashedPassword);

        // simpan ke database
        Users users = UsersMapper.mapToUsers(usersDto);
        Users savedUsers = usersRepository.save(users);

        // Sembunyikan password dari response API
        UsersDto savedUsersDto = UsersMapper.mapToUsersDto(savedUsers);
        savedUsersDto.setPassword(null);

        return savedUsersDto;
    }

    @Override
    public String loginUsers(LoginDto loginDto) {
        Users existUsers = usersRepository.findByEmail(loginDto.getEmail());

        if (existUsers == null) {
            throw new RuntimeException("Email belum terdaftar");
        }

        boolean isMatching = passwordEncoder.matches(loginDto.getPassword(), existUsers.getPassword());

        if (!isMatching) {
            throw new RuntimeException("Password Salah!");
        }

        return jwtUtil.generateToken(existUsers.getEmail());
    }

    @Override
    public UsersDto getUsersById(Long usersId) {
        Users users = usersRepository.findById(usersId)
            .orElseThrow(()  -> new ResourceNotFoundException("Users is not with id: " + usersId + "is not exist"));
        return UsersMapper.mapToUsersDto(users);
    }

    @Override
    public List<UsersDto> getAllUsers() {
        List<Users> users = usersRepository.findAll();
        return users.stream().map((user) -> UsersMapper.mapToUsersDto(user))
            .collect(Collectors.toList());
    }

    @Override
    public UsersDto updateUsers(Long usersId, UsersDto updateUsers) {

        Users users = usersRepository.findById(usersId).orElseThrow(
            () -> new ResourceNotFoundException("Users with ID: " + usersId + "is not exist")
        );

        users.setName(updateUsers.getName());
        users.setEmail(updateUsers.getEmail());

        // hash password baru
        String hashedPassword = passwordEncoder.encode(updateUsers.getPassword());
        users.setPassword(hashedPassword);

        Users updatedUsers = usersRepository.save(users);
        return UsersMapper.mapToUsersDto(updatedUsers);
    }

    @Override
    public void deleteUsers(Long usersId) {
        usersRepository.findById(usersId).orElseThrow(
            () -> new ResourceNotFoundException("Users with ID: " + usersId + "is not exist")
        );

        usersRepository.deleteById(usersId);
    }
}
