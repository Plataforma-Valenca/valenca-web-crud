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

        <form class="login-form" method="post" action="${pageContext.request.contextPath}/login">
            <input type="text" placeholder="Email ou usuário" name="loginUsuario" class="login-input" required>
            <input type="text" placeholder="Senha" name="senhaUsuario" class="login-input" required>

            <div class="links">
                <a class="esqueceu-senha" href="${pageContext.request.contextPath}/">Esqueci minha senha</a>

                <a class="primeiro-acesso" id="primeiro-acesso" href="${pageContext.request.contextPath}/validarPreCadastro">Primeiro acesso</a>
            </div>

            <% if (request.getAttribute("erroLogin") != null) { %>
            <div class="mensagem" id="erroLogin">
                <%= request.getAttribute("erroLogin") %>
            </div>
            <%} %>

            <% if (request.getAttribute("mensagemSucesso") != null) { %>
            <div class="mensagem" id="mensagemSucesso">
                <%= request.getAttribute("mensagemSucesso") %>
            </div>
            <%} %>

            <button type="submit" class="login-button">Entrar</button>
        </form>
    </div>
</body>
</html>