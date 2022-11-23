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
            byte[] key=SECRET.getBytes("UTF-8");
            Jws<Claims> claims= Jwts.parser().setSigningKey(key).parseClaimsJws(accessToken);
            return true;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    // token에서 Auth 정보 가져오기
    public User getAuth(String accessToken) throws JwtException{
        User auth=new User();
        try{
            byte[] key=SECRET.getBytes("UTF-8");
            Claims body=Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(accessToken)
                    .getBody();
            auth.setId(body.get("id",String.class));

            return auth;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
