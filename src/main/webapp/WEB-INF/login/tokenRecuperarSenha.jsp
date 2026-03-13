<%--
  Created by IntelliJ IDEA.
  User: emanuellymeso-ieg
  Date: 08/03/2026
  Time: 22:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<!DOCTYPE html>

<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Recuperar senha (Código de verificação) - Colégio Valença</title>

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
                    <h2>Recuperar a senha</h2>
                    <p>Digite o código de seis dígitos que foi enviado no seu e-mail.</p>
                </div>

                <form method="post"
                      action="${pageContext.request.contextPath}/verificarCodigo"
                      class="form-group">

                    <div class="form-group-inputs">

                        <div class="form-group-inputs-box">

                            <div class="form-control input-primary">
                                <h5>Código de Verificação</h5>

                                <input type="text"
                                       name="codigo"
                                       placeholder="Digite o código"
                                       maxlength="6"
                                       required>
                            </div>

                        </div>

                        <% if (request.getAttribute("mensagem") != null) { %>
                        <div class="mensagem">
                            <%= request.getAttribute("mensagem") %>
                        </div>
                        <% } %>

                    </div>

                    <div class="form-group-enter">

                        <button type="submit"
                                class="login-button btn-primary">
                            Prosseguir
                        </button>

                        <div class="login-first-access">
                            <p>Não recebeu o código?</p>

                            <a href="${pageContext.request.contextPath}/recuperarSenha"
                               class="login-first-access-link">
                                Enviar novamente
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

