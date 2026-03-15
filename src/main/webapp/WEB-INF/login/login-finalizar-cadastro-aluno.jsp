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
    <title>Colégio Valença</title>
    <link rel="icon" type="image/x-icon" href="src/main/webapp/assets/img/icone-colegio-valenca.svg">

    <link rel="stylesheet" href="src/main/webapp/assets/css/login.css">
</head>
<body>

<div class="finalizar-cadastro-content">

    <% String erro = (String) request.getAttribute("erro");
        if (erro != null) { %>
    <div class="error">
        <%= erro %>
    </div>
    <% } %>

    <div id="finalizar-cadastro-left-box">
        <div id="finalizar-cadastro-left-box-content">
            <div class="login-logo-valenca">
                <img src="src/main/webapp/assets/img/icone-colegio-valenca.svg">
                <h1>Colégio Valença</h1>
            </div>

            <div id="finalizar-cadastro-welcome-text">
                <h1>Seja bem-vindo(a)</h1>
                <p>Preencha as suas informações para acessar a plataforma.</p>
            </div>

            <form class="finalizar-cadastro-form" method="post" action="${pageContext.request.contextPath}/FinalizarCadastroAluno">

                <div id="finalizar-cadastro-form-nome">
                    <h5>Nome completo</h5>
                    <input type="text" name="nome" placeholder="Digite o seu nome completo" class="login-input" required>
                </div>

                <div id="finalizar-cadastro-form-email">
                    <h5>E-mail / Nome de usuário</h5>
                    <input type="email" name="email" placeholder="Digite seu e-mail" class="login-input" required>
                </div>

                <div id="finalizar-cadastro-form-senha">
                    <h5>Senha</h5>
                    <input type="password" name="senha" placeholder="Senha" class="login-input" required>
                </div>


                <div id="finalizar-cadastro-form-confirmar-senha">
                    <h5>Confirmar senha</h5>
                    <input type="password" name="confirmarSenha" placeholder="Confirmar senha" class="login-input" required>
                </div>

                <% if (request.getAttribute("erroLogin") != null) { %>
                <div class="error">
                    <%= request.getAttribute("erroLogin") %>
                </div>
                <%} %>

                <button type="submit" class="login-button">Finalizar</button>
            </form>

        </div>
    </div>

    <div id="finalizar-cadastro-right-box">
        <img href="src/main/webapp/assets/img/login-image.png">
    </div>
</div>
</body>
</html>
