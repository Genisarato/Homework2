<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Articles</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/estilPrincipal.css'/>">
</head>
<body>
    <!-- Cabecera con botones a la derecha -->
    <header class="header">
        <div class="header-content">
            <!-- Barra de búsqueda -->
            <div class="header-search">
                <form action="<c:url value='/search'/>" method="GET" class="search-form">
                    <input type="text" name="query" placeholder="Buscar artículos..." class="search-bar">
                    <button type="submit" class="search-button">🔍</button>
                </form>
            </div>

            <!-- Botón de login a la derecha -->
            <div class="header-buttons">
                <form action="<c:url value='/articles'/>" method="GET">
                    <button type="submit">Login</button>
                </form>
            </div>
        </div>
    </header>

    <!-- Contenedor principal centrado -->
    <main class="container">
        <h1>Articles</h1>
        <div class="article-container"></div>
    </main>

    <!-- Scripts -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="<c:url value='/resources/js/LlistaArticles.js'/>"></script>
</body>
</html>
