package com.whereismy.service;

import com.whereismy.mapper.UserMapper;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Date;

@Service
public class JwtServiceImpl implements JwtService{
    private static final String SECRET="homeSecret";
    @Autowired
    UserMapper mapper;

    @Override
    public String createAccessToken(String val) {
        return createToken(val, "access-token",1000*600);
    }

    @Override
    public String createRefreshToken(String val) {
        return createToken(val,"refresh-token",1000*1800);
    }

    // token 생성
    public String createToken(String id, String tokenName,long expire){
        Date now=new Date();
        String jwt=null;
        try {
            byte[] b = SECRET.getBytes("UTF-8");
            jwt=Jwts.builder()
                    .setHeaderParam(Header.TYPE,Header.JWT_TYPE)
                    .setSubject(tokenName)
                    .setExpiration(new Date(System.currentTimeMillis()+expire))
                    .claim("id",id)
                    .signWith(SignatureAlgorithm.HS256,b)
                    .compact();
        }catch(Exception e){
            e.printStackTrace();
        }
        return jwt;
    }

    @Override
    public boolean checkToken(String token) {
        try{
            byte[] key=SECRET.getBytes("UTF-8");
            Jws<Claims> claims= Jwts.parser().setSigningKey(key).parseClaimsJws(token);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    @Override
    public String getRefreshToken(String id) {
        return mapper.getRefreshToken(id);
    }

}
