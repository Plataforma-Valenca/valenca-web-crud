    <%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Login - Colégio Barão</title>
    
    <!-- Fonte Inter -->
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;600&display=swap" rel="stylesheet">

</head>

<body>

<div class="main-content">

    <!-- Logo -->
    <img src="/src/main/webapp/assets/img/logoCB.svg" alt="Colégio Barão">

    <h1>Bem-vindo(a)!</h1>

    <!-- Exibir erro se existir -->
    <% String erro = (String) request.getAttribute("erro"); 
       if (erro != null) { %>
        <div class="error">
            <%= erro %>
        </div>
    <% } %>

    <form action="/login" method="post" class="login-form">
        
        <input type="text" 
               name="email" 
               class="login-input"
               placeholder="Digite o seu e-mail ou nome de usuário"
               required>

        <input type="password" 
               name="senha" 
               class="login-input"
               placeholder="Senha"
               required>

        <div class="links">
            <a href="esqueceuSenha.jsp" class="esqueceu-senha">
                Esqueci a senha
            </a>

            <a href="primeiroAcesso.jsp" 
               class="primeiro-acesso" 
               id="primeiro-acesso">
                Primeiro acesso
            </a>
        </div>

        <button type="submit" class="login-button">
            Entrar
        </button>

    </form>

</div>

</body>
</html>