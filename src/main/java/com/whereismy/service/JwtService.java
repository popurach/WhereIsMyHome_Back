package com.whereismy.service;

public interface JwtService {
    // accessToken 발급
    String createAccessToken(String val);
    // refreshToken 발급
    String createRefreshToken(String val);
    // token 발급
    String createToken(String id, String name,long expire);
    // 토큰 유효성 검사
    boolean checkToken(String token);
    //refreshToken 발급 여부 확인
    String getRefreshToken(String id);
}
