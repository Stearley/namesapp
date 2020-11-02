package com.newsapplication.demo.controller;

import com.newsapplication.demo.model.UserData;
import com.newsapplication.demo.exception.UserNotFoundException;
import com.newsapplication.demo.repository.UserRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import javax.validation.Valid;
import java.util.List;

@RestController //эта аннотация используется для обозначения каждого метода в объявляемом классе как главный объект
public class UserController {

    final
    UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // вызвать все данные о пользователях
    @GetMapping("/users")
    public List<UserData> getAllUsers() {
        return userRepository.findAll();
    }

    // Создать нового пользователя
    @PostMapping("/users")
    public UserData createUser(@Valid @RequestBody UserData userData) {
        return userRepository.save(userData);
    }

    // вызвать одиночного пользователя
    @GetMapping("/users/{user}")
    public UserData getUserById(@PathVariable UserData user) throws UserNotFoundException {
//        return userRepository.findById(userId)
//                .orElseThrow(() -> new UserNotFoundException(userId));
        return user;
    }

    // обновить запись о пользователе
    @PutMapping("/users/{id}")
    public UserData updateUser(@PathVariable(value = "id") Long userId,
                           @Valid @RequestBody UserData userDataDetails) throws UserNotFoundException {

        UserData userData = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        userData.setFirst_name(userDataDetails.getFirst_name());
        userData.setLast_name(userDataDetails.getLast_name());


        UserData updatedUserData = userRepository.save(userData);

        return updatedUserData;
    }

    // удалить запись о пользователе
    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUserData(@PathVariable(value = "id") Long userId) throws UserNotFoundException {
        UserData userData = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        userRepository.delete(userData);

        return ResponseEntity.ok().build(); //ResponseEntity класс, который добавляет сущность хттп запросов и позволяет
                                           // их обрабатывать.
    }
}