<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<!-- csss -->

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
 crossorigin="anonymous">
 <link rel="stylesheet" href="css/style.css"/>
 <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
 <style>
 .banner-background{
clip-path: polygon(50% 0%, 100% 0, 100% 35%, 99% 100%, 73% 94%, 41% 100%, 23% 94%, 0 100%, 0% 35%, 0 0);
 }
 
 </style>
</head>
<body>
<!-- Navbar -->
<%@include file="normal_nav.jsp" %>
<main class="primary-background banner-background p-5 " style=" padding-bottom: 80px">

<div class="container" > 

<div class="col-md-6 offset-md-3">
<div class="card">
<div class="card-header text-center">
<span class="fa fa-3x fa-user-circle "></span>
</br>
<p> Register Here</p>
</div>
<div class="card-body">
<form id="reg-form" action="RegesterServlet" method="post">

 <div class="form-group">
    <label for="exampleInputEmail1">User Name</label>
    <input type="text" name="u-name" class="form-control" id="user-name" aria-describedby="emailHelp" placeholder="User Name">
   
  </div>


  <div class="form-group">
    <label for="exampleInputEmail1">Email address</label>
    <input type="email" name="u-email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email">
    <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
  </div>
  
  <div class="form-group">
    <label for="exampleInputPassword1">Password</label>
    <input type="password" name="u-password" class="form-control" id="exampleInputPassword1" placeholder="Password">
  </div>
  
   <div class="form-group">
    <label for="Gender"> Select Gender</label></br>
    <input type="radio"  id="gender" name="gender" value="male">Male
       <input type="radio"  id="gender"name="gender" value="female">Female
  </div>
  
<div class="form-check">
<textarea class="form-control" name="about" rows="5" placeholder="Enter About Here"></textarea>
</div>
  <div class="form-check">
    <input type="checkbox"  name="check" class="form-check-input" id="exampleCheck1">
    <label class="form-check-label" for="exampleCheck1">Term and condition</label>
  </div>
  
  </br>
  <div class="container text-center" id="loader"style="display:none">
  <span class="fa fa-refresh fa-spin fa-3x"></span>
  <h4>Please Wait..</h4>
  </div>
  </br>
  <button type="submit" class="btn btn-primary" id="btn">Submit</button>
</form>
</div>

</div>
</div>
</div>
</main>



<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
  <script src="js/myJs.js" ></script>
  
 <!-- Ajex Implementation --> 
 
 <script>
 
 $(document).ready(function(){
	
	console.log("Loaded");
	$("#reg-form").on('submit',function(event){
		event.preventDefault();
	
		let form= new FormData(this);
		$("#btn").hide();
		$("#loader").show();
		//Send request servlet;
		$.ajax({
			url:"RegesterServlet",
		
			type:'post',
		
			data:form,
			success:function(data,textStatus,jqXHR){
				console.log(data);
				$("#btn").show();
				$("#loader").hide();
				if(data.trim()==='successfull'){
				swal("Registration Successfully..we are redirection to Login Pase")
				.then((value) => {
				window.location="Login.jsp"
				
				});
				}
				else{
					swal(data);	
				}
			},error:function(jqXHR,textStatus,errorThrown){
				$("#btn").show();
				$("#loader").hide();
				swal("Something Went Wrong");
				
				
			},
			processData:false,
			contentType:false
		});
		
		
	});
 });
 
 </script>
</body>
</html>