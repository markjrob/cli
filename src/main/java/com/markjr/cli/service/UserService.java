package com.markjr.cli.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.markjr.cli.model.User;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    List<User> userList = new ArrayList<>();

    public void setupUser() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File("user.json");
        userList = mapper.readValue(file, new TypeReference<List<User>>() {});
    }

    public boolean isUserIdExists(Long userId) {
        return userList.stream().noneMatch(user -> user.getId().equals(userId));
    }

    public List<User> getAllUser() {
        return userList;
    }

    public User findUserByUserId(Long userId) {
        return userList.stream().filter(user -> user.getId().equals(userId)).findFirst().get();
    }

    public User insertUser(User user) {
        User newUser = new User(user);
        userList.add(newUser);
        return newUser;
    }

    public User updateUser(Long userId, User user) {
        User updateUser = findUserByUserId(userId);
        updateUser.setName(user.getName());
        updateUser.setUsername(user.getUsername());
        updateUser.setEmail(user.getEmail());
        updateUser.setPhone(user.getPhone());
        updateUser.setWebsite(user.getWebsite());

        deleteUser(userId);
        userList.add(updateUser);

        return updateUser;
    }

    public void deleteUser(Long userId) {
        userList = userList.stream().filter(user -> !user.getId().equals(userId)).collect(Collectors.toList());
    }

}
