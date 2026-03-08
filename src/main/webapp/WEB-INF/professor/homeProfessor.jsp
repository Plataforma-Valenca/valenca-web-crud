<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.example.projetodiogo.model.Usuario" %>

<html>
<head>
    <title>Colégio Valença</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/home.css">
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/assets/img/icone-colegio-valenca.svg">
    <style>
        .popup-menu {
            display: none;
            position: absolute;
            top: 70px;
            right: 20px;
            background: white;
            border-radius: 10px;
            box-shadow: 0 4px 15px rgba(0,0,0,0.1);
            padding: 10px;
            width: 150px;
            z-index: 100;
        }
        .popup-menu a {
            text-decoration: none;
            color: #333;
            display: block;
            padding: 10px;
            border-radius: 6px;
            font-size: 14px;
        }
        .popup-menu a:hover {
            background: #f5f5f5;
        }
    </style>
</head>

<%
    Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
    String nomeUsuario = (usuario != null) ? usuario.getNome() : "Usuário";
    String tipoUsuario = (usuario != null) ?
            usuario.getTipoUsuario().substring(0, 1).toUpperCase() + usuario.getTipoUsuario().substring(1) : "";
%>

<body>
<jsp:include page="/WEB-INF/views/componentes/sidebarAdm.jsp">
    <jsp:param name="activePage" value="home" />
</jsp:include>

<div class="page-content">
    <header class="header-home">
        <div class="header-profile" onclick="togglePopup()" style="cursor: pointer; position: relative;">
            <img src="${pageContext.request.contextPath}/assets/img/icon-woman-profile.svg" height="60">

            <div class="header-profile-infos">
                <b><%= nomeUsuario %></b>
                <p><%= tipoUsuario %></p>
            </div>

            <img src="${pageContext.request.contextPath}/assets/img/icon-logout.svg">

            <div id="popupMenu" class="popup-menu">
                <a href="${pageContext.request.contextPath}/logout">Sair da conta</a>
            </div>
        </div>
    </header>

    <main class="main-home">
        <div class="main-home-title">
            <h1>Olá, o que você procura?</h1>
        </div>

        <div class="main-home-grade">
            <h4>Atividades administrativas</h4>
            <div style="margin-top: 20px; color: #999;">
                Utilize o menu lateral para gerenciar alunos, turmas e professores.
            </div>
        </div>
    </main>
</div>

<script>
    function togglePopup() {
        const popup = document.getElementById("popupMenu");
        popup.style.display = (popup.style.display === "block") ? "none" : "block";
    }

    window.onclick = function(event) {
        if (!event.target.closest('.header-profile')) {
            document.getElementById("popupMenu").style.display = "none";
        }
    }
</script>
</body>
</html>