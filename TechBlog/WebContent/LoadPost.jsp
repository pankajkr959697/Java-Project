<%@page import="com.teck.blog.entities.User"%>
<%@page import="com.teck.blog.dao.LikeDao"%>
<%@page import="javax.security.auth.message.callback.PrivateKeyCallback.Request"%>
<%@page import="com.teck.blog.entities.Post"%>
<%@page import="java.util.List"%>
<%@page import="com.teck.blog.helper.DbConnect"%>
<%@page import="com.teck.blog.dao.PostDao"%>





<div class="row">
<% User user = (User) session.getAttribute("currentUser");


PostDao d=new PostDao(DbConnect.getConnection());
int cid=Integer.parseInt(request.getParameter("cid"));
List<Post> post=null;
//Thread.sleep(3000);
if(cid==0){
	post=d.getAllPosts();
}else{
	post=d.getPostByCatId(cid);
}
if(post.size()==0){
	out.println("<h3 class='display-3 text-center'>No Post in this category</h3>");
	return;
}

for(Post p:post){
	
	
	%>
	
	<div class="col-6">
	<div class="card">
	<div class="card-body">
	 <img class="card-img-top" src="blog-pic/<%= p.getpPic() %>" alt="Card image cap">
	<b><%= p.getpTitle() %></b></br>
	<b><%= p.getTimestamp() %></b>
	</b></br>
	<b><%= p.getpContent() %></b>
	</b></br>
	<!--  <pre><%= p.getpCode() %></pre>-->
	</div>
	
	<div class="card-footer text-center primary-background">
		<%
	LikeDao ide=new LikeDao(DbConnect.getConnection());
	%>
	<a href="#!" onclick="doLike(<%=p.getPid()%>,<%=user.getId()%>)"class="btn btn-outline-light btn-sm"><i class="fa fa-thumbs-o-up"></i><span class="like-counter"><%=ide.countLikeOnPost(p.getPid()) %></span></a> 
	<a href="show_blog_page.jsp?post_id=<%=p.getPid() %>" class="btn btn-outline-light btn-sm">Read More...</a>

  
  <a href="#!" class="btn btn-outline-light btn-sm"><i class="fa fa-commenting-o"></i><span>10</span></a>                                                                                                                                                                                                                                               
	</div>
	
	</div>
	
	</div>
	
	
	
	<%
}
%>
</div>