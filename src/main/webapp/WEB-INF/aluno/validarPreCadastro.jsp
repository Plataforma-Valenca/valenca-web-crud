<%--
  Created by IntelliJ IDEA.
  User: enzomota-ieg
  Date: 18/02/2026
  Time: 20:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Validar Pré-Cadastro - Alunos</title>
    <link rel="stylesheet" href="assets/css/login.css">
</head>
<body>
<div class="main-content">
    <img src="assets/img/logoCB.svg">

    <h1>Valide sua Credencial!</h1>

    <form class="login-form" method="post" action="${pageContext.request.contextPath}/validarPreCadastro">
        <input type="text" name="inputValidacao" placeholder="Digite o seu cpf ou matrícula" class="login-input" required>

        <% if (request.getAttribute("erroLogin") != null) { %>
        <div class="error">
            <%= request.getAttribute("erroLogin") %>
        </div>
        <%} %>

        <button type="submit" class="login-button">Validar</button>
    </form>
</div>
</body>
</html>
