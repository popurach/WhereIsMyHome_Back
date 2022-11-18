package com.whereismy.service;

import com.whereismy.mapper.UserMapper;
import com.whereismy.vo.LoginUser;
import com.whereismy.vo.UpdateUser;
import com.whereismy.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
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
		mapper.saveRefreshToken(id,refreshToken);
	}

}
