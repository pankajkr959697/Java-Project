


<%@page import="com.teck.blog.dao.LikeDao"%>
<%@page import="java.text.DateFormat"%>
<%@page import="com.teck.blog.dao.userDao"%>
<%@page import="com.teck.blog.entities.Categories"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.teck.blog.entities.Post"%>
<%@page import="com.teck.blog.helper.DbConnect"%>
<%@page import="com.teck.blog.dao.PostDao"%>
<%@page import="com.teck.blog.entities.User"%>
<%@page errorPage="error_page.jsp"%>
<%
User user = (User) session.getAttribute("currentUser");

if (user == null) {
	response.sendRedirect("Login.jsp");
}
%>
<%
int postId = Integer.parseInt(request.getParameter("post_id"));
PostDao d = new PostDao(DbConnect.getConnection());
Post p = d.getPostByPostId(postId);
%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<div id="fb-root"></div>
<script async defer crossorigin="anonymous"
	src="https://connect.facebook.net/hi_IN/sdk.js#xfbml=1&version=v13.0"
	nonce="HpnaB7X1"></script>
<meta charset="ISO-8859-1">
<title><%=p.getpTitle()%></title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<link rel="stylesheet" href="css/style.css" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<style>
.banner-background {
	clip-path: polygon(50% 0%, 100% 0, 100% 35%, 99% 100%, 73% 94%, 41% 100%, 23% 94%,
		0 100%, 0% 35%, 0 0);
}

.post-title {
	font-weight: 100px;
	font-size: 30px
}

.post-content {
	font-weight: 100px;
	font-size: 25px
}

.post-date {
	font-weight: bold;
	font-style
	=Italic;
}

.post-userinfo {
	font-size: 20px;
	font-weight: 300px:
}

.row-user {
	boder: 1px solid #e2e2e2;
	padding-top: 15px;
}

body {
	background: url(images/bg.jpg);
	background-size: cover;
	background-attachment: fixed;
}
</style>

