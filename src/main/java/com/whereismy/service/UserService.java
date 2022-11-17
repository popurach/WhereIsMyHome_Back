package com.whereismy.service;


import com.whereismy.vo.LoginUser;
import com.whereismy.vo.UpdateUser;
import com.whereismy.vo.User;

public interface UserService {

	User check(LoginUser user);

	void register(User user);

	User selectOne(String id);

	void modify(UpdateUser user);

	void delete(String id);

}
