package com.whereismy.util;

import com.whereismy.vo.User;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Service;

@Service
public class JwtUtil {
    private static final String SECRET="homeSecret";

    // accessToken 유효성 확인
    public boolean checkToken(String accessToken) {
        try{
            Jws<Claims> claims= Jwts.parser().setSigningKey(SECRET).parseClaimsJws(accessToken);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    // token에서 Auth 정보 가져오기
    public User getAuth(String accessToken) throws JwtException{
        User auth=new User();
        Claims body=Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(accessToken)
                .getBody();
        auth.setId(body.get("id",String.class));
        return auth;
    }

}
