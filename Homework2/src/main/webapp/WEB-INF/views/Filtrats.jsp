<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Homework2</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/estilPrincipal.css'/>">
</head>
<body>
    <header class="header">
        <div class="header-content">
            <div class="header-buttons">
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

    <main class="container">
        <h1>Articles</h1>
        <div class="article-container">
            <!-- Verificar si hay artículos -->
            <c:if test="${not empty articles}">
                <c:forEach var="article" items="${articles}">
                    <!-- Asignar valores por defecto a las variables -->
                    <c:set var="imagen" value="${article.imatge != null ? article.imatge : 'https://via.placeholder.com/150'}"/>
                    <c:set var="iconoCandado" value="${article.privat == true ? '🔒' : '🔓'}"/>

                    <!-- Eliminar el manejo de la fecha -->
                    <c:set var="fechaPubliValida" value="${article.data_publi != null ? article.data_publi.replace('[UTC]', '') : null}"/>

                    <div class="article" data-id="${article.id}">
                        <div class="article-details">
                            <img src="${imagen}" alt="${article.titol != null ? article.titol : 'Títol no disponible'}" class="article-img">
                            <p class="article-title">${article.titol != null ? article.titol : 'Títol no disponible'}</p>
                            <p><strong>${iconoCandado}</strong></p>
                            <p><strong>Autor:</strong> ${article.nom_Aut != null ? article.nom_Aut : 'Autor desconegut'}</p>
                            <p><strong>Publicació:</strong> Data no disponible</p> <!-- Texto fijo por ahora -->
                            <div class="article-views">
                                <span class="eye-icon">👁️</span> ${article.n_views}
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </c:if>
            <c:if test="${empty articles}">
                <p>No se encontraron artículos.</p>
            </c:if>
        </div>
    </main>

    <div class="back-button-container">
        <form action="<c:url value='/Web/Principal'/>" method="GET">
            <button type="submit" class="back-button">Volver a la página principal</button>
        </form>
    </div>
</body>
</html>
