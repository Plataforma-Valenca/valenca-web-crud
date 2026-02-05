<%--
  Created by IntelliJ IDEA.
  User: enzomota-ieg
  Date: 05/02/2026
  Time: 19:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Área de Alunos - Colégio Barão</title>
</head>
<body>
<div class="main-content">
    <img src="assets/img/logoCB.svg">

    <h1>Bem-vindo(a)!</h1>

    <form class="login-form" method="post" action="${pageContext.request.contextPath}">
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
