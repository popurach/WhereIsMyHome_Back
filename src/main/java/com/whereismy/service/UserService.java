package com.whereismy.service;


import com.whereismy.vo.LoginUser;
import com.whereismy.vo.ResetPass;
import com.whereismy.vo.UpdateUser;
import com.whereismy.vo.User;

import javax.mail.internet.AddressException;

public interface UserService {

	User check(LoginUser user);

	void register(User user);

	User selectOne(String id);

	void modify(UpdateUser user);

	void delete(String id);

    void logout(String id);

	void saveRefreshToken(String id, String refreshToken);

    String findPass(String id);

	void sendMail(ResetPass reset);

	void resetPass(LoginUser user);
}
