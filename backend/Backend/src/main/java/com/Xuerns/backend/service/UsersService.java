/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Xuerns.backend.service;

import com.Xuerns.backend.dto.LoginDto;
import com.Xuerns.backend.dto.UsersDto;
import java.util.List;
/**
 *
 * @author lenovo
 */
public interface UsersService {
    UsersDto registerUsers(UsersDto usersDto);

    String loginUsers(LoginDto loginDto);

    UsersDto getUsersById(Long usersId);

    List<UsersDto> getAllUsers();

    UsersDto updateUsers(Long usersId, UsersDto updateUsers);

    void deleteUsers(Long usersId);
}
