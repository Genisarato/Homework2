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
                <form action="<c:url value='/Web/login'/>" method="GET">
                    <button type="submit">Login</button>
                </form>
            </div>
        </div>
    </header>

    <!-- Contenedor principal centrado -->
    <main class="container">
        <h1>Crear Nou Article</h1>
        
        <!-- Formulario centrado -->
        <form action="<c:url value='/Web/createArticle'/>" method="POST" class="form-container">
            <div class="form-group">
                <label for="titol">Títol</label>
                <input type="text" id="titol" name="titol" class="form-input" required>
            </div>
            <div class="form-group">
                <label for="imatge">Imatge (URL)</label>
                <input type="text" id="imatge" name="imatge" class="form-input" placeholder="Pega el link aquí" required>
            </div>
            <div class="form-group">
                <label for="autor">Autor</label>
                <input type="text" id="autor" name="autor" class="form-input" required>
            </div>
            <div class="form-group">
                <label for="topics">Tòpics</label>
                <input type="text" id="topics" name="topics" class="form-input" placeholder="Coma separada" required>
            </div>
            <div class="form-group">
                <label for="privat">Privat</label>
                <select id="privat" name="privat" class="form-input" required>
                    <option value="true">Sí</option>
                    <option value="false">No</option>
                </select>
            </div>
            <div class="form-group">
                <label for="descripcio">Descripció</label>
                <textarea id="descripcio" name="descripcio" class="form-input" rows="4" required></textarea>
            </div>
            <div class="form-group">
                <input type="hidden" name="data_publi" value="<%= new java.util.Date() %>"> <!-- La fecha se establece automáticamente -->
            </div>
            <div class="form-group">
                <button type="submit" class="submit-button">Crear Article</button>
            </div>
        </form>
    </main>

    <!-- Estilos personalizados -->
    <style>
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

        .submit-button {
            background-color: #333;
            color: white;
            border: none;
            padding: 10px 20px;
            font-size: 16px;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .submit-button:hover {
            background-color: #555;
        }
    </style>
</body>
</html>
