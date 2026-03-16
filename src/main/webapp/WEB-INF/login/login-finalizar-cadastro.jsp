<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Colégio Valença</title>

    <link rel="icon" type="image/x-icon"
          href="${pageContext.request.contextPath}/assets/img/icone-colegio-valenca.svg">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/login.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
</head>

<body>
    <div class="login-page">

        <div class="login-left-box">
            <div class="login-left-box-content">

                <div class="login-valenca-logo">
                    <img src="${pageContext.request.contextPath}/assets/img/icone-colegio-valenca.svg">
                    <h1>Colégio Valença</h1>
                </div>

                <div class="login-left-box-content-form">
                    <div class="login-left-box-content-title">
                        <h2>Seja bem-vindo(a)!</h2>
                        <p>Preencha as suas informações para acessar a plataforma.</p>
                    </div>

                    <form method="post" action="${pageContext.request.contextPath}/aluno/finalizarCadastroAluno" class="form-group">

                        <div class="form-group-inputs">
                            <div class="form-group-inputs-box">

                                <div class="form-control input-primary">
                                    <h5>Nome completo</h5>
                                    <input type="text"
                                           placeholder="Digite o seu nome completo"
                                           name="nome"
                                           required>
                                </div>

                                <div class="form-control input-primary">
                                    <h5>E-mail</h5>
                                    <input type="email"
                                           placeholder="Digite seu e-mail"
                                           name="email"
                                           required>
                                </div>

                                <div class="form-control input-primary">
                                    <h5>Senha</h5>
                                    <input type="password"
                                           placeholder="Digite sua senha"
                                           name="senha"
                                           required>
                                </div>

                                <div class="form-control input-primary">
                                    <h5>Confirmar senha</h5>
                                    <input type="password"
                                           placeholder="Confirme sua senha"
                                           name="confirmarSenha"
                                           required>
                                </div>

                                <button type="submit" class="login-button btn-primary">
                                    Finalizar
                                </button>

                            </div>

                            <div class="message-container">
                                <% if (request.getAttribute("erro") != null) { %>
                                <div class="mensagem" id="erroLogin">
                                    <%= request.getAttribute("erro") %>
                                </div>
                                <% } %>

                                <% if (request.getAttribute("erroLogin") != null) { %>
                                <div class="mensagem" id="erroLogin">
                                    <%= request.getAttribute("erroLogin") %>
                                </div>
                                <% } %>

                                <% if (request.getAttribute("mensagemSucesso") != null) { %>
                                <div class="mensagem" id="mensagemSucesso">
                                    <%= request.getAttribute("mensagemSucesso") %>
                                </div>
                                <% } %>
                            </div>

                        </div>

                        <div class="form-group-enter">
                            <button type="submit" class="login-button btn-primary" style="width: 100%">
                                Finalizar
                            </button>
                            <div class="login-first-access">
                                <p>Já tem uma conta?</p>
                                <a href="${pageContext.request.contextPath}/login" class="login-first-access-link">
                                    Faça login
                                </a>
                            </div>
                        </div>

                    </form>

                </div>

            </div>
        </div>

        <div class="login-right-box">
            <img src="${pageContext.request.contextPath}/assets/img/login-main-image.svg">
        </div>

    </div>

</body>
</html>
