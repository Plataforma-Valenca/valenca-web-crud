<%@ page import="org.example.projetodiogo.model.Usuario" %>
<%@ page import="org.example.projetodiogo.model.Aluno" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Colégio Valença</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/page-grid.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/list-card.css">

    <link rel="icon" type="image/x-icon"
          href="${pageContext.request.contextPath}/assets/img/icone-colegio-valenca.svg">
</head>

<%
    String activePage = request.getParameter("activePage");

    Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");

    String nomeUsuario = "--";
    String tipoUsuario = "--";

    if (usuario != null) {
        nomeUsuario = usuario.getNome();

        String tipo = usuario.getTipoUsuario();
        tipoUsuario = tipo.substring(0,1).toUpperCase() + tipo.substring(1);
    }

    Aluno aluno = (Aluno) request.getAttribute("aluno");
%>

<body>

<jsp:include page="/WEB-INF/views/componentes/sidebarAluno.jsp">
    <jsp:param name="activePage" value="home" />
</jsp:include>

<div class="page-content">

    <header class="header-home">
        <div class="header-profile">

            <img src="${pageContext.request.contextPath}/assets/img/icon-woman-profile.svg" height="60">

            <div class="header-profile-infos">
                <b><%= nomeUsuario %></b>
                <p><%= tipoUsuario %></p>
            </div>

            <a href="${pageContext.request.contextPath}/index.jsp">
                <img src="${pageContext.request.contextPath}/assets/img/icon-logout.svg">
            </a>

        </div>
    </header>

    <main class="main-home">

        <div class="main-home-title">
            <h1>Olá, o que você procura?</h1>
        </div>

        <div class="main-home-grade">

            <h4>Informações</h4>

            <div class="main-information-profile">

                <div class="primary-box">
                    <img src="${pageContext.request.contextPath}/assets/img/icon-woman-profile.svg">
                </div>

                <div class="secondary-box">

                    <div class="details-infos-box">
                        <h4>Aluno</h4>
                        <p><%= aluno != null ? aluno.getNome() : "--" %></p>
                    </div>

                    <div class="details-infos-box">
                        <h4>Turma</h4>
                        <p><%= request.getAttribute("nomeTurma") != null ? request.getAttribute("nomeTurma") : "--" %></p>
                    </div>

                </div>

                <div class="terciary-box">

                    <div class="details-infos-box">
                        <h4>N° de matrícula</h4>
                        <p><%= request.getAttribute("matricula") != null ? request.getAttribute("matricula") : "--" %></p>
                    </div>

                    <div class="details-infos-box">
                        <h4>Email</h4>
                        <p><%= request.getAttribute("email") != null ? request.getAttribute("email") : "--" %></p>
                    </div>

                </div>

            </div>

        </div>

    </main>

</div>

</body>
</html>