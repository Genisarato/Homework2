<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Artículos</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/estilPrincipal.css">
</head>
<body>
    <!-- Cabecera de la página con botones -->
    <header class="header">
        <h1>Artículos Disponibles</h1>
        <div class="header-buttons">
            <form action="${pageContext.request.contextPath}/newArticle" method="GET">
                <button type="submit">Buscar</button>
            </form>
            <form action="${pageContext.request.contextPath}/articles" method="GET">
                <button type="submit">Login</button>
            </form>
        </div>
    </header>

    <!-- Contenedor principal para la cuadrícula de artículos -->
    <main class="container">
   <h1>Articles</h1>
    <game-container class="game-container">
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="<c:url value="/resources/js/LlistaArticles.js" />"></script>
    </game-container>

    </main>
</body>
</html>
