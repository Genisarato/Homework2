<%-- 
    Document   : info-user
    Created on : 31 dic 2024, 11:27:40
    Author     : janto
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Perfil d'Usuari</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/bootstrap.min.css'/>">
    <style>
        .profile-container {
            max-width: 600px;
            margin: 50px auto;
            padding: 20px;
            border: 1px solid #ddd;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            background-color: #f9f9f9;
        }

        .profile-header {
            text-align: center;
            margin-bottom: 20px;
        }

        .profile-header h1 {
            font-size: 24px;
            color: #333;
        }

        .profile-info {
            margin-bottom: 15px;
        }

        .profile-info label {
            font-weight: bold;
            color: #555;
        }

        .profile-info span {
            display: block;
            font-size: 16px;
            color: #333;
        }

        .button-container {
            display: flex;
            justify-content: space-between;
            margin-top: 20px;
        }

        .button {
            padding: 10px 20px;
            background-color: #007bff;
            color: white;
            text-align: center;
            text-decoration: none;
            border-radius: 5px;
            font-size: 16px;
            border: none;
            cursor: pointer;
        }

        .button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="profile-container">
            <div class="profile-header">
                <h1>Perfil d'Usuari</h1>
            </div>
            <div class="profile-info">
                <label>Nom:</label>
                <span>${nom}</span>
            </div>
            <div class="profile-info">
                <label>Nom d'usuari:</label>
                <span>${username}</span>
            </div>
            <div class="profile-info">
                <label>Email:</label>
                <span>${email}</span>
            </div>
            <div class="profile-info">
                <label>DNI:</label>
                <span>${dni}</span>
            </div>
            <div class="profile-info">
                <label>Telèfon:</label>
                <span>${telf}</span>
            </div>
            <div class="button-container">
                <a href="<c:url value='/Web/edit'/>" class="button">Modificar Dades</a>
                <a href="<c:url value='/Web/Principal'/>" class="button">Tornar</a>
            </div>
        </div>
    </div>
</body>
</html>

