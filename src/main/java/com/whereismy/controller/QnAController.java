package com.whereismy.controller;

import com.whereismy.service.QnAService;
import com.whereismy.service.UserService;
import com.whereismy.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@RestController
@CrossOrigin("*")
@Api(tags = {"QnA Controller API"}, description = "QnA 관련 서비스")
public class QnAController {

	@Autowired
	QnAService service;

	@ApiOperation(value="게시글 목록 보기")
	@ApiResponses({@ApiResponse(code=200,message="게시글 목록 조회 성공"),@ApiResponse(code=404,message = "페이지 없음"),@ApiResponse(code=500,message = "게시글 로딩 실패")})
	@GetMapping("/qna")
	public ArrayList<QnA> listQnA() {
		ArrayList<QnA> qna=service.getQnAList();

		return qna;
	}

	@ApiOperation(value="게시글 상세 보기")
	@ApiResponses({@ApiResponse(code=200,message="게시글 조회 성공"),@ApiResponse(code=404,message = "페이지 없음"),@ApiResponse(code=500,message = "게시글 로딩 실패")})
	@GetMapping("/qna/{num}")
	public ResponseEntity<QnA> detailQnA(@PathVariable String num, HttpServletRequest request) {
		QnA qna=service.getOneQnA(num);
		if(qna!=null) {
			return new ResponseEntity<QnA>(qna, HttpStatus.OK);
		}else{
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}

	@ApiOperation(value="게시글 등록")
	@ApiResponses({@ApiResponse(code=200,message="게시글 등록 성공"),@ApiResponse(code=404,message = "페이지 없음"),@ApiResponse(code=500,message = "게시글 등록 실패")})
	@PostMapping("/qna")
	public void insertQnA(@RequestBody QnAPost qna) {
		service.insertQnA(qna);
	}

	@ApiOperation(value="게시글 수정")
	@ApiResponses({@ApiResponse(code=200,message="게시글 수정 성공"),@ApiResponse(code=404,message = "페이지 없음"),@ApiResponse(code=500,message = "게시글 수정 실패")})
	@PutMapping("/qna/{num}")
	public void updateQnA(@PathVariable String num,@RequestBody QnAUpdate qna) {
		service.updateQnA(num,qna);
	}

	@ApiOperation(value="게시글 삭제")
	@ApiResponses({@ApiResponse(code=200,message="게시글 삭제 성공"),@ApiResponse(code=404,message = "페이지 없음"),@ApiResponse(code=500,message = "게시글 삭제 실패")})
	@DeleteMapping("/qna/{num}")
	public void deleteQnA(@PathVariable String num) {
		service.deleteQnA(num);
	}

	@ApiOperation(value="게시글 제목 검색")
	@ApiResponses({@ApiResponse(code=200,message="게시글 검색 성공"),@ApiResponse(code=404,message = "페이지 없음"),@ApiResponse(code=500,message = "게시글 검색 실패")})
	@GetMapping("/qna/search/{title}")
	public ArrayList<QnA> search(@PathVariable String title) {
		ArrayList<QnA> qna=service.searchQnA(title);

		return qna;
	}

	@ApiOperation(value="게시글에 따른 댓글 조회")
	@ApiResponses({@ApiResponse(code=200,message="댓글 조회 성공"),@ApiResponse(code=404,message = "페이지 없음"),@ApiResponse(code=500,message = "댓글 조회 실패")})
	@GetMapping("/comment/{bnum}")
	public ArrayList<Comment> getComment(@PathVariable String bnum) {
		ArrayList<Comment> comment=service.getComment(bnum);

		return comment;
	}

	@ApiOperation(value="댓글 등록")
	@ApiResponses({@ApiResponse(code=200,message="댓글 등록 성공"),@ApiResponse(code=404,message = "페이지 없음"),@ApiResponse(code=500,message = "댓글 등록 실패")})
	@PostMapping("/comment")
	public void insertComment(@RequestBody Comment comment) {
		service.insertComment(comment);
	}

	@ApiOperation(value="댓글 삭제")
	@ApiResponses({@ApiResponse(code=200,message="댓글 삭제 성공"),@ApiResponse(code=404,message = "페이지 없음"),@ApiResponse(code=500,message = "댓글 삭제 실패")})
	@DeleteMapping("/comment/{bnum}/{num}")
	public void deleteComment(@PathVariable String bnum,@PathVariable String num) {
		service.deleteComment(bnum,num);
	}
}
