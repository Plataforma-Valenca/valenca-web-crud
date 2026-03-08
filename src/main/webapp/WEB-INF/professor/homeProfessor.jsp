<%@ page import="org.example.projetodiogo.model.Usuario" %><%--
  Created by IntelliJ IDEA.
  User: yasminholando-ieg
  Date: 07/03/2026
  Time: 19:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Colégio Valença</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/home.css">
    <link rel="icon" type="image/x-icon"
          href="${pageContext.request.contextPath}/assets/img/icone-colegio-valenca.svg">
</head>

<%
    String activePage = request.getParameter("activePage");
    Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
    String nomeUsuario = usuario.getNome();
    String tipoUsuario = usuario.getTipoUsuario().substring(0, 1).toUpperCase() + usuario.getTipoUsuario().substring(1);
%>

<body>
    <jsp:include page="/WEB-INF/views/componentes/sidebarProfessor.jsp">
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

                <a href="${pageContext.request.contextPath}/index.jsp"><img src="${pageContext.request.contextPath}/assets/img/icon-logout.svg"></a>

            </div>
        </header>

        <main class="main-home">
            <div class="main-home-title">
                <h1>Olá, o que você procura?</h1>
            </div>

            <div class="main-home-grade">
                <h4>Aulas da semana</h4>
                <img>
            </div>
        </main>
    </div>
</body>
</html>
