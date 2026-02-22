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
    <title>Completar Cadastro - Alunos</title>
</head>
<body>
<div class="main-content">
    <img src="assets/img/logoCB.svg">

    <h1>Bem-vindo(a)!</h1>

    <form class="login-form" method="post" action="${pageContext.request.contextPath}/FinalizarCadastroAluno">
        <input type="text" name="nome" placeholder="Digite o seu nome completo" class="login-input">

        <input type="email" name="email" placeholder="Digite seu e-mail" class="login-input">

        <input type="password" name="senha" placeholder="Senha" class="login-input">

        <input type="password" name="confirmarSenha" placeholder="Confirmar senha" class="login-input">

        <button type="submit">Finalizar</button>

        <span style="color:red;">
            ${erro}
        </span>
    </form>
</div>
</body>
</html>
