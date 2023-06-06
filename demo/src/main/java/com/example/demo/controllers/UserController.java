package com.example.demo.controllers;

import com.example.demo.exceptions.UserNotFoundException;

import com.example.demo.models.UserModel;
import com.example.demo.repositories.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/users")
public class UserController {
    final UserRepository repository;

    public UserController(UserRepository userRepository) {
        this.repository = userRepository;
    }

    @GetMapping
    public List<UserModel> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public UserModel getOne(@PathVariable UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @PostMapping
    public UserModel saveOne(@RequestBody UserModel newUser) {
        return repository.save(newUser);
    }

    @PutMapping("/{id}")
    public UserModel updateOne(@RequestBody UserModel newUser, @PathVariable UUID id) {
    return repository.findById(id)
      .map(user -> {
        user.setNome(newUser.getNome());
        user.setIdade(newUser.getIdade());
        user.setSexo(newUser.getSexo());
        user.setProfissao(newUser.getProfissao());
        return repository.save(user);
      })
      .orElseGet(() -> {
        newUser.setId(id);
        return repository.save(newUser);
      });
    }

    @DeleteMapping("/{id}")
    public void deleteOne(@PathVariable UUID id) {
        repository.deleteById(id);
    }
}
