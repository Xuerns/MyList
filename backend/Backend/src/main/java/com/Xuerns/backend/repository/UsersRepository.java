/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Xuerns.backend.repository;
import com.Xuerns.backend.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author lenovo
 */
public interface UsersRepository extends JpaRepository<Users, Long> {
    Users findByEmail(String email);
}
