<%@ page import="org.example.projetodiogo.model.Usuario" %>
<%@ page import="org.example.projetodiogo.model.Aluno" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
    String nomeUsuario = (usuario != null) ? usuario.getNome() : "Usuário";
    String tipoUsuario = (usuario != null) ?
            usuario.getTipoUsuario().substring(0,1).toUpperCase() + usuario.getTipoUsuario().substring(1).toLowerCase() : "--";

    Aluno aluno = (Aluno) request.getAttribute("aluno");
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

<jsp:include page="/WEB-INF/views/componentes/sidebarAluno.jsp">
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

            <h4>Informações Gerais</h4>

            <div class="main-information-profile">

                <div class="primary-box">
                    <img src="${pageContext.request.contextPath}/assets/img/icon-profile.svg" height="300" >
                </div>

                <div class="secondary-box">
                    <div class="details-infos-box">
                        <h4>Nome Completo</h4>
                        <p><%= request.getAttribute("nomeAluno") != null ? request.getAttribute("nomeAluno") : "--" %></p>
                    </div>

                    <div class="details-infos-box">
                        <h4>Turma Atual</h4>
                        <p><%= request.getAttribute("nomeTurma") != null ? request.getAttribute("nomeTurma") : "--" %></p>
                    </div>
                </div>

                <div class="terciary-box">
                    <div class="details-infos-box">
                        <h4>N° de matrícula</h4>
                        <p><%= request.getAttribute("matricula") != null ? request.getAttribute("matricula") : "--" %></p>
                    </div>

                    <div class="details-infos-box">
                        <h4>Email Acadêmico</h4>
                        <p><%= request.getAttribute("email") != null ? request.getAttribute("email") : "--" %></p>
                    </div>
                </div>

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
            const popup = document.getElementById("popupMenu");
            if (popup) popup.style.display = "none";
        }
    }
</script>

</body>
</html>