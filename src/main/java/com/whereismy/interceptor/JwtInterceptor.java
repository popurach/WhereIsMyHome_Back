package com.whereismy.interceptor;

import com.whereismy.util.JwtUtil;
import com.whereismy.vo.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import io.jsonwebtoken.JwtException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

@Component
@RequiredArgsConstructor
// 매핑된 controller uri가 호출된 후 controller 진입 직전 interceptor에서 작업 수행
public class JwtInterceptor implements HandlerInterceptor {
    private final JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // access token 가져오기
        String authorization=request.getHeader("Authorization");

        if(request.getMethod().equals("OPTIONS")){
            return true;
        }

        if(authorization==null || authorization.length()==0){
            throw new JwtException("AUTHORIZATION이 존재하지 않습니다.");
        }

        String accessToken=authorization.split(" ")[1];
        boolean isValid=jwtUtil.checkToken(accessToken);
        if(isValid){
            // 토큰 유효함 user Auth 정보 가져오기
            User auth=jwtUtil.getAuth(accessToken);
            request.setAttribute("auth",auth);
            return true;
        }else{
            // access token 재발급 하도록 유도
            throw new JwtException("Access Token이 만료되었습니다.");
        }
    }

}
