/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Xuerns.backend.mapper;

import com.Xuerns.backend.dto.UsersDto;
import com.Xuerns.backend.entity.Users;
/**
 *
 * @author lenovo
 */
public class UsersMapper {
    
    public static UsersDto mapToUsersDto(Users users) {
        return new UsersDto(
            users.getId(),
            users.getName(),
            users.getEmail(),
            users.getPassword(),
            users.getCreated_at()
        );
    }

    public static Users mapToUsers(UsersDto usersDto) {
        return new Users(
            usersDto.getId(),
            usersDto.getName(),
            usersDto.getEmail(),
            usersDto.getPassword(),
            usersDto.getCreated_at()
        );
    }
}
