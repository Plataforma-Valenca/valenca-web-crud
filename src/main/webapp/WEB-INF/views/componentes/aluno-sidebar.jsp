<%@ page import="org.example.projetodiogo.model.Usuario" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/sidebar.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/loading-button.css">
<script src="${pageContext.request.contextPath}/assets/js/loading-button.js" defer></script>

<%
    String activePage = request.getParameter("activePage");

    Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");

    String nomeUsuario = usuario != null ? usuario.getNome() : "Usuário";
    String tipoUsuario = (usuario != null) ?
            usuario.getTipoUsuario().substring(0, 1).toUpperCase() + usuario.getTipoUsuario().substring(1) : "";
%>

<aside class="sidebar">
    <div class="sidebar-valenca-logo">
        <div class="sidebar-valenca-logo-content">
            <img src="${pageContext.request.contextPath}/assets/img/icone-colegio-valenca.svg" height="30">
            <h3>Colégio Valença</h3>
        </div>
    </div>

    <div class="sidebar-top-box">
        <ul class="sidebar-tabs-menu">

            <li class="<%= "home".equals(activePage) ? "active" : "" %> menu-items-tab">
                <a href="${pageContext.request.contextPath}/aluno/homeAluno">
                    <img src="${pageContext.request.contextPath}/assets/img/sidebar-icon-home.svg">
                    Home
                </a>
            </li>

            <li class="<%= "disciplinas".equals(activePage) ? "active" : "" %> menu-items-tab">
                <a href="${pageContext.request.contextPath}/aluno/VerDisciplinas">
                    <img src="${pageContext.request.contextPath}/assets/img/sidebar-icon-disciplinas.svg">
                    Disciplinas
                </a>
            </li>

            <li class="<%= "boletim".equals(activePage) ? "active" : "" %> menu-items-tab">
                <a href="${pageContext.request.contextPath}/aluno/VerBoletim">
                    <img src="${pageContext.request.contextPath}/assets/img/sidebar-icon-boletim.svg">
                    Boletim
                </a>
            </li>

        </ul>
    </div>

    <div class="sidebar-bottom-box">
        <img src="${pageContext.request.contextPath}/assets/img/sidebar-main-image.svg">
    </div>
</aside>