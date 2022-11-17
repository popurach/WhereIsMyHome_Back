package com.whereismy.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.whereismy.service.UserService;
import com.whereismy.vo.LoginUser;
import com.whereismy.vo.UpdateUser;
import com.whereismy.vo.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@Api(tags = {"User Controller API"}, description = "User 관련 서비스")
public class UserController {

	@Autowired
	UserService service;

	@ApiOperation(value="로그인")
	@ApiResponses({@ApiResponse(code=200,message="로그인 성공"),@ApiResponse(code=404,message = "페이지 없음"),@ApiResponse(code=500,message = "로그인 실패")})
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginUser user, HttpServletRequest request) {
		//User flag = service.check(new User(user.getId(), user.getPass(), null, null, null));    //맞는 유저인지 체크
		User flag = service.check(user);    //맞는 유저인지 체크

		if (flag != null) {    //유저가 맞다면
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			return new ResponseEntity<String>("success", HttpStatus.OK);
		} else {    //아니라면
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value="로그아웃")
	@ApiResponses({@ApiResponse(code=200,message="로그아웃 성공"),@ApiResponse(code=404,message = "페이지 없음"),@ApiResponse(code=500,message = "로그아웃 실패")})
	@GetMapping("/logout")
	public ResponseEntity<String> logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}

	@ApiOperation(value="회원 등록")
	@ApiResponses({@ApiResponse(code=200,message="회원 등록 성공"),@ApiResponse(code=404,message = "페이지 없음"),@ApiResponse(code=500,message = "등록 실패")})
	@PostMapping("/user")
	public void register(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) {
		service.register(user);
	}

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

	@ApiOperation(value="회원 탈퇴")
	@ApiResponses({@ApiResponse(code=200,message="회원 탈퇴 성공"),@ApiResponse(code=404,message = "페이지 없음"),@ApiResponse(code=500,message = "탈퇴 실패 아무데도 못 감")})
	@DeleteMapping("/user/{id}")
	public void deleteUser(@PathVariable String id) {
		service.delete(id);
	}
}
