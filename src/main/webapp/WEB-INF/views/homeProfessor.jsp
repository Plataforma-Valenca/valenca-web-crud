<%--
  Created by IntelliJ IDEA.
  User: enzomota-ieg
  Date: 05/02/2026
  Time: 19:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Área de Professores - Colégio Barão</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/componentes/sidebar.jsp">
    <jsp:param name="activePage" value="homeProfessor"/>
</jsp:include>
<div class="main-content">
    <img src="assets/img/logoCB.svg">

    <h1>Bem-vindo(a)!</h1>

    <form class="login-form" method="post" action="${pageContext.request.contextPath}">
        <input type="text" placeholder="Digite o seu nome completo" name="nome" class="login-input">
        <input type="text" placeholder="senha" name="senha" class="login-input">

        <a class="esqueceu-senha" href="${pageContext.request.contextPath}/">Esqueci minha senha</a>

        <button type="submit" class="login-button">Entrar</button>
    </form>
</div>
</body>
</html>
