<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - Colégio Barão</title>
    <link rel="stylesheet" href="assets/css/login.css">
</head>
<body>
    <div class="main-content">
        <img src="assets/img/logoCB.svg">

        <h1>Bem-vindo(a)!</h1>

        <form class="login-form" method="post" action="${pageContext.request.contextPath}/loginIdentificacao">
            <input type="text" placeholder="Digite o seu nome completo" name="nome" class="login-input">

            <% if (request.getAttribute("erroLogin") != null) { %>
            <div class="error">
                <%= request.getAttribute("erroLogin") %>
            </div>
            <%} %>

            <button type="submit" class="login-button">Entrar</button>
        </form>
    </div>
</body>
</html>