<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.example.projetodiogo.model.Usuario" %>

<%
    Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
    String nomeUsuario = (usuario != null) ? usuario.getNome() : "Usuário";
    String tipoUsuario = (usuario != null) ?
            usuario.getTipoUsuario().substring(0, 1).toUpperCase() + usuario.getTipoUsuario().substring(1).toLowerCase() : "";
%>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <title>Colégio Valença</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/home.css">
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/assets/img/icone-colegio-valenca.svg">
</head>

<body>
<jsp:include page="/WEB-INF/views/componentes/professor-sidebar.jsp">
    <jsp:param name="activePage" value="home" />
</jsp:include>

<div class="page-content">
    <header class="header-home">
        <div class="header-profile" onclick="togglePopup()" style="position: relative;">

            <img src="${pageContext.request.contextPath}/assets/img/icon-profile.svg" height="300" >

            <div class="header-profile-infos">
                <b><%= nomeUsuario %></b>
                <p><%= tipoUsuario %></p>
            </div>

            <div id="popupMenu" class="popup-menu">
                <a href="${pageContext.request.contextPath}/logout">
                    Sair da conta
                    <img src="${pageContext.request.contextPath}/assets/img/icon-logout.svg" class="btn-logout">
                </a>
            </div>
        </div>
    </header>

    <main class="main-home">
        <div class="main-home-title">
            <h1>Olá, o que você procura?</h1>
        </div>

        <div class="main-home-grade">
            <div>
                <h4>Atividades administrativas</h4>
                <p style="margin-top: 10px; color: #999;">
                    Utilize o menu lateral para gerenciar alunos, turmas e professores.
                </p>
            </div>
        </div>
    </main>
</div>

<script src="${pageContext.request.contextPath}/assets/js/profile-logout.js"></script>

</body>
</html>