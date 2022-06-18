package com.teck.blog.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


import com.teck.blog.entities.Categories;
import com.teck.blog.entities.Post;

public class PostDao {
	Connection con;

	public PostDao(Connection con) {
		super();
		this.con = con;
	}
public ArrayList<Categories> getCategories(){
	ArrayList<Categories> list=new ArrayList();
	
	try {
		Statement sc=con.createStatement();
		ResultSet rs=sc.executeQuery("select * from categories");
		while(rs.next()) {
			
			int cid=rs.getInt("cid");
			String name=rs.getString("name");
			String description=rs.getString("description");
			Categories c=new Categories(cid,name,description);
			list.add(c);
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return list;
}
public boolean savePost(Post p) {
	boolean f=false;
	try {
		
	PreparedStatement pt=con.prepareStatement(" insert into posts(pTitle,pContent,pCode,pPic,catId,userId) values(?,?,?,?,?,?)");
		pt.setString(1,p.getpTitle());
		pt.setString(2,p.getpContent());
		pt.setString(3,p.getpCode());
		pt.setString(4,p.getpPic());
		
		pt.setInt(5,p.getCatId());
		pt.setInt(6,p.getUserId());
		pt.executeUpdate();
		f=true;
	} catch (Exception e) {
		e.printStackTrace();
	}
	return f;
	
}

public List<Post> getAllPosts(){
	List<Post> list=new ArrayList<>();
	//Fetch all post
	try {
		PreparedStatement p=con.prepareStatement("select * from posts order by pid desc");
		ResultSet set=p.executeQuery();
		while(set.next()) {
			int pid=set.getInt("pid");
			String pTitle=set.getString("pTitle");
			String pContent=set.getString("pContent");
			String pCode=set.getString("pCode");
			String pPic=set.getString("pPic");
		Timestamp pDate=set.getTimestamp("pDate");
		int catId=set.getInt("catId");
		int userId=set.getInt("userId");
		Post post=new Post(pid, pTitle, pContent, pCode, pPic, pDate, catId, userId);
		list.add(post);
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return list;
	
}

public List<Post> getPostByCatId(int catId){
	//Fetch all post by id
	List<Post> list=new ArrayList<>();
	try {
		PreparedStatement p=con.prepareStatement("select * from posts where catId=? ");
		p.setInt(1, catId);
		ResultSet set=p.executeQuery();
		while(set.next()) {
			int pid=set.getInt("pid");
			String pTitle=set.getString("pTitle");
			String pContent=set.getString("pContent");
			String pCode=set.getString("pCode");
			String pPic=set.getString("pPic");
			Timestamp pDate=set.getTimestamp("pDate");
		
		int userId=set.getInt("userId");
		Post post=new Post(pid, pTitle, pContent, pCode, pPic, pDate, catId, userId);
		list.add(post);
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
		return list;
}
public Post getPostByPostId(int postId) {
	Post post=null;
	try {
		PreparedStatement p=con.prepareStatement("select * from posts where pid=? ");
		p.setInt(1, postId);
  ResultSet set=p.executeQuery();
  if(set.next()) {
		int pid=set.getInt("pid");
		String pTitle=set.getString("pTitle");
		String pContent=set.getString("pContent");
		String pCode=set.getString("pCode");
		String pPic=set.getString("pPic");
		Timestamp pDate=set.getTimestamp("pDate");
	int catId=set.getInt("catId");
	int userId=set.getInt("userId");
	post=new Post(pid, pTitle, pContent, pCode, pPic, pDate, catId, userId);
	
  }
	} catch (Exception e) {
		e.printStackTrace();
	}
	return post;
}

}
