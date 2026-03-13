<%--
  Created by IntelliJ IDEA.
  User: emanuellymeso-ieg
  Date: 09/03/2026
  Time: 10:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="pt-BR">

<head>

  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">

  <title>Nova senha - Colégio Valença</title>

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

          <h2>Redefinir senha</h2>

          <p>Digite sua nova senha.</p>

        </div>

        <% if (request.getAttribute("mensagemErro") != null) { %>

        <div class="mensagem">

          <%= request.getAttribute("mensagemErro") %>

        </div>

        <% } %>

        <form method="post"
              action="${pageContext.request.contextPath}/novaSenha"
              class="form-group">

          <input type="hidden"
                 name="token"
                 value="${token}">

          <div class="form-group-inputs">

            <div class="form-control input-primary">

              <h5>Nova senha</h5>

              <input type="password"
                     name="senha"
                     placeholder="Digite a nova senha"
                     required>

            </div>

          </div>

          <div class="form-group-enter">

            <button type="submit"
                    class="login-button btn-primary">

              Alterar senha

            </button>

          </div>

        </form>

        <div class="login-first-access">

          <a href="${pageContext.request.contextPath}/login.jsp"
             class="login-first-access-link">

            Voltar para login

          </a>

        </div>

      </div>

    </div>

  </div>

  <div class="login-right-box">

    <img src="${pageContext.request.contextPath}/assets/img/login-main-image.svg">

  </div>

</div>

</body>

</html>