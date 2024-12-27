<%@ page language="java" contentType="text/html; charset=UTF-8" 
    pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Detalls de l'article</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/estilPrincipal.css'/>">
</head>
<body>
    <!-- Cabecera fija -->
    <header class="header">
        <div class="header-content">
            <div class="header-search">
                <form action="<c:url value='/search'/>" method="GET" class="search-form">
                    <input type="text" name="query" placeholder="Buscar artículos..." class="search-bar">
                    <button type="submit" class="search-button">🔍</button>
                </form>
            </div>
            <div class="header-buttons">
                <form action="<c:url value='/Web/createArticle'/>" method="GET">
                    <button type="submit">Crear Articles</button>
                </form>
                <form action="<c:url value='/Web/login'/>" method="GET">
                    <button type="submit">Login</button>
                </form>
            </div>
        </div>
    </header>

    <!-- Contenedor principal -->
    <div class="container">
        <h1>Detalls de l'article</h1>

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
                <p>No hi ha dades disponibles per a aquest article.</p>
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
