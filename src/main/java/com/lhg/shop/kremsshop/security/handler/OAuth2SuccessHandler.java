package com.lhg.shop.kremsshop.security.handler;

import com.lhg.shop.kremsshop.entity.User;
import com.lhg.shop.kremsshop.security.JwtTokenProvider;
import com.lhg.shop.kremsshop.security.PrincipalDetails;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

        PrincipalDetails principal;
        if (oAuth2User instanceof PrincipalDetails) {
            principal = (PrincipalDetails) oAuth2User;
        } else {
            throw new IllegalStateException("Unexpected user type: " + oAuth2User.getClass());
        }

        String accessToken = jwtTokenProvider.createAccessToken(principal.getUser().getId(), principal.getUser().getRole());
//        String refreshToken = jwtTokenProvider.createRefreshToken(principal.getUser().getId());

        log.info("AccessToken: {}", accessToken);
//        log.info("RefreshToken: {}", refreshToken);

        // 테스트 위한 리다이렉트 URL 설정
        // 오류나지만 그 뒤의 쿼리파라미터 확인을 위해 남겨둠
        String targetUrl = UriComponentsBuilder.fromUriString("http://localhost:8080/login-success")
                .queryParam("accessToken", accessToken)
                .build().toUriString();

        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }
}
