<%@ page import="org.example.projetodiogo.model.Usuario" %>
<%@ page import="org.example.projetodiogo.model.Aluno" %>
<%@ page import="org.example.projetodiogo.dao.DTO.AlunoConsultaDtoDAO" %>
<%@ page import="org.example.projetodiogo.model.DTO.AlunoConsultaDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
    String tipoUsuario = (usuario != null) ?
            usuario.getTipoUsuario().substring(0,1).toUpperCase() + usuario.getTipoUsuario().substring(1).toLowerCase() : "--";

    AlunoConsultaDtoDAO alunoConsultaDtoDAO = new AlunoConsultaDtoDAO();
    AlunoConsultaDTO alunoConsulta = alunoConsultaDtoDAO.buscarPorCpf(usuario.getCpf());
%>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <title>Colégio Valença</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/page-grid.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/home.css"> <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/assets/img/icone-colegio-valenca.svg">

    <style>
        .header-profile img {
            height: 50px;
            width: 50px;
            object-fit: cover;
        }

        .primary-box img {
            height: 100px;
            width: 100px;
            border-radius: 50%;
        }

        .popup-menu {
            display: none;
            position: absolute;
            top: 110%;
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

        .main-information-profile {
            display: flex;
            align-items: center;
            gap: 30px;
            background: #fff;
            padding: 20px;
            border-radius: 12px;
            margin-top: 20px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.05);
        }

        .details-infos-box h4 {
            font-size: 12px;
            color: #999;
            margin: 0;
            text-transform: uppercase;
        }

        .details-infos-box p {
            font-size: 16px;
            font-weight: 600;
            margin: 5px 0 15px 0;
            color: #333;
        }
    </style>
</head>

<body>

<jsp:include page="/WEB-INF/views/componentes/sidebarAluno.jsp">
    <jsp:param name="activePage" value="home" />
</jsp:include>

<div class="page-content">

    <header class="header-home">
        <div class="header-profile" onclick="togglePopup()" style="position: relative;">

            <img src="${pageContext.request.contextPath}/assets/img/icon-woman-profile.svg" alt="Perfil">

            <div class="header-profile-infos">
                <b><%= alunoConsulta.getNome() %></b>
                <p><%= tipoUsuario %></p>
            </div>

            <div id="popupMenu" class="popup-menu">
                <a href="${pageontext.request.contextPath}/logout">
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

        <div class="main-home-grade">

            <h4>Informações Gerais</h4>

            <div class="main-information-profile">

                <div class="primary-box">
                    <img src="${pageContext.request.contextPath}/assets/img/icon-woman-profile.svg" alt="Avatar Aluno">
                </div>

                <div class="secondary-box">
                    <div class="details-infos-box">
                        <h4>Nome Completo</h4>
                        <p> <%= alunoConsulta.getNome() %> </p>
                    </div>

                    <div class="details-infos-box">
                        <h4>Turma Atual</h4>
                        <p><%= alunoConsulta.getTurma() != null ? alunoConsulta.getTurma() : "--" %></p>
                    </div>
                </div>

                <div class="terciary-box">
                    <div class="details-infos-box">
                        <h4>N° de matrícula</h4>
                        <p><%= alunoConsulta.getMatricula() != null ? alunoConsulta.getMatricula() : "--" %></p>
                    </div>

                    <div class="details-infos-box">
                        <h4>Email Acadêmico</h4>
                        <p><%= usuario.getEmail() != null ? usuario.getEmail() : "--" %></p>
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