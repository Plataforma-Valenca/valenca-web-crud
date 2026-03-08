<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.example.projetodiogo.model.Usuario" %>

<%
    // Lógica consolidada de usuário
    Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
    String nomeUsuario = (usuario != null) ? usuario.getNome() : "Usuário";
    String cargoUsuario = (usuario != null) ?
            usuario.getTipoUsuario().substring(0, 1).toUpperCase() + usuario.getTipoUsuario().substring(1).toLowerCase() : "";
%>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Colégio Valença</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/page-grid.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/list-card.css">
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/assets/img/icone-colegio-valenca.svg">

    <style>
        /* Estilização do Popup Menu para garantir o funcionamento correto */
        .popup-menu {
            display: none;
            position: absolute;
            top: 110%; /* Posiciona logo abaixo do perfil */
            right: 0;
            background: white;
            border-radius: 10px;
            box-shadow: 0 4px 15px rgba(0,0,0,0.1);
            padding: 10px;
            width: 170px;
            z-index: 100;
        }
        .popup-menu a {
            text-decoration: none;
            color: #333;
            display: flex;
            align-items: center;
            justify-content: space-between;
            padding: 10px;
            border-radius: 6px;
            font-size: 14px;
        }
        .popup-menu a:hover {
            background: #f5f5f5;
        }
    </style>
</head>

<body>
<jsp:include page="/WEB-INF/views/componentes/sidebarAdm.jsp"/>

<div class="page-content">
    <header class="header-home">
        <div class="header-profile" onclick="togglePopup()" style="position: relative;">

            <img src="${pageContext.request.contextPath}/assets/img/personagem.png" alt="Perfil">

            <div class="header-profile-infos">
                <b><%= nomeUsuario %></b>
                <p><%= cargoUsuario %></p>
            </div>

            <div id="popupMenu" class="popup-menu">
                <a href="${pageContext.request.contextPath}/logout">
                    Sair da conta
                    <img src="${pageContext.request.contextPath}/assets/img/icon-logout.svg" height="18">
                </a>
            </div>
        </div>
    </header>

    <main class="main-home">
        <div class="main-home-title">
            <h1>Olá, o que você procura?</h1>
        </div>

    </main>
</div>

<script>
    function togglePopup() {
        const popup = document.getElementById("popupMenu");
        popup.style.display = (popup.style.display === "block") ? "none" : "block";
    }

    // Fecha o popup se clicar em qualquer lugar fora do perfil
    window.onclick = function(event) {
        if (!event.target.closest('.header-profile')) {
            const popup = document.getElementById("popupMenu");
            if (popup) popup.style.display = "none";
        }
    }
</script>

</body>
</html>