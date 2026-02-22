<%--
  Created by IntelliJ IDEA.
  User: enzomota-ieg
  Date: 08/02/2026
  Time: 01:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Completar Cadastro - Alunos</title>
    <link rel="stylesheet" href="assets/css/login.css">
</head>
<body>
<div class="main-content">
    <img src="assets/img/logoCB.svg">

    <h1>Bem-vindo(a)!</h1>

    <form class="login-form" method="post" action="${pageContext.request.contextPath}/FinalizarCadastroAluno">
        <input type="text" name="nome" placeholder="Digite o seu nome completo" class="login-input" required>

        <input type="email" name="email" placeholder="Digite seu e-mail" class="login-input" required>

        <input type="password" name="senha" placeholder="Senha" class="login-input" required>

        <input type="password" name="confirmarSenha" placeholder="Confirmar senha" class="login-input" required>

        <% if (request.getAttribute("erroLogin") != null) { %>
        <div class="error">
            <%= request.getAttribute("erroLogin") %>
        </div>
        <%} %>

        <button type="submit" class="login-button">Finalizar</button>
    </form>
</div>
</body>
</html>
