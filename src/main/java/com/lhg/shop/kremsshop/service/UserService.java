package com.lhg.shop.kremsshop.service;

import com.lhg.shop.kremsshop.dto.request.UserCreateRequest;
import com.lhg.shop.kremsshop.dto.request.UserUpdateRequest;
import com.lhg.shop.kremsshop.entity.User;

public interface UserService {
    User create(UserCreateRequest request);
    User findById(Long id);
    User findByEmail(String email);
    User update(Long id, UserUpdateRequest request);
    void deleteById(Long id);
}
