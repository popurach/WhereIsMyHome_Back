package com.whereismy.mapper;


import com.whereismy.vo.LoginUser;
import com.whereismy.vo.UpdateUser;
import com.whereismy.vo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

	User check(LoginUser user);

	void register(User user);

	User selectOne(String id);

	void modify(UpdateUser user);

	void delete(String id);

}
