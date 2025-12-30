package com.lhg.shop.kremsshop.entity.user;

import com.lhg.shop.kremsshop.config.JpaAuditingConfig;
import com.lhg.shop.kremsshop.entity.User;
import com.lhg.shop.kremsshop.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(JpaAuditingConfig.class)
public class userRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testUserService() {
        User user = User.builder()
                .nickname("testuser")
                .email("00@gmail.com")
                .password("password")
                .build();

        userRepository.save(user);

        assertThat(userRepository.findById(user.getId())).isPresent();
    }
}
