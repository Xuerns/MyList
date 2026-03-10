/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Xuerns.backend.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
/**
 *
 * @author lenovo
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsersDto {
    private Long id;
    private String name;
    private String email;
    private String password;
    private LocalDateTime created_at;
}
