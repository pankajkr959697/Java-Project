package com.teck.blog.dao;


import com.teck.blog.entities.*;

import java.sql.*;
public class userDao {
 private Connection con;

public userDao(Connection con) {
	
	this.con = con;
}
//method to insert users detials 
public boolean saveUser(User user) {
	boolean f=false;
	try {
		
		PreparedStatement pt=this.con.prepareStatement(" insert into users(name,email,password,gender,about) values(?,?,?,?,?)");
		pt. setString(1, user.getName());
		pt. setString(2, user.getEmail());
		pt. setString(3, user.getPassword());
		pt. setString(4, user.getGender());
		pt. setString(5, user.getAbout());
		
		//pt. setDate(6, (Date) new java.util.Date());
		pt.executeUpdate();
		f=true;
	}
	catch (Exception e) {
		e.printStackTrace();
	}
	return f;
	
}
//Get user by usermail and userPassword
 public User getUserByEmailAndPassword(String email,String password) {
	 
	 User user=null;
	 
	 try {
		
		 PreparedStatement pt=this.con.prepareStatement(" select * from users where email=? and password=?");
	 pt.setString(1, email);
	 pt.setString(2, password);
	 ResultSet set=pt.executeQuery();
	 if(set.next()) {
		 user=new User();
		 //data from user
		 String name=set.getString("name");
		 //set to user object
		 user.setName(name);
		 user.setId(set.getInt("id"));
		 user.setEmail(set.getString("email"));
		 user.setPassword(set.getString("password"));
		 user.setGender(set.getString("gender"));
		 user.setAbout(set.getString("about"));
		 user.setDate(set.getTimestamp("pDate"));
		 user.setProfile(set.getString("profile"));
	 } 
	 
	 } catch(SQLIntegrityConstraintViolationException e) {
		e.printStackTrace();
		 
	 }
	 catch (Exception e) {
		e.printStackTrace();
	}
	 
	 
	 return user;
 }
 public boolean updateUser(User user) {
	 boolean f=false;
	try {
		
		PreparedStatement pt=this.con.prepareStatement(" update users set name=? ,email=?, password=?, gender=?,about=?, profile=? where id=?");
		pt.setString(1,user.getName());
		pt.setString(2,user.getEmail());
		pt.setString(3,user.getPassword());
		pt.setString(4,user.getGender());
		pt.setString(5,user.getAbout());
		pt.setString(6,user.getProfile());
		pt.setInt(7, user.getId());
		pt.executeUpdate();
		f=true;
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return f;
	 
}
 public User getUserByUserId(int userid) {
	 User user=null;
	 try {
			PreparedStatement pt=this.con.prepareStatement(" select * from users where id=?");
	pt.setInt(1, userid);
	 ResultSet set=pt.executeQuery();
	 if(set.next()) {
		 user=new User();
		 //data from user
		 String name=set.getString("name");
		 //set to user object
		 user.setName(name);
		 user.setId(set.getInt("id"));
		 user.setEmail(set.getString("email"));
		 user.setPassword(set.getString("password"));
		 user.setGender(set.getString("gender"));
		 user.setAbout(set.getString("about"));
		 user.setDate(set.getTimestamp("pDate"));
		 user.setProfile(set.getString("profile"));
	 } 
	 } catch (Exception e) {
	e.printStackTrace();
	}
	 return user;
 }
}
	

