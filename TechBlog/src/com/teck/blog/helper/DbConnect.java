package com.teck.blog.helper;

import java.sql.*;;

public class DbConnect {
private static Connection con;
public static Connection getConnection() {
	
	try
	{
		if(con==null) {

			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/techbolg","root","Incapp@123");
		}
	}
	catch(Exception e) {
	e.printStackTrace();	
	
}
	return con;
}

}
