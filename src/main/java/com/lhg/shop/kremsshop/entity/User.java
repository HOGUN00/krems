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

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;

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
