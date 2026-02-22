<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Primeiro Acesso - Colégio Barão</title>
    
    <!-- Fonte Inter -->
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;600&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/login.css">
</head>

<body>

<div class="main-content">

    <!-- Logo -->
     <img src="assets/img/logoCB.svg">

    <h1>Primeiro acesso</h1>

    <% String erro = (String) request.getAttribute("erro"); 
       if (erro != null) { %>
        <div class="error">
            <%= erro %>
        </div>
    <% } %>

    <form action="validarPreCadastro" method="post" class="login-form">
        
        <input type="text" 
               name="cpfMatricula" 
               class="login-input"
               placeholder="Digite seu CPF ou Matrícula"
               required>

        <button type="submit" class="login-button">
            Continuar
        </button>

    </form>

    <a href="login.jsp" class="voltar">Voltar para login</a>

</div>

</body>
</html>