package com.example.demo.controllers;

import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserServices;

import com.example.demo.models.UserModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/users")
public class UserController {
    final UserServices services;

    public UserController(UserServices userServices) {
        this.services = userServices;
    }

    @GetMapping
    public List<UserModel> getAll() {
        return services.getAll();
    }

    @GetMapping("/{id}")
    public UserModel getOne(@PathVariable UUID id) {
        return services.getOne(id);
    }

    @PostMapping
    public UserModel saveOne(@RequestBody UserModel newUser) {
        return services.create(newUser);
    }

    @PutMapping("/{id}")
    public UserModel updateOne(@RequestBody UserModel newUser, @PathVariable UUID id) {
        return services.updateOnePut(newUser, id);
    }

    @DeleteMapping("/{id}")
    public void deleteOne(@PathVariable UUID id) {
        services.deleteOne(id);
    }
}
