package com.lhg.shop.kremsshop.controller;

import com.lhg.shop.kremsshop.dto.data.UserDto;
import com.lhg.shop.kremsshop.dto.request.UserCreateRequest;
import com.lhg.shop.kremsshop.dto.request.UserUpdateRequest;
import com.lhg.shop.kremsshop.entity.User;
import com.lhg.shop.kremsshop.mapper.UserMapper;
import com.lhg.shop.kremsshop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping
    public ResponseEntity<UserDto> createUser(
            @RequestBody UserCreateRequest request) {
        User user = userService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(userMapper.toDto(user));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUser(
            @PathVariable Long userId) {
        User user = userService.findById(userId);
        return ResponseEntity.ok(userMapper.toDto(user));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(
            @PathVariable Long userId,
            @RequestBody UserUpdateRequest request) {
        User user = userService.update(userId, request);
        return ResponseEntity.ok(userMapper.toDto(user));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(
            @PathVariable Long userId) {
        userService.deleteById(userId);
        return ResponseEntity.noContent().build();
    }
}
