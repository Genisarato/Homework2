<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Sign Up</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" />
<script src="<c:url value='/resources/js/jquery-1.11.1.min.js' />"></script>
<script src="<c:url value='/resources/js/bootstrap.min.js' />"></script>
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>

<!-- FontAwesome -->
<link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
      rel="stylesheet"
      integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN"
      crossorigin="anonymous"/>
<script>
    // Validació de contrasenyes
    function validatePasswords(event) {
        const password = document.getElementById('password').value;
        const confirmPassword = document.getElementById('confirmPassword').value;

        if (password !== confirmPassword) {
            event.preventDefault();
            alert('Les contrasenyes no coincideixen!');
        }
    }
</script>
</head>
<body>
	<div class="container">
            <div class="col-md-offset-2 col-md-7">
                <div class="panel panel-info">
                    <div class="panel-heading">
                        <div class="panel-title">Sign Up</div>
                    </div>
                    <div class="panel-body">
                        <form action="${mvc.uri('sign-up')}" class="form-horizontal" method="POST" onsubmit="validatePasswords(event)">
                            <input type="hidden" name="${mvc.csrf.name}" value="${mvc.csrf.token}"/>

                            <!-- Nom -->
                            <div class="form-group">
                                <label for="nom" class="col-md-3 control-label">Nom</label>
                                <div class="col-md-9">
                                    <input type="text" name="nom" value="${user.nom}" class="form-control" />
                                </div>
                            </div>

                            <!-- Username -->
                            <div class="form-group">
                                <label for="username" class="col-md-3 control-label">Username</label>
                                <div class="col-md-9">
                                    <input type="text" name="username" value="${user.username}" class="form-control" />
                                </div>
                            </div>

                            <!-- Telèfon -->
                            <div class="form-group">
                                <label for="telf" class="col-md-3 control-label">Telèfon</label>
                                <div class="col-md-9">
                                    <input type="text" name="telf" value="${user.telf}" class="form-control" />
                                </div>
                            </div>

                            <!-- DNI -->
                            <div class="form-group">
                                <label for="dni" class="col-md-3 control-label">DNI</label>
                                <div class="col-md-9">
                                    <input type="text" name="dni" value="${user.dni}" class="form-control" />
                                </div>
                            </div>

                            <!-- Email -->
                            <div class="form-group">
                                <label for="email" class="col-md-3 control-label">Email</label>
                                <div class="col-md-9">
                                    <input type="text" name="email" value="${user.email}" class="form-control" />
                                </div>
                            </div>

                            <!-- Contrasenya -->
                            <div class="form-group">
                                <label for="password" class="col-md-3 control-label">Contrasenya</label>
                                <div class="col-md-9">
                                    <input type="password" id="password" name="password" class="form-control" />
                                </div>
                            </div>

                            <!-- Confirmació de contrasenya -->
                            <div class="form-group">
                                <label for="confirmPassword" class="col-md-3 control-label">Confirma la Contrasenya</label>
                                <div class="col-md-9">
                                    <input type="password" id="confirmPassword" class="form-control" />
                                </div>
                            </div>

                            <!-- Submit Button -->
                            <div class="form-group">
                                <div class="col-md-offset-3 col-md-9">
                                    <input type="submit" value="Submit" />
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
	</div>
    <style>
        .form-group label {
            font-weight: bold;
        }

        .form-control {
            margin-bottom: 10px;
        }
    </style>
</body>
</html>
