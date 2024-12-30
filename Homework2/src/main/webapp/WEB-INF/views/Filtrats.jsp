<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Homework2</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/estilPrincipal.css'/>">
    <style>
        .large-back-button-container {
            text-align: center;
            margin-top: 20px;
        }
        .large-back-button {
            background-color: #005599; /* Color de fondo */
            color: white;
            font-size: 20px;
            padding: 15px 50px; /* Aumenta el tamaño para hacerlo más largo */
            border: none;
            border-radius: 5px;
            cursor: pointer;
            text-decoration: none;
            width: 100%; /* Hace el botón más largo ocupando el 100% del ancho disponible */
            max-width: 400px; /* Limita el tamaño máximo */
            display: inline-block;
        }
        .large-back-button:hover {
            background-color: #004477; /* Cambio de color al pasar el cursor */
        }
    </style>
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
                <form action="<c:url value='/Web/SignUp'/>" method="GET">
                    <button type="submit">Registrar-se</button>
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

    <!-- Botón grande para volver -->
    <div class="large-back-button-container">
        <form action="<c:url value='/Web/Principal'/>" method="GET">
            <button type="submit" class="large-back-button">Volver a la página principal</button>
        </form>
    </div>

    <script>
        var articles = [
            <c:forEach var="article" items="${articles}" varStatus="status">
                {
                    id: "<c:out value='${article.id}'/>",
                    titol: "<c:out value='${article.titol}'/>",
                    nom_Aut: "<c:out value='${article.nom_Aut}'/>",
                    imatge: "<c:out value='${article.imatge}'/>",
                    privat: <c:out value="${article.privat}"/>,
                    data_publi: "<c:out value='${article.data_publi}'/>",
                    n_views: <c:out value="${article.n_views}"/>
                }<c:if test="${!status.last}">,</c:if>
            </c:forEach>
        ];

        console.log("Articles:", articles);
    </script>

    <!-- Scripts -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="<c:url value='/resources/js/LListaFiltrats.js'/>"></script>
</body>
</html>
