<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Homework2</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/estilPrincipal.css'/>">
    <script>
        // Función para manejar el clic en el botón de volver
        function goBack(event) {
            event.preventDefault(); // Evitar que el formulario se envíe
            window.location.href = "<c:url value='/Web/Principal'/>"; // Redirigir a la página principal
        }
    </script>
</head>
<body>
    <!-- Contenedor principal centrado -->
<main class="container">
    <h1>Iniciar Sessió</h1>
    
    <!-- Mostra el missatge d'error si existeix -->
    <c:if test="${not empty error}">
        <div class="error-message">
            ${error}
        </div>
    </c:if>
    
    <!-- Formulario centrado -->
    <form action="<c:url value='login'/>" method="POST" class="form-container">
        <!-- Grup del camp d'usuari -->
        <div class="form-group">
            <label for="username">Usuari</label>
            <input type="text" id="username" name="username" class="form-input" 
                   placeholder="Nom d'usuari" value="${username != null ? username : ''}" required>
        </div>

        <!-- Grup del camp de contrasenya -->
        <div class="form-group">
            <label for="password">Contrasenya</label>
            <input type="password" id="password" name="password" class="form-input" 
                   placeholder="Contrasenya" required>
        </div>
        
        <!-- Opció Recuérdame -->
        <div class="form-group">
            <label>
                <input type="checkbox" id="rememberMe" name="rememberMe" 
                       ${username != null ? 'checked' : ''}> Recorda'm
            </label>
        </div>

        <!-- Contenedor para los botones -->
        <div class="button-container">
            <!-- Botón para cancelar -->
            <div class="back-button-container">
                <button type="button" onclick="goBack(event)" class="back-button">
                    Cancelar
                </button>
            </div>

            <!-- Botón para iniciar sessió -->
            <div class="create-button-container">
                <button type="submit" class="back-button">Inicia Sessió</button>
            </div>
        </div>
    </form>
</main>

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

        .button-container {
            display: flex;
            justify-content: space-between;
            align-items: center;
            width: 100%;
            margin-top: 20px;
            gap: 20px;
        }

        .back-button-container,
        .create-button-container {
            display: flex;
            justify-content: center;
            align-items: center;
            width: 48%;
        }

        /* Botones */
        .back-button,
        .create-button-container button {
            display: flex;
            align-items: center;
            justify-content: center;
            padding: 0 20px;
            width: 100%;
            max-width: 200px;
            height: 50px;
            line-height: 1;
            background-color: #f1f1f1;
            color: #333;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .back-button:hover,
        .create-button-container button:hover {
            background-color: #ddd;
        }
    </style>
</body>
</html>
