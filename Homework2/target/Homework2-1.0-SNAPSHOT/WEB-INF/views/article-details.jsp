<%@ page language="java" contentType="text/html; charset=UTF-8" 
    pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Detalls de l'article</title>
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

    <!-- Contenedor principal -->
    <div class="container">

        <c:choose>
            <c:when test="${not empty article}">
                <div class="article-detail">
                    <!-- Imagen del artículo -->
                    <div class="article-img-container">
                        <img src="${article.imatge}" class="article-img">
                    </div>

                    <div class="article-info">
                        <div class="article-header">
                            <h2>${article.titol}</h2>
                        </div>
                        
                        <!-- Meta información y topics -->
                        <div class="article-meta-topics">
                            <div class="article-meta">
                                <p>${article.nom_Aut} | ${article.data_publi}</p>
                            </div>
                            <div class="article-topics">
                                <p>${article.topics[0]}</p> <!-- Primer topic -->
                                <p>${article.topics[1]}</p> <!-- Segundo topic -->
                            </div>
                        </div>

                        <!-- Cuadro de la descripción -->
                        <div class="article-description-box">
                            <p>${article.descripcio}</p>
                        </div>

                        <!-- Vistas (ícono de ojo) -->
                        <div class="article-views">
                            <span class="eye-icon">👁️</span> ${article.n_views}
                        </div>
                    </div>

                    <!-- Botón para volver -->
                    <div class="back-button-container">
                        <form action="<c:url value='/Web/Principal'/>" method="GET">
                            <button type="submit" class="back-button">Volver a la pàgina principal</button>
                        </form>
                    </div>
                </div>
            </c:when>
            <c:otherwise>
                <p>Aquest article es privat, primer has d'iniciar sessió.</p>
                <form action="<c:url value='/Web/login'/>" method="GET">
                            <button type="submit" class="back-button">Iniciar Sessió</button>
                </form>
            </c:otherwise>
        </c:choose>
    </div>

    <style>
        /* Menos espacio arriba, para que no quede tan separado */
        .container {
            margin-top: 80px;
        }
        .article-detail {
            gap: 10px;
        }

        /* Contenedor de la imagen, centrado y de ancho 50% */
    .article-img-container {
        width: 80%;          /* El ancho que desees (por ejemplo, 80%) */
        margin: 0 auto;      /* Centra el contenedor horizontalmente */
    }

    .article-img {
        display: block;      
        width: 100%;         /* Ocupa todo el ancho del contenedor */
        height: auto;        /* Se ajusta la altura de forma proporcional */
        /* Si quieres mostrar la imagen completa sin recorte, quita o cambia:
           object-fit: cover; -> recorta la imagen para que llene el contenedor
           object-fit: contain; -> muestra todo sin recortar, pero puede añadir "espacios vacíos" */
        border-radius: 8px;
    }
    </style>
</body>
</html>
