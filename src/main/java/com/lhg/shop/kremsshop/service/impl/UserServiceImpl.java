package com.lhg.shop.kremsshop.service.impl;

import com.lhg.shop.kremsshop.dto.request.UserCreateRequest;
import com.lhg.shop.kremsshop.dto.request.UserUpdateRequest;
import com.lhg.shop.kremsshop.entity.User;
import com.lhg.shop.kremsshop.repository.UserRepository;
import com.lhg.shop.kremsshop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    @Override
    public User create(UserCreateRequest request) {
        User user = User.builder()
                .email(request.email())
                .password(request.password())
                .nickname(request.nickname())
                .build();

        return userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow();
    }

    @Override
    @Transactional(readOnly = true)
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow();
    }

    @Override
    public User update(Long id, UserUpdateRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow();

        user.update(request);

        return userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
