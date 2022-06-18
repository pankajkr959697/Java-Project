package dao;

import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class DbConnect {
	private  Connection con;
	public DbConnect() throws Exception{
		Class.forName("com.mysql.cj.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/autoinsurance","root","Incapp@123");
		
	}
	public void disconnect()throws Exception {
		if(con!=null && !con.isClosed()) {
			con.close();
		}
		
		
	}
	
public String getAdminLogin(String email,String password)throws Exception {
	PreparedStatement p=con.prepareStatement("Select name from admin where email=? and password=?");
	p.setString(1, email);
	p.setString(2, password);
	ResultSet rs=p.executeQuery();
	if(rs.next()) {
		return rs.getString("name");
	}
	else
	{
		return null;
	}
	
	
	
}

public boolean addCompanny(HashMap<String,Object> company)throws Exception{
	try {
		
	
	PreparedStatement p=con.prepareStatement("insert into company(email,name,reg_no,phone,logo,password,address,status,cid) values(?,?,?,?,?,?,?,?,?)");	
    p.setString(1,(String)company.get("email"));
    p.setString(2,(String)company.get("name"));
    p.setString(3,(String)company.get("reg_no"));
    p.setString(4,(String)company.get("phone"));
    p.setBinaryStream(5, (InputStream)company.get("logo"));
    p.setString(6,(String)company.get("password"));
    p.setString(7,(String)company.get("address"));
    p.setString(8,"pending");
   
    p.setInt(9,(int)company.get("cid"));
    p.executeUpdate();
   
	}
	catch(SQLIntegrityConstraintViolationException e) {
		e.printStackTrace();
	}
	 return true;
		
}
public String getCompanyLogin(int cid,String email,String password)throws Exception {
	PreparedStatement p=con.prepareStatement("Select name from company where cid=? and email=? and password=?");
	p.setInt(1, cid);
	p.setString(2, email);
	p.setString(3, password);
	ResultSet rs=p.executeQuery();
	if(rs.next()) {
		return rs.getString("name");
	}
	else
	{
		return null;
	}
	
	
	
}

public HashMap<String,String> getAdminDetials()throws Exception {
	PreparedStatement p=con.prepareStatement("Select * from admin ");
	ResultSet rs=p.executeQuery();
	rs.next();
	HashMap<String,String> h=new HashMap<String,String>();
	h.put("email", rs.getString("email"));
	h.put("password", rs.getString("password"));
		return h;
	
}
 public  ArrayList<HashMap<String,String>> getCompaniesBystatus( String status) throws Exception {
		PreparedStatement p=con.prepareStatement("Select * from company where status=? ");
	  p.setString(1, status);
	  ResultSet rs=p.executeQuery();
	  ArrayList<HashMap<String,String>> companies=new ArrayList();
	  while(rs.next()) {
		  HashMap<String,String > company= new HashMap<String,String > ();
		  company.put("email",rs.getString("email"));
		  company.put("name",rs.getString("name"));
		  company.put("phone",rs.getString("phone"));
		  company.put("address",rs.getString("address"));
		  company.put("reg_no",rs.getString("reg_no"));
		  company.put("status",rs.getString("status"));
		  
		  companies.add(company); 
	  }
	  
	 
	  
	return companies ;
	 
 }
 public  HashMap<String,String > getCompanyDetials(String email) throws Exception {
		PreparedStatement p=con.prepareStatement("Select * from company where email=? ");
		  p.setString(1, email);
		  ResultSet rs=p.executeQuery();
	  
	  
	  if(rs.next()) {
		  HashMap<String,String > company= new HashMap<String,String > ();
		  company.put("email",rs.getString("email"));
		  company.put("name",rs.getString("name"));
		  company.put("phone",rs.getString("phone"));
		  company.put("address",rs.getString("address"));
		  company.put("reg_no",rs.getString("reg_no"));
		  company.put("status",rs.getString("status"));
			return  company;
	  }
	  else
	  {
	  
	return null;
	  }
}
 public String updateCompanyStatus(String cemail ,String status)throws Exception {
		PreparedStatement p=con.prepareStatement("update company set status=? where email=?");
		p.setString(1, status);
		p.setString(2, cemail);
		int x= p.executeUpdate();
		if(x==0) {
			return "Update Successfully";
		}
		else {
			return "Update Failed";
		}
		
		
	}
	public boolean addPolicy(HashMap<String, Object> policy) throws Exception {
	
		PreparedStatement p1=con.prepareStatement("select * from policy where c_email=? and p_name=?");
		p1.setString(1, (String)policy.get("c_email"));
		p1.setString(2, (String)policy.get("p_name"));
		ResultSet rs=p1.executeQuery();
		if(rs.next()) {
			return false;
		}else {
			PreparedStatement p=con.prepareStatement("insert into policy (p_name,p_si_amt,p_desc,c_email,status) values (?,?,?,?,'active',?)");
			p.setString(1, (String)policy.get("p_name"));
			p.setInt(2, (int)policy.get("p_si_amt"));
			p.setString(3, (String)policy.get("p_desc"));
			p.setString(4, (String)policy.get("c_email"));
			p.setString(5, (String)policy.get("category"));
		
			p.executeUpdate();
			return true;
		}
	}
	public ArrayList<HashMap<String, String>>  getAllPolicies(String c_email) throws Exception{
		PreparedStatement p=con.prepareStatement("select * from policy where c_email=?");
		p.setString(1, c_email);
		ResultSet rs=p.executeQuery();
		ArrayList<HashMap<String, String>> policies=new ArrayList();
		while(rs.next()) {
			HashMap<String, String> policy=new HashMap<String, String>();
			policy.put("pid", rs.getString("pid"));
			policy.put("p_name", rs.getString("p_name"));
			policy.put("p_si_amt", rs.getString("p_si_amt"));
			policy.put("p_desc", rs.getString("p_desc"));
			policy.put("status", rs.getString("status"));
			policy.put("category", rs.getString("category"));
			
			policies.add(policy);
		}
		return policies;
	}
	public String updatePolicyStatus(int pid,String status) throws Exception{
		PreparedStatement p=con.prepareStatement("update policy set status=? where pid=?");
		p.setString(1, status);
		p.setInt(2, pid);
		int x=p.executeUpdate();
		if(x==0) {
			return "Updation Failed!";
		}else {
			return "Updation Success!";
		}
	}
	public boolean addUser(HashMap<String, Object> user) throws Exception {
		try {
			PreparedStatement p=con.prepareStatement("insert into users (email,name,phone,photo,password,address) values (?,?,?,?,?,?)");
			p.setString(1, (String)user.get("email"));
			p.setString(2, (String)user.get("name"));
			p.setString(3, (String)user.get("phone"));
			p.setBinaryStream(4, (InputStream)user.get("photo"));
			p.setString(5, (String)user.get("password"));
			p.setString(6, (String)user.get("address"));
			p.executeUpdate();
			return true;
		}catch(SQLIntegrityConstraintViolationException e) {
			return false;
		}
	}
	public String getUserLogin(String email,String password) throws Exception{
		PreparedStatement p=con.prepareStatement("select name from users where email=? and password=?");
		p.setString(1, email);
		p.setString(2, password);
		ResultSet rs=p.executeQuery();
		if(rs.next()) {
			return rs.getString("name");
		}else {
			return null;
		}
	}
	public byte[] getPhoto(String email) throws Exception{

			PreparedStatement p=con.prepareStatement("select photo from users where email=?");
		p.setString(1, email);
		ResultSet rs=p.executeQuery();
		if(rs.next()) {
			return rs.getBytes("photo");
		}else {
			return null;
		}
	
	}
	public byte[] getPhotoCompany(String email) throws Exception{

		PreparedStatement p=con.prepareStatement("select logo from company where email=?");
	p.setString(1, email);
	ResultSet rs=p.executeQuery();
	if(rs.next()) {
		return rs.getBytes("logo");
	}else {
		return null;
	}

}

	
	public ArrayList<HashMap<String, Object>>  getPoliciesByCategory(String category) throws Exception{
		PreparedStatement p=con.prepareStatement("select * from policy where category=? and status='active'");
		p.setString(1, category);
		ResultSet rs=p.executeQuery();
		ArrayList<HashMap<String, Object>> policies=new ArrayList();
		while(rs.next()) {
			HashMap<String, Object> policy_details=new HashMap<String, Object>();
			policy_details.put("pid", rs.getInt("pid"));
			policy_details.put("p_name", rs.getString("p_name"));
			policy_details.put("p_si_amt", rs.getInt("p_si_amt"));
			policy_details.put("c_email", rs.getString("c_email"));
			policy_details.put("p_desc", rs.getString("p_desc"));
			policies.add(policy_details);
		}
		return policies;
	}
	public HashMap<String, Object>  getPolicyByID(int pid) throws Exception{
		PreparedStatement p=con.prepareStatement("select * from policy where pid=? and status='active'");
		p.setInt(1, pid);
		ResultSet rs=p.executeQuery();
		HashMap<String, Object> policy_details=new HashMap<String, Object>();
		if(rs.next()) {
			policy_details.put("pid", rs.getInt("pid"));
			policy_details.put("p_name", rs.getString("p_name"));
			policy_details.put("p_si_amt", rs.getInt("p_si_amt"));
			policy_details.put("c_email", rs.getString("c_email"));
			policy_details.put("p_desc", rs.getString("p_desc"));
			return policy_details;
		}else {
			return null;
		}
		
	}
	public boolean addOrder(HashMap<String, Object> order) throws Exception {
		try {
			PreparedStatement p1=con.prepareStatement("select * from policy_orders where v_no=? ");
			p1.setString(1, (String)order.get("v_no"));
			ResultSet rs=p1.executeQuery();
			if(rs.next()) {
				return false;
			}else {
				PreparedStatement p=con.prepareStatement("insert into policy_orders (pid,u_email,v_no,e_no,c_date) values (?,?,?,?,CURRENT_DATE)");
				p.setInt(1, (int)order.get("pid"));
				p.setString(2, (String)order.get("u_email"));
				p.setString(3, (String)order.get("v_no"));
				p.setString(4, (String)order.get("e_no"));
				p.executeUpdate();
				return true;
			}
		}catch(SQLIntegrityConstraintViolationException e) {
			return false;
		}
	}
	public ArrayList<HashMap<String, Object>>  getPoliciesByUser(String u_email) throws Exception{
		PreparedStatement p=con.prepareStatement("select * from policy_orders where u_email=?");
		p.setString(1, u_email);
		ResultSet rs=p.executeQuery();
		ArrayList<HashMap<String, Object>> policies=new ArrayList();
		while(rs.next()) {
			HashMap<String, Object> policy_order=new HashMap<String, Object>();
			policy_order.put("oid", rs.getInt("oid"));
			policy_order.put("pid", rs.getInt("pid"));
			policy_order.put("v_no", rs.getString("v_no"));
			policy_order.put("e_no", rs.getString("e_no"));
			policy_order.put("c_date", rs.getDate("c_date"));
			policies.add(policy_order);
		}
		return policies;
	}
	}



