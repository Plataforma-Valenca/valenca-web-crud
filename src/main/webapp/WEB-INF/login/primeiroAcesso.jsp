<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Primeiro Acesso - Colégio Valença</title>
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
                <h1>Primeiro acesso</h1>
                <p>Preencha a sua informação para validar o cadastro.</p>
            </div>

            <form action="validarPreCadastro" method="post" class="login-form">

                <div id="login-form-details">
                    <div id="login-form-cpf">
                        <h5>CPF ou Matrícula</h5>
                        <input type="text"
                               name="cpfMatricula"
                               class="login-input"
                               placeholder="Digite seu CPF ou Matrícula"
                               required>
                    </div>
                </div>

                <div class="login-acesso">
                    <button type="submit" class="login-button">
                        Continuar
                    </button>

                    <div>
                        <p>Já possui cadastro?</p>
                        <a href="login.jsp"
                           class="primeiro-acesso"
                           id="voltar-login">
                            Voltar para login
                        </a>
                    </div>
                </div>

            </form>
        </div>
    </div>

    <div id="login-right-box">
        <img src="src/main/webapp/assets/img/login-image.png">
    </div>

</div>

</body>
</html>