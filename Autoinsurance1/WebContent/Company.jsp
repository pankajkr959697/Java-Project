


<!DOCTYPE html>
<html>
<head>
<title>Auto Insurance</title>
<link rel="icon" href="resource/autoinsurance-icon.png" />

<meta name="viewport" content="width=device-width, initial-scale=1">

<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" />
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.min.js"></script>

<!-- AOS css and JS -->
<link rel="stylesheet" href="resource/aos/aos.css">
<script src="resource/aos/aos.js"></script>
<!-- AOS css and JS END-->

<!-- fontawesome -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://kit.fontawesome.com/a076d05399.js"></script>
<!-- fontawesome END -->

<!-- Lightbox CSS & Script -->
<script src="resource/lightbox/ekko-lightbox.js"></script>
<link rel="stylesheet" href="resource/lightbox/ekko-lightbox.css" />



<!-- custom css-->
<link rel="stylesheet" href="resource/custom.css" />
<!-- custom css END-->

<meta name="author" content="Rahul Chauhan" />
<meta name="description" content="This is a Auto Insurance website" />
<meta name="keywords" content="best Insurance comapny in noida" />
</head>
<body>
	<!-- Code for message receiving  -->
	<%
	String m = (String) session.getAttribute("msg");
	if (m != null) {
	%>
	<div class="alert alert-danger text-center" role="alert">
		<%=m%>
	</div>

	<%
	session.setAttribute("msg", null);
	}
	%>

	<nav class="navbar navbar-expand-sm sticky-top bg-light">
		<a class="navbar-brand c-font" href=""> <img
			src='resource/autoinsurance-icon.png' width="30px" /> <span
			class="text-danger font-weight-bold ">Auto</span><span
			class="text-muted">Insurance</span>
		</a>
		<button class="navbar-toggler bg-light" type="button"
			data-toggle="collapse" data-target="#collapsibleNavbar">
			<!-- <span class="navbar-toggler-icon text-dark"> <i class="fas fa-sort-down"></i> </span> -->
			<span class="fa fa-bars"></span> Menu
		</button>
		<div class="collapse navbar-collapse " id="collapsibleNavbar">
			<ul class="navbar-nav mx-auto ">
				<li class="nav-item"><a class="nav-link text-secondary"
					href="index.jsp">HOME</a></li>
			
				<li class="nav-item"><a class="nav-link text-secondary"
					href="Company.jsp">COMPANY</a></li>
				
			</ul>
			<div>
				<a class="text-danger h5 mr-3" href="tel:+91-9811981198">CALL US
					TODAY: +91-9811981198</a>
			</div>
		</div>
	</nav>





	<div class="container p-4">
		<div class="row">
			<div class="col-sm-4 bg-light p-4" >
				<h4 class="text-primary text-center ">Company Login!</h4>
				
				<hr/>
				<form action="CompanyLogin" method="post">
				<div class="form-group">
						<label for="email">Company ID:</label> <input type="text"
							class="form-control"  pattern="[0-9]+" placeholder="Enter Company ID" id="cnumber"
							name="cid" required>
					</div>
					<div class="form-group">
						<label for="email">Company Email ID:</label> <input type="text"
							class="form-control" placeholder="Enter admin id" id="email"
							name="email" required>
					</div>
					<div class="form-group">
						<label for="pwd">Password:</label> <input type="password"
							class="form-control" placeholder="Enter password" id="pwd"
							name="password" required>
					</div>
					<button type="submit" class="btn btn-primary">Login</button>
				</form>
			</div>
			<div class="col-sm-2" >
			</div>
			<div class="col-sm-6 bg-light p-4" >
				<h4 class="text-primary text-center ">Company Registration!</h4>
				
				<hr/>
				<form action="CompanyRegister" method="post" enctype="multipart/form-data">
				
					<div class="form-group">
						<label >Company Email ID:</label> <input type="text"
							class="form-control" placeholder="Enter admin id" id="email"
							name="email" required>
					</div>
					<div class="form-group">
						<label >Company Name:</label>
						 <input type="text" pattern="[a-z A-z]+"
							class="form-control" placeholder="Enter Company Name" id="name"
							name="name" required>
					</div>
					<div class="form-group">
						<label >Company Registration No:</label> <input type="text"
							class="form-control" placeholder="Enter Company Registration No" id="rnumber"
							name="reg_no" required>
					</div>
					<div class="form-group">
						<label for="email">Contact No:</label> <input type="text"
							class="form-control"  maxlength="10" minlength="10" pattern="[0-9]+" placeholder="Enter Contact No" id="cnumber"
							name="phone" required>
					</div>
					<div class="form-group">
						<label >Company Logo:</label> <input type="file"
							class="form-control" placeholder="" id="cnumber"
							name="logo" required>
					</div>
					<div class="form-group">
						<label>Password:</label> 
						<input type="password"
							class="form-control" placeholder="Enter password" id="pwd"
							name="password" required>
					</div>
					<div class="form-group">
						<label >Company Address:</label> 
						<textarea 	 name="address"  class="form-control" placeholder="Enter detials" id="address"
							name="address" required>
						</textarea>
					</div>
					<button type="submit" class="btn btn-primary">Registration</button>
				</form>
			</div>
		</div>
	</div>

	 <footer class="bg-dark p-4">
          <p class="text-white">
            Design by PANKAJ KUMAR YADAV
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            Social Media: 
            <a href="linkedin.com/in/pankaj-kumar-yadav-babaa6173/"><i class="fab fa-linkedin"></i></a>
          </p>
        </footer>


</body>

</html>
