package com.lhg.shop.kremsshop.security.service;

import com.lhg.shop.kremsshop.entity.User;
import com.lhg.shop.kremsshop.repository.UserRepository;
import com.lhg.shop.kremsshop.security.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    public static final String ROLE_USER = "ROLE_USER";
    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        // 공급자 이름 (예: google, facebook 등)
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        Map<String, Object> attributes = oAuth2User.getAttributes();

        String email = (String) attributes.get("email");
        String name = (String) attributes.get("name");
        String providerId = attributes.containsKey("sub")
                ? (String) attributes.get("sub")
                : (String) attributes.get("id");

        // 처음 소셜로그인할때 회원가입 처리
        // 이미 가입된 회원이면 찾아서 리턴
        User user = userRepository.findByProviderAndProviderId(registrationId, providerId)
                .orElseGet(() ->
                        userRepository.save(User.builder()
                                .email(email)
                                .nickname(name)
                                .provider(registrationId)
                                .providerId(providerId)
                                .role(ROLE_USER)
                                .build())
                );

        return new PrincipalDetails(user, attributes);
    }
}
