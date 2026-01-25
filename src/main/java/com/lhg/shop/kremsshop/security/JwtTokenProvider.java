package com.lhg.shop.kremsshop.security;

import com.lhg.shop.kremsshop.entity.User;
import com.lhg.shop.kremsshop.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final UserRepository userRepository;

    @Value("${jwt.secret}")
    private String SECRET_KEY;

    // AccessToken은 짧게 (30분)
    private final long ACCESS_TOKEN_VALID_TIME = 30 * 60 * 1000L;
    // RefreshToken은 길게 (7일)
    private final long REFRESH_TOKEN_VALID_TIME = 7 * 24 * 60 * 60 * 1000L;

    // 3. 토큰 생성 (유저 PK를 Subject로 담음)
    public String createAccessToken(Long userId, String role) {
        Claims claims = Jwts.claims().setSubject(String.valueOf(userId));
        claims.put("role", role);

        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims) // 데이터
                .setIssuedAt(now) // 발행 시간
                .setExpiration(new Date(now.getTime() + ACCESS_TOKEN_VALID_TIME)) // 만료 시간
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY) // 암호화 알고리즘과 키
                .compact();
    }

    // RefreshToken 생성 (userId를 담도록 수정)
    public String createRefreshToken(Long userId) {
        Claims claims = Jwts.claims().setSubject(String.valueOf(userId));
        Date now = new Date();

        return Jwts.builder()
                .setClaims(claims) // 유저 식별자 포함
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + REFRESH_TOKEN_VALID_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    // 4. 토큰에서 유저 ID 추출 (나중에 API 요청 올 때 검증용)
    public Long getUserId(String token) {
        return Long.parseLong(Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody()
                .getSubject());
    }

    // 5. 토큰 유효성 검사
    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    public Authentication getAuthentication(String token) {

        Long userId = this.getUserId(token);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("유저 없음"));

        PrincipalDetails userDetails = new PrincipalDetails(user);

        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }
}
