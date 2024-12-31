<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Homework2</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/estilPrincipal.css'/>">
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
    <!-- Cabecera amb text de benvinguda, barra de búsqueda i botons -->
    <header class="header">
        <div class="header-content">
            <!-- Text de benvinguda a l'esquerra -->
            <c:choose>
                <c:when test="${isLoggedIn}">
                    <div class="welcome-container">
                        <form action="<c:url value='/userInfo'/>" method="GET" style="margin: 0;">
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

    <!-- Contenedor principal centrat -->
    <main class="container">
        <h1>Articles</h1>
        <div class="article-container">
            <!-- Aquí es mostraran els articles -->
        </div>
    </main>

    <!-- Scripts -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="<c:url value='/resources/js/LlistaArticles.js'/>"></script>
</body>
</html>
