package com.lhg.shop.kremsshop.entity;

import com.lhg.shop.kremsshop.dto.request.UserUpdateRequest;
import com.lhg.shop.kremsshop.entity.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {

    @Column(nullable = false)
    private String email;

    // 소셜로그인 사용자는 password가 null일 수 있음
    @Column(nullable = true)
    private String password;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String role;

    @Column(nullable = true)
    private String provider;

    @Column(nullable = true, name = "provider_id")
    private String providerId;

    public void update(UserUpdateRequest request) {
        if(request.email() != null) {
            this.email = request.email();
        }
        if(request.password() != null) {
            this.password = request.password();
        }
        if(request.nickname() != null) {
            this.nickname = request.nickname();
        }
    }
}
