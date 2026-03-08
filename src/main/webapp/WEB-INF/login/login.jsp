    <%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Colégio Valença</title>
    <link rel="icon" type="image/x-icon" href="src/main/webapp/assets/img/icone-colegio-valenca.svg">

    <link rel="stylesheet" href="src/main/webapp/assets/css/login.css">
</head>

<body>

<div class="login-content">

    <% String erro = (String) request.getAttribute("erro");
        if (erro != null) { %>
    <div class="error">
        <%= erro %>
    </div>
    <% } %>

    <div id="login-left-box">
        <div id="login-left-box-content">
            <div id="login-logo-valenca">
                <img src="src/main/webapp/assets/img/icone-colegio-valenca.svg">
                <h1>Colégio Valença</h1>
            </div>

            <div id="login-welcome-text">
                <h1>Seja bem-vindo(a)</h1>
                <p>Preencha as suas informações para acessar a plataforma.</p>
            </div>

            <form action="/login" method="post" class="login-form">

                <div id="login-form-details">
                    <div id="login-form-email">
                        <h5>E-mail / Nome de usuário</h5>
                        <input type="text"
                               name="email"
                               class="login-input"
                               placeholder="Digite o seu e-mail ou nome de usuário"
                               required>
                    </div>

                    <div id="login-form-senha">
                        <h5>Senha</h5>
                        <input type="password"
                               name="senha"
                               class="login-input"
                               placeholder="Senha"
                               required>
                    </div>

                    <a href="esqueceuSenha.jsp" class="esqueceu-senha">
                        Esqueci a senha
                    </a>
                </div>



                <div class="login-acesso">
                    <button type="submit" class="login-button">
                        Entrar
                    </button>

                    <div>
                        <p>Primeiro acesso?</p>
                        <a href="primeiroAcesso.jsp"
                           class="primeiro-acesso"
                           id="primeiro-acesso">
                            Clique aqui
                        </a>
                    </div>

                </div>



            </form>
        </div>
    </div>

    <div id="login-right-box">
        <img href="src/main/webapp/assets/img/login-image.png">
    </div>



</div>

</body>
</html>