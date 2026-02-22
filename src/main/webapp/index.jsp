<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - Colégio Barão</title>

    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: Arial, Helvetica, sans-serif;
        }

        body {
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            background: linear-gradient(135deg, #2e8b84, #4fa59c);
        }

        .card {
            width: 900px;
            height: 500px;
            background-color: #e9eeee;
            border-radius: 12px;
            box-shadow: 0px 8px 20px rgba(0, 0, 0, 0.2);

            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
        }

        .logo {
            width: 200px;
            margin-bottom: 20px;
        }

        h2 {
            margin-bottom: 30px;
            color: #555;
        }

        form {
            display: flex;
            flex-direction: column;
            align-items: center;
            width: 300px;
        }

        input {
            width: 100%;
            padding: 14px;
            margin-bottom: 15px;
            border-radius: 25px;
            border: none;
            background-color: #f2f2f2;
            box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
            outline: none;
        }

        .links {
            width: 100%;
            display: flex;
            justify-content: space-between;
            font-size: 12px;
            margin-bottom: 20px;
        }

        .links a {
            text-decoration: none;
            color: #888;
        }

        .login-button {
            width: 100%;
            padding: 14px;
            border-radius: 25px;
            border: none;
            background-color: #27b3a2;
            color: white;
            font-weight: bold;
            cursor: pointer;
            transition: 0.3s;
            box-shadow: 0px 5px 10px rgba(0, 0, 0, 0.15);
        }

        .login-button:hover {
            background-color: #1f9a8c;
        }

        .error {
            color: #c0392b;
            padding: 10px;
            border-radius: 8px;
            margin-bottom: 15px;
            width: 100%;
            text-align: center;
            font-size: 13px;
        }

        #mensagemSucesso {
            color: green;
        }
    </style>
</head>

<body>

<div class="card">

    <img src="${pageContext.request.contextPath}/assets/img/logoCB.svg"
         alt="Logo Colégio Barão"
         class="logo">

    <h2>Bem-vindo(a)!</h2>

    <form method="post" action="${pageContext.request.contextPath}/login">

        <input type="text"
               placeholder="Digite o seu e-mail ou nome de usuário"
               name="loginUsuario"
               required>

        <input type="password"
               placeholder="Senha"
               name="senhaUsuario"
               required>

        <div class="links">
            <a href="${pageContext.request.contextPath}/">
                Esqueci a senha
            </a>

            <a href="${pageContext.request.contextPath}/validarPreCadastro">
                Primeiro acesso
            </a>
        </div>

        <% if (request.getAttribute("erroLogin") != null) { %>
        <div class="error">
            <div class="mensagem" id="erroLogin">
                <%= request.getAttribute("erroLogin") %>
            </div>
                <%} %>

                <% if (request.getAttribute("mensagemSucesso") != null) { %>
            <div class="mensagem" id="mensagemSucesso">
                <%= request.getAttribute("mensagemSucesso") %>
            </div>
                <%} %>

        <button type="submit" class="login-button">
            Entrar
        </button>

    </form>

</div>

</body>
</html>