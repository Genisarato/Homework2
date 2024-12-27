<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Homework2</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/estilPrincipal.css'/>">
</head>
<body>
    <!-- Cabecera con barra de búsqueda, login y crear artículo -->
    <header class="header">
        <div class="header-content">
            <!-- Barra de búsqueda -->
            <div class="header-search">
                <form action="<c:url value='/search'/>" method="GET" class="search-form">
                    <input type="text" name="query" placeholder="Buscar artículos..." class="search-bar">
                    <button type="submit" class="search-button">🔍</button>
                </form>
            </div>

            <!-- Botones a la derecha -->
            <div class="header-buttons">
                <!-- Nuevo botón para filtrar -->
                <form action="<c:url value='/Web/filtrar'/>" method="GET">
                    <button type="submit">Filtrar</button>
                </form>
                <form action="<c:url value='/Web/createArticle'/>" method="GET">
                    <button type="submit">Crear Articles</button>
                </form>
                <form action="<c:url value='/Web/login'/>" method="GET">
                    <button type="submit">Login</button>
                </form>
            </div>
        </div>
    </header>

    <!-- Contenedor principal centrado -->
    <main class="container">
        <h1>Articles</h1>
        <div class="article-container">
            <!-- Aquí se mostrarán los artículos -->
        </div>
    </main>

    <!-- Scripts -->
     <!-- Scripts -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="<c:url value='/resources/js/LListaFiltrats.js'/>"></script>
    <script>
        // Aquí, 'articles' es una variable que contiene el JSON de los artículos que pasaste desde el backend.
        var articles = JSON.parse('${articles}');  // Esto inyecta el JSON directamente en la variable de JavaScript

        // Aquí es donde puedes usar la variable 'articles' en tu código JS
        $(document).ready(function () {
            carregarArticles(articles);  // Pasamos los artículos al script
        });
    </script>
    <!-- Botón para volver -->
    <div class="back-button-container">
        <form action="<c:url value='/Web/Principal'/>" method="GET">
            <button type="submit" class="back-button">Volver a la pàgina principal</button>
        </form>
    </div>
</body>
</html>
