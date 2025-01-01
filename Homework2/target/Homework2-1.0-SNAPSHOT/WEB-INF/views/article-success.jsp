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
                <form action="<c:url value='/Web/createArticle'/>" method="GET">
                    <button type="submit">Crear Artículo</button>
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