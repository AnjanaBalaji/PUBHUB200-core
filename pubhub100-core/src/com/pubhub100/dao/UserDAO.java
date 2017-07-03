package com.pubhub100.dao;

import org.springframework.jdbc.core.JdbcTemplate;

import com.pubhub100.model.User;
import com.pubhub100.util.ConnectionUtil;

public class UserDAO {

	private JdbcTemplate jdbcTemplate= ConnectionUtil.getJdbcTemplate();

	public void register(User user){
		String sql="insert into users(name,password) values(?,?)";
		Object[] args={user.getName(),user.getPassword()};
		int rows=jdbcTemplate.update(sql,args);
		System.out.println(rows);
	}

	
	public boolean login(User user){
		String sql="select email,password from users where email=? and password=?";
		Object[] args={user.getName(),user.getPassword() };
		jdbcTemplate.queryForObject(sql, args,(rs,rowNum) -> {
			user.setEmail(rs.getString("email"));
			user.setPassword(rs.getString("password"));
			return user;
		});
		return false;
	}

}
