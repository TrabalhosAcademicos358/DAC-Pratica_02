package com.example.demo.services;

import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.models.UserModel;
import com.example.demo.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServices {
    final UserRepository repository;

    public UserServices(UserRepository UserRepository) {
        this.repository = UserRepository;
    }

    public List<UserModel> getAll() {
        return repository.findAll();
    }

    public UserModel getOne(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public UserModel create(UserModel newUser) {
        return repository.save(newUser);
    }

    public UserModel updateOnePut(UserModel newUser, UUID id) {
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

    public void deleteOne(UUID id) {
        repository.deleteById(id);
    }
}
