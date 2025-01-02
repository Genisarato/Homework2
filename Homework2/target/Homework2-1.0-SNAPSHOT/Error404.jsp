<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ca">

<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Oops! Ha ocorregut un error</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" />
    <script src="<c:url value='/resources/js/jquery-1.11.1.min.js' />"></script>
    <script src="<c:url value='/resources/js/bootstrap.min.js' />"></script>
    <style>
        body {
            background-color: #f8f9fa;
            font-family: 'Arial', sans-serif;
            color: #333;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .error-container {
            text-align: center;
            max-width: 600px;
            margin: auto;
        }

        .error-container img {
            width: 100%;
            max-width: 400px;
            margin: 0 auto 20px;
            display: block;
            border-radius: 10px;
        }

        .error-container h1 {
            font-size: 2rem;
            color: #333;
            margin-bottom: 1rem;
        }

        .error-container p {
            font-size: 1rem;
            color: #6c757d;
            margin-bottom: 2rem;
        }

        .btn-home {
            display: inline-block;
            padding: 0.75rem 1.5rem;
            font-size: 1rem;
            font-weight: 600;
            color: #fff;
            background-color: #007bff;
            border: none;
            border-radius: .25rem;
            text-transform: uppercase;
            text-decoration: none;
            transition: background-color 0.3s ease;
        }

        .btn-home:hover {
            background-color: #0056b3;
        }
    </style>
</head>

<body>
    <div class="error-container">
        <!-- Usamos la imatge proporcionada -->
        <img src="https://img.freepik.com/premium-vector/something-went-wrong_701961-7090.jpg" alt="Oops! Ha ocorregut un error">
        <h1>Oops! Ha ocorregut un error</h1>
        <p>Alguna cosa no ha anat bé. Estem treballant per solucionar-ho. Torna-ho a intentar més tard o ves a la pàgina principal.</p>
        <a href="<c:url value='/Web/Principal' />" class="btn-home">Torna a l'inici</a>
    </div>
</body>

</html>
