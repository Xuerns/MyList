package com.Mylist.demo.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Mylist.demo.Users.dto.AuthResponse;
import com.Mylist.demo.Users.dto.LoginRequest;
import com.Mylist.demo.Users.dto.RegisterRequest;
import com.Mylist.demo.security.JwtUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class UsersController {
    
    @Autowired
    private UsersService usersService;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        try {
            Users user = usersService.register(request);
            return ResponseEntity.ok("User " + user.getUsername() +" registered successfully");
        } catch (RuntimeException error) {
            return ResponseEntity.badRequest().body(error.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            Users logUser = usersService.login(request);
            String token = jwtUtils.generateToken(logUser.getEmail());
            return ResponseEntity.ok(new AuthResponse(token, logUser.getUsername()));
        } catch (RuntimeException error) {
            return ResponseEntity.badRequest().body(error.getMessage());
        }
    }
    
}
