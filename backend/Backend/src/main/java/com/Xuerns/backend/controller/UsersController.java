/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Xuerns.backend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Xuerns.backend.dto.LoginDto;
import com.Xuerns.backend.dto.UsersDto;
import com.Xuerns.backend.service.UsersService;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author lenovo
 */
@CrossOrigin("*") // CORS Policy
@AllArgsConstructor
@RestController
@RequestMapping("/api/users") // Endpoint
public class UsersController {

    private UsersService usersService;

    // Buat register Users REST API
    @PostMapping("/register")
    public ResponseEntity<UsersDto> registerUsers(@RequestBody UsersDto usersDto) {
        UsersDto savedUsers = usersService.registerUsers(usersDto);
        return new ResponseEntity<>(savedUsers, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginUsers(@RequestBody LoginDto loginDto) {
        String token = usersService.loginUsers(loginDto);

        Map<String, String> response = new HashMap<>();
        response.put("token", token);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    

    // Buat Get Users by ID REST API
    @GetMapping("{id}") // Endpoint + id
    public ResponseEntity<UsersDto> getUsersById(@PathVariable("id") Long usersId) {
        UsersDto usersDto = usersService.getUsersById(usersId);
        return ResponseEntity.ok(usersDto);
    }

    // Buat Get All user REST API
    @GetMapping
    public ResponseEntity<List<UsersDto>> getAllUsers() {
        List<UsersDto> users = usersService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    // Buat PUT endpoint untuk Update Users
    @PutMapping("{id}")
    public ResponseEntity<UsersDto> updateUsers(@PathVariable("id") Long usersId, @RequestBody UsersDto updateUsers) {
        UsersDto usersDto = usersService.updateUsers(usersId, updateUsers);
        return ResponseEntity.ok(usersDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUsers(@PathVariable("id") Long usersId) {
        usersService.deleteUsers(usersId);
        return ResponseEntity.ok("Users With ID: " + usersId + " is Succecs deleted");
    }
}
