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
                                    <input type="password" id="password" name="password" class="form-control" value="${user.password}" />
                                </div>
                            </div>

                            <!-- Botons Cancelar i Submit -->
                            <div class="form-group button-group">
                                <!-- Botó Cancelar -->
                                <button type="button" onclick="window.location.href='<c:url value="/Web/Principal"/>'" class="back-button">
                                    Cancelar
                                </button>
                                <!-- Botó Submit -->
                                <button type="submit" class="submit-button">
                                    Submit
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

    <!-- Estilos personalizados -->
    <style>
        .form-container {
            display: flex;
            flex-direction: column;
            align-items: center;
            max-width: 500px;
            margin: 0 auto;
        }

        .form-group {
            margin: 15px 0;
            width: 100%;
        }

        .form-group label {
            display: block;
            font-weight: bold;
            margin-bottom: 5px;
        }

        .form-input {
            width: 100%;
            padding: 10px;
            font-size: 16px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
        }

        .form-input:focus {
            border-color: #007bff;
            outline: none;
        }

        .button-group {
            display: flex; /* Col·loca els botons un al costat de l'altre */
            justify-content: center; /* Centra els botons dins del contenidor */
            align-items: center; /* Centra verticalment dins del contenidor */
            gap: 20px; /* Espai entre els botons */
            margin-top: 20px; /* Espai superior per separar dels camps */
        }

        .back-button,
        .submit-button {
            padding: 10px 20px; /* Ajustar el padding per fer els botons més grans */
            background-color: #f1f1f1;
            color: #333;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s ease;
            width: 150px; /* Amplada uniforme per als dos botons */
            text-align: center;
        }

        .back-button:hover,
        .submit-button:hover {
            background-color: #ddd; /* Canvi de color al passar el ratolí */
        }
    </style>
</body>
</html>
