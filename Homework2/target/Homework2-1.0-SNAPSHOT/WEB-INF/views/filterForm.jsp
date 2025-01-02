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
    <style>
        .header-content {
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .welcome-container {
            display: flex;
            align-items: center;
            color: white;
            font-size: 20px;
        }

        .welcome-button {
            background-color: transparent;
            border: none;
            color: white;
            font-size: 20px;
            cursor: pointer;
            text-decoration: underline;
        }

        .welcome-button:hover {
            color: #f0f0f0;
        }

        .header-buttons {
            display: flex;
            align-items: center;
            gap: 10px;
        }

        .header-buttons button {
            padding: 8px 16px;
            font-size: 14px;
        }

        .header-search {
            margin-left: auto; /* Fa que la barra de cerca quedi centrada o a la dreta */
        }
    </style>
</head>
<body>
    <!-- Cabecera con barra de búsqueda, login y crear artículo -->
     <header class="header">
        <div class="header-content">
            <!-- Text de benvinguda a l'esquerra -->
            <c:choose>
                <c:when test="${isLoggedIn}">
                    <div class="welcome-container">
                        <form action="<c:url value='/Web/userInfo'/>" method="GET" style="margin: 0;">
                            <button type="submit" class="welcome-button">
                                Benvingut, <strong>${username}</strong>
                            </button>
                        </form>
                    </div>
                </c:when>
            </c:choose>

            <!-- Barra de búsqueda -->
            <div class="header-search">
                <form action="<c:url value='/search'/>" method="GET" class="search-form">
                    <input type="text" name="query" placeholder="Buscar artículos..." class="search-bar">
                    <button type="submit" class="search-button">🔍</button>
                </form>
            </div>

            <!-- Botons a la dreta -->
            <div class="header-buttons">
                <!-- Botó Filtrar -->
                <form action="<c:url value='/Web/filtrar'/>" method="GET">
                    <button type="submit">Filtrar</button>
                </form>
                <form action="<c:url value='/Web/createArticle'/>" method="GET">
                    <button type="submit">Crear Articles</button>
                </form>
                <c:choose>
                    <c:when test="${isLoggedIn}">
                        <!-- Botó de tancar sessió -->
                        <form action="<c:url value='/Web/Logout'/>" method="GET">
                            <button type="submit">Tanca Sessió</button>
                        </form>
                    </c:when>
                    <c:otherwise>
                        <!-- Botons de login i registre -->
                        <form action="<c:url value='/Web/login'/>" method="GET">
                            <button type="submit">Iniciar Sessió</button>
                        </form>
                        <form action="<c:url value='/Web/SignUp'/>" method="GET">
                            <button type="submit">Registrar-se</button>
                        </form>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </header>

    <!-- Contenedor principal centrado -->
    <main class="container">
        <h1>Crear Nou Article</h1>
        
        <!-- Formulario centrado -->
        <form action="<c:url value='/Web/filtrar'/>" method="POST" class="form-container">
            <div class="form-group">
                <label for="autor">Autor</label>
                <input type="text" id="autor" name="autor" class="form-input" >
            </div>
            <div class="form-group">
                <label for="topics">Tòpics</label>
                <input type="text" id="topics" name="topics" class="form-input" placeholder="Coma separada" >
            </div>
            <div class="form-group">
                <input type="hidden" name="data_publi" value="<%= new java.util.Date() %>">
            </div>

            <!-- Contenedor para los botones -->
            <div class="button-container">
                <!-- Botón para volver -->
                <div class="back-button-container">
                    <button type="button" onclick="goBack(event)" class="back-button">
                        Cancelar
                    </button>
                </div>

                <!-- Botón para crear artículo -->
                <div class="create-button-container">
                    <button type="submit" class="back-button">Cercar</button>
                </div>
            </div>
        </form>
    </main>

    <!-- Estilos personalizados -->
    <style>
        
        .welcome-container {
            display: flex;
            align-items: center;
            color: white;
            font-size: 20px; 
        }
        
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
            align-items: center; /* Alineación vertical */
            width: 100%;
            margin-top: 20px;
            gap: 20px;
        }

        .back-button-container,
        .create-button-container {
            display: flex;
            justify-content: center;
            align-items: center; /* Centra verticalmente el botón */
            width: 48%;
            margin: 0; /* Asegura que no haya margen extra */
            padding: 0;
        }

        /* Botones */
        .back-button,
        .create-button-container button {
            display: flex;
            align-items: center;
            justify-content: center;
            margin: 0;          /* Sin margen extra */
            padding: 0 20px;    /* Ajusta si quieres más/menos espacio horizontal */
            width: 100%;        /* Que llene el contenedor */
            max-width: 200px;
            height: 50px;       /* Misma altura */
            line-height: 1;     /* Asegura que no haya un salto de línea adicional */
            box-sizing: border-box;
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