</head>
<body>
	<!-- Navbar -->



	<nav class="navbar navbar-expand-lg navbar-dark primary-background ">
		<a class="navbar-brand" href="index.jsp"><span
			class="fa fa-asterisk"></span>Tech Blog</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active"><a class="nav-link"
					href="Profile.jsp"><span class="fa fa-bell-o"></span>LearnCodeWithPankaj
						<span class="sr-only">(current)</span></a></li>

				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
					role="button" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false"> <span class="fa fa-check-square"></span>
						Categories
				</a>
					<div class="dropdown-menu" aria-labelledby="navbarDropdown">
						<a class="dropdown-item" href="#">Java</a> <a
							class="dropdown-item" href="#">Data Structure</a>
						<div class="dropdown-divider"></div>
						<a class="dropdown-item" href="#">C</a>
					</div></li>
				<li class="nav-item"><a class="nav-link" href="#"><span
						class="fa fa-id-badge"></span> Contact</a></li>
				<li class="nav-item"><a class="nav-link" href="#"
					data-toggle="modal" data-target="#add-post-model"><span
						class="fa fa-asterisk"></span>Do Post</a></li>

			</ul>
			<ul class="navbar-nav mr-right">
				<li class="nav-item"><a class="nav-link" href="#!"
					data-toggle="modal" data-target="#profle-model"> <span
						class="fa fa-user-circle "></span><%=user.getName()%></a></li>
				<li class="nav-item"><a class="nav-link" href=LogOutServlet>
						<span class="fa fa-user-plus "></span>LogOut
				</a></li>
			</ul>
		</div>
	</nav>
	<!--  End Navbar -->
	<!-- Main content of body -->

	<div class="container">

		<div class="row my-4 row-user">

			<div class="col-md-8 offset-md-2">

				<div class="card">
					<div class="card-header primary-background text-white">
						<h4 class="post-title"><%=p.getpTitle()%></h4>

					</div>

					<div class="card-body">
						<img class="card-img-top" src="blog-pic/<%=p.getpPic()%>"
							alt="Card image cap">

						<div class="row my-3 row-user">
							<div class="col-md-8">
								<%
								userDao ud = new userDao(DbConnect.getConnection());
								%>
								<p class="post-userinfo">
									<a class=post-userinfo href="#"><%=ud.getUserByUserId(p.getUserId()).getName()%></a>
									posted
								</p>
							</div>
							<div class="cpl-md-4">
								<p class="post-date">
									<%=DateFormat.getDateTimeInstance().format(p.getTimestamp())%></p>
							</div>
						</div>
						<p class="post-content">
							<%=p.getpContent()%></p>
						<br> <br>
						<div class="post-code">
							<pre><%=p.getpCode()%></pre>
						</div>
						<br> <br>
					</div>
					<div class="card-footer primary-background">
						<%
						LikeDao id = new LikeDao(DbConnect.getConnection());
						%>
						<a href="#!" onclick="doLike(<%=p.getPid()%>,<%=user.getId()%>)"
							class="btn btn-outline-light btn-sm"><i
							class="fa fa-thumbs-o-up"></i><span class="like-counter"><%=id.countLikeOnPost(p.getPid())%></span></a>
						<a href="#!" class="btn btn-outline-light btn-sm"><i
							class="fa fa-commenting-o"></i><span>10</span></a>

					</div>
					<div class="card-footer">

						<div class="fb-comments"
							data-href="http://localhost:8689/TechBlog/show_blog_page.jsp?post_id=<%=p.getPid() %>"
							data-width="" data-numposts="5"></div>
					</div>


				</div>




			</div>



		</div>


		<!-- Main content of body -->
		<!-- Modal -->
		<div class="modal fade" id="profle-model" tabindex="-1" role="dialog"
			aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header primary-background text-white">
						<h5 class="modal-title" id="exampleModalLabel">TechBlog</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<div class="container text-center">
							<img alt="" src="pics/<%=user.getProfile()%>" class="img-fluid"
								style="border-radius: 50%; max-width: 150px">
							<h5 class="modal-title md-3" id="exampleModalLabel"><%=user.getName()%></h5>
							<!-- Profile Details -->


							<div id="profile-detial">

								<table class="table">

									<tbody>
										<tr>
											<th scope="row">Id:</th>
											<td><%=user.getId()%></td>

										</tr>
										<tr>
											<th scope="row">Email:</th>
											<td><%=user.getEmail()%></td>

										</tr>
										<tr>
											<th scope="row">Gender:</th>
											<td><%=user.getGender()%></td>

										</tr>
										<tr>
											<th scope="row">About:</th>
											<td><%=user.getAbout()%></td>

										</tr>
										<tr>
											<th scope="row">Register Date:</th>
											<td><%=user.getDate()%></td>
										</tr>
									</tbody>
								</table>
							</div>
							<!--  end model -->
							<!--  Profile Edit -->


							<div id="profile-edit" style="display: none;">
								<h2 class="mt-2">Please Edit Carefully!</h2>
								<form action="EditServlet" method="post"
									enctype="multipart/form-data">
									<table class="table">
										<tr>
											<th scope="row">Id:</th>
											<td><%=user.getId()%></td>

										</tr>
										<tr>
											<th scope="row">Email:</th>
											<td><input type="email" class="form-control"
												name="user-Email" value="<%=user.getEmail()%>"></td>

										</tr>
										<tr>
											<th scope="row">Name:</th>
											<td><input type="text" class="form-control"
												name="user-name" value="<%=user.getName()%>"></td>

										</tr>
										<tr>
											<th scope="row">Password:</th>
											<td><input type="password" class="form-control"
												name="user-password" value="<%=user.getPassword()%>"></td>

										</tr>
										<tr>
											<th scope="row">Gender:</th>
											<td><%=user.getGender().toUpperCase()%></td>

										</tr>
										<tr>
											<th scope="row">About:</th>
											<td><textarea name="user-about" rows="3"
													class="form-control"> <%=user.getAbout()%></textarea></td>

										</tr>
										<tr>
											<th scope="row">Register Date:</th>
											<td><%=user.getDate()%></td>
										</tr>
										<tr>
											<th scope="row">New Profile:</th>
											<td><input type="file" name="image" class="form-control" />

											</td>

										</tr>
									</table>
									<div class="contianer">
										<button type="submit"
											class="btn btn-outline-primary primary-background">Save</button>

									</div>

								</form>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">Close</button>
						<button id="edit-profile-button" type="button"
							class="btn btn-primary">Edit</button>
					</div>
				</div>
			</div>
		</div>
		<!-- end  profil model -->


		<!--  post model -->



		<div class="modal fade" id="add-post-model" tabindex="-1"
			role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">Provide
							Content Detials!!</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<form id="add-post-form" action="AddPostServlet" method="post">
							<select class="form-group" name="cid">
								<option selected disabled>---Select---</option>
								<%
								PostDao postd = new PostDao(DbConnect.getConnection());
								ArrayList<Categories> list = postd.getCategories();
								for (Categories o : list) {
								%>
								<option value="<%=o.getCid()%>"><%=o.getName()%></option>
								<%
								}
								%>


							</select>
							<div class="form-group">
								<input name="pTitle" type="text" placeholder="Enter post Title"
									class="form-control"></input>
							</div>
							<div class="form-group">
								<textarea name="pContent" rows="5" class="form-control"
									placeholder="Enter Your Program(if any?)"></textarea>
							</div>
							<div class="form-group">
								<textarea name="pCode" rows="5" class="form-control"
									placeholder="Enter Your Program(if any?)"></textarea>
							</div>
							<div class="form-group">
								<label>Select Photo</label> </br> <input name="pPic" type="file">
							</div>


							<div class="container text-center">
								<button type="submit" class="btn btn-outline-primary">Post</button>



							</div>
						</form>
					</div>


				</div>
			</div>
		</div>

		<!-- end model -->

		<!-- Java Script -->
		<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
		<script src="https://code.jquery.com/jquery-3.6.0.min.js"
			integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
			crossorigin="anonymous"></script>
		<script
			src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
			integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
			crossorigin="anonymous"></script>
		<script
			src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
			integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
			crossorigin="anonymous"></script>
		<script src="js/myJs.js"></script>

		<script>
		$(document).ready(function() {
			let editStatus = false
			$("#edit-profile-button").click(function() {
				//alert("click");
				if (editStatus == false) {
					$("#profile-detial").hide();
					$("#profile-edit").show();
					editStatus = true;
					$(this).text("Back");
				} else {
					$("#profile-detial").show();
					$("#profile-edit").hide();
					editStatus = false;
					$(this).text("Edit");
				}
			});
		});
	</script>


		<!-- Now Add post -->
		<script>
	<!--
		$(document)
				.ready(
						function(event) {
							//alert("loaded");
							$("#add-post-form")
									.on(
											"submit",
											function(event) {
												//this code is called when form submited
												event.preventDefault();
												console.log("Submited");
												let form = new FormData(this);
												$
														.ajax({
															//now requesting to server
															url : "AddPostServlet",
															type : 'post',
															data : form,
															success : function(
																	data,
																	textStatus,
																	jqXHR) {
																//Success
																console
																		.log(data);
																if (data.trim() == "Done") {
																	swal(
																			"Good job!",
																			"Saved Successufully!",
																			"success",
																			{

																			});
																} else {
																	swal(
																			"Error!",
																			"Something went Wrong!",
																			"success",
																			{

																			});
																}
															},
															error : function(
																	jqXHR,
																	textStatus,
																	errorThrown) {
																//Error
															},
															processData : false,
															contentType : false
														});
											});

						});
	</script>
</body>

</html>