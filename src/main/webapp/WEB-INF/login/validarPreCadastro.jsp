<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Primeiro Acesso - Colégio Valença</title>

    <link rel="icon" type="image/x-icon"
          href="${pageContext.request.contextPath}/assets/img/icone-colegio-valenca.svg">

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/css/login.css">

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/css/style.css">
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
                    <h2>Primeiro acesso</h2>
                    <p>Insira seus dados abaixo para validar seu cadastro na plataforma.</p>
                </div>

                <form method="post"
                      action="${pageContext.request.contextPath}/validarPreCadastro"
                      class="form-group">

                    <div class="form-group-inputs">
                        <div class="form-group-inputs-box">

                            <div class="form-control input-primary">
                                <h5>CPF ou Matrícula</h5>
                                <input type="text"
                                       placeholder="Digite seu CPF ou Matrícula"
                                       name="inputValidacao"
                                       required>
                            </div>

                        </div>

                        <% if (request.getAttribute("erro") != null) { %>
                        <div class="error">
                            <div class="mensagem" id="erro">
                                <%= request.getAttribute("erro") %>
                            </div>
                        </div>
                        <% } %>

                    </div>

                    <div class="form-group-enter">
                        <button type="submit" class="login-button btn-primary">
                            Continuar
                        </button>

                        <div class="login-first-access">
                            <p>Já possui uma conta?</p>
                            <a href="index.jsp"
                               class="login-first-access-link">
                                Voltar para login
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