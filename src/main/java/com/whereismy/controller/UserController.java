package com.whereismy.controller;

import com.whereismy.service.JwtService;
import com.whereismy.service.UserService;
import com.whereismy.vo.LoginUser;
import com.whereismy.vo.ResetPass;
import com.whereismy.vo.UpdateUser;
import com.whereismy.vo.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.mail.internet.AddressException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@CrossOrigin("*")
@Api(tags = {"User Controller API"}, description = "User 관련 서비스")
public class UserController {

	@Autowired
	UserService service;
	@Autowired
	JwtService jwtService;

	@ApiOperation(value="로그인")
	@ApiResponses({@ApiResponse(code=200,message="로그인 성공"),@ApiResponse(code=404,message = "페이지 없음"),@ApiResponse(code=500,message = "로그인 실패")})
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(@RequestBody LoginUser user) {
		//User flag = service.check(new User(user.getId(), user.getPass(), null, null, null));    //맞는 유저인지 체크
		Map<String,Object> resultMap=new HashMap<>();
		HttpStatus status=null;

		try{
			// DB에 들어있는 유저인지 확인
			User flag = service.check(user);    //맞는 유저인지 체크
			if(flag!=null){
				// 로그인이 되는 유저 -> token 발급
				String accessToken=jwtService.createAccessToken(flag.getId());
				String refreshToken=jwtService.createRefreshToken(flag.getId());

				// refreshToken DB 저장
				service.saveRefreshToken(flag.getId(),refreshToken);

				resultMap.put("access-token",accessToken);
				resultMap.put("refresh-token",refreshToken);
				resultMap.put("message","success");
				status=HttpStatus.ACCEPTED;
			}else{
				resultMap.put("message","fail");
				status=HttpStatus.ACCEPTED;
			}
		}catch(Exception e){
			resultMap.put("message",e.getMessage());
			status=HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String,Object>>(resultMap,status);
	}

	@ApiOperation(value="구글로그인")
	@ApiResponses({@ApiResponse(code=200,message="로그인 성공"),@ApiResponse(code=404,message = "페이지 없음"),@ApiResponse(code=500,message = "로그인 실패")})
	@PostMapping("/oauth/google")
	public ResponseEntity<?> google(@RequestBody Map<String,String> access){
		try {
			String accessToken = access.get("accessToken");
			String GOOGLE_USERINFO_REQUEST_URL="https://www.googleapis.com/oauth2/v1/userinfo";

			//header에 accessToken을 담는다.
			HttpHeaders headers = new HttpHeaders();
			headers.add("Authorization","Bearer "+accessToken);

			//HttpEntity를 하나 생성해 헤더를 담아서 restTemplate으로 구글과 통신하게 된다.
			HttpEntity<MultiValueMap<String, String>> request = new HttpEntity(headers);

			HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
			factory.setReadTimeout(5000);  // 읽기시간초과, ms
			factory.setConnectTimeout(3000); // 연결시간초과, ms
			HttpClient httpClient = HttpClientBuilder.create()
					.setMaxConnTotal(100) // connection pool 적용
					.setMaxConnPerRoute(5) // connection pool 적용
					.build();
			factory.setHttpClient(httpClient); // 동기실행에 사용될 HttpClient 세팅
			RestTemplate restTemplate = new RestTemplate(factory);

			ResponseEntity<String> response=restTemplate.exchange("https://www.googleapis.com/oauth2/v1/userinfo", HttpMethod.GET,request,String.class);

			JSONParser jsonParser=new JSONParser();
			JSONObject jsonObject=(JSONObject) jsonParser.parse(response.getBody().toString());

			String passId=(String)jsonObject.get("id");
			String emailId=((String)jsonObject.get("email")).split("@")[0];
			String name=(String)jsonObject.get("name");
			LoginUser user=new LoginUser(emailId,passId);

			User flag = service.check(user);

			// DB에 있는 계정이라면 로그인
			if(flag!=null){
				return login(user);
			}
			// DB에 없는 계정이라면 회원가입 -> 로그인
			else{
				service.register(new User(emailId,passId,name,null,null));
				return login(user);
			}

		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

	@ApiOperation(value="로그아웃")
	@ApiResponses({@ApiResponse(code=200,message="로그아웃 성공"),@ApiResponse(code=404,message = "페이지 없음"),@ApiResponse(code=500,message = "로그아웃 실패")})
	@GetMapping("/logout/{userId}")
	public ResponseEntity<Map<String,Object>> logout(@PathVariable String userId) {
		Map<String,Object> resultMap=new HashMap<>();
		HttpStatus status=null;

		try{
			service.logout(userId);
			resultMap.put("message","success");
			status=HttpStatus.ACCEPTED;
		}catch(Exception e){
			resultMap.put("message",e.getMessage());
			status=HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String,Object>>(resultMap, status);
	}

	// AccessToken 재발급
	@ApiOperation(value="access token 재발급")
	@ApiResponses({@ApiResponse(code=200,message="access token 재발급 성공"),@ApiResponse(code=404,message = "페이지 없음"),@ApiResponse(code=500,message = "토큰 재발급 실패")})
	@GetMapping("/retoken/{userId}")
	public ResponseEntity<Map<String,Object>> reAccessToken(@PathVariable String userId) {
		Map<String,Object> resultMap=new HashMap<>();
		HttpStatus status=null;

		try{
			//refresh token이 유효한지 확인
			String refresh=jwtService.getRefreshToken(userId);
			//refresh token이 유효하다면 access token 발급
			if(refresh!=null){
				String access=jwtService.createAccessToken(userId);
				resultMap.put("access-token",access);
				resultMap.put("message","success");
			}else{
				// 아니라면 refresh token 재발급하라고 안내 메세지 전송
				// 로그인 mapping으로 넘어가게됨
				resultMap.put("message","fail");
			}

			status=HttpStatus.ACCEPTED;
		}catch(Exception e){
			resultMap.put("message",e.getMessage());
			status=HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String,Object>>(resultMap,status);
	}

	@ApiOperation(value="회원 등록")
	@ApiResponses({@ApiResponse(code=200,message="회원 등록 성공"),@ApiResponse(code=404,message = "페이지 없음"),@ApiResponse(code=500,message = "등록 실패")})
	@PostMapping("/user")
	public void register(@RequestBody User user) {
		service.register(user);
	}

	//interceptor에서 Id 가져오기
	//회원 정보 수정 위한 기본 정보 조회때도 이용하면 됨
	@ApiOperation(value="회원 정보 조회")
	@ApiResponses({@ApiResponse(code=200,message="회원 정보 조회 성공"),@ApiResponse(code=204,message="존재하지 않는 회원입니다"),@ApiResponse(code=404,message = "페이지 없음"),@ApiResponse(code=500,message = "조회 실패")})
	@GetMapping("/user/{id}")
	public ResponseEntity<User> info(@PathVariable String id) {
		User user=service.selectOne(id);

		if(user!=null){
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}else{
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}

	}

	@ApiOperation(value="회원 정보 수정")
	@ApiResponses({@ApiResponse(code=200,message="회원 정보 수정 성공"),@ApiResponse(code=404,message = "페이지 없음"),@ApiResponse(code=500,message = "정보 수정 실패")})
	@PutMapping("/user")
	public void modify(@RequestBody UpdateUser user) {
		service.modify(user);
	}

	@ApiOperation(value="비밀번호 변경")
	@ApiResponses({@ApiResponse(code=200,message="비밀번호 변경 성공"),@ApiResponse(code=404,message = "페이지 없음"),@ApiResponse(code=500,message = "비밀번호 변경 실패")})
	@PutMapping("/reset/pass")
	public void resetPass(@RequestBody LoginUser user) {
		service.resetPass(user);
	}

	@ApiOperation(value="회원 탈퇴")
	@ApiResponses({@ApiResponse(code=200,message="회원 탈퇴 성공"),@ApiResponse(code=404,message = "페이지 없음"),@ApiResponse(code=500,message = "탈퇴 실패 아무데도 못 감")})
	@DeleteMapping("/user/{id}")
	public void deleteUser(@PathVariable String id) {
		service.delete(id);
	}

	@ApiOperation(value="비밀번호 찾기")
	@ApiResponses({@ApiResponse(code=200,message="비밀번호 찾기 이메일 발송 성공"),@ApiResponse(code=404,message = "페이지 없음"),@ApiResponse(code=500,message = "이메일 발송 실패")})
	@GetMapping("/find/pass/{id}/{email}")
	public ResponseEntity<?> findPass(@PathVariable String id,@PathVariable String email) {
		// DB에 저장된 id인지 확인
		User user=service.selectOne(id);
		HttpStatus status=null;

		// 맞다면 이메일 발송 및 비밀번호 변경 작업
		try {
			if (user != null) {
				String newPass = service.findPass(id);
				service.sendMail(new ResetPass(id, newPass, email));
				status=HttpStatus.OK;
				return new ResponseEntity("success",status);
			}
			// 없다면 오류 발송
			else {
				return new ResponseEntity("fail",HttpStatus.NO_CONTENT);
			}
		}catch(Exception e){
			return new ResponseEntity("fail",HttpStatus.NO_CONTENT);
		}
	}
}
