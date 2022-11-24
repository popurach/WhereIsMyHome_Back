package com.whereismy.service;

import com.whereismy.mapper.UserMapper;
import com.whereismy.vo.LoginUser;
import com.whereismy.vo.ResetPass;
import com.whereismy.vo.UpdateUser;
import com.whereismy.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	UserMapper mapper;

	@Override
	public User check(LoginUser user) {
		return mapper.check(user);
	}

	@Override
	public void register(User user) {
		mapper.register(user);
	}

	@Override
	public User selectOne(String id) {
		return mapper.selectOne(id);
	}

	@Override
	public void modify(UpdateUser user) {
		mapper.modify(user);
	}

	@Override
	public void delete(String id) {
		mapper.delete(id);
	}

	@Override
	public void logout(String id) {
		mapper.logout(id);
	}

	@Override
	public void saveRefreshToken(String id, String refreshToken) {
		String sqlId=mapper.getRefreshToken(id);
		if(sqlId!=null){
			mapper.logout(id);
		}
		mapper.saveRefreshToken(id,refreshToken);
	}

	@Override
	public String findPass(String id) {
		// 임시 비밀번호 생성
		char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
				'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

		String tmp="";
		int idx = 0;
		for (int i = 0; i < 10; i++) {
			idx = (int) (charSet.length * Math.random());
			tmp += charSet[idx];
		}

		// 비밀번호 변경
		mapper.resetPass(new LoginUser(id,tmp));

		// 임시 비밀번호 return
		return tmp;
	}

	@Value("${spring.mail.username}")
	private String sender;

	@Autowired
	private JavaMailSender mailSender;

	@Override
	public void sendMail(ResetPass reset) {
		User user=mapper.selectOne(reset.getId());

		// 임시 비밀번호 메일 보내기
		SimpleMailMessage message=new SimpleMailMessage();
		message.setTo(reset.getEmail());
		message.setFrom(sender);
		message.setSubject("구해줘 태봉 임시 비밀번호 발급입니다.");
		message.setText("안녕하세요. "+user.getName()+"님! \n 임시 비밀번호 발급해드립니다. 다음의 비밀번호를 이용하여 로그인 진행해주세요.\n" +
				reset.getNewPass()+"\n 감사합니다~. 구해줘 태봉이었습니다.!");
		mailSender.send(message);
	}

	@Override
	public void resetPass(LoginUser user) {
		mapper.resetPass(user);
	}

}
