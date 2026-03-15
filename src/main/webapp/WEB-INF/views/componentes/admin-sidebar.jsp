<%@ page import="org.example.projetodiogo.model.Usuario" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/sidebar.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">

<%
    String activePage = request.getParameter("activePage");

    Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");

    String nomeUsuario = usuario != null ? usuario.getNome() : "Usuário";

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
                <a href="${pageContext.request.contextPath}/admin/dashboard">
                    <img src="${pageContext.request.contextPath}/assets/img/sidebar-icon-home.svg">
                    Home
                </a>
            </li>

            <li class="<%= "turmas".equals(activePage) ? "active" : "" %> menu-items-tab">
                <a href="${pageContext.request.contextPath}/admin/verTurmas">
                    <img src="${pageContext.request.contextPath}/assets/img/sidebar-icon-aluno.svg">

                    Turmas
                </a>
            </li>


            <li class="<%= "busca".equals(activePage) ? "active" : "" %> menu-items-tab">
                <a href="${pageContext.request.contextPath}/admin/verProfessores">
                    <img src="${pageContext.request.contextPath}/assets/img/sidebar-icon-professor.svg">

                    Professores
                </a>
            </li>

            <li class="<%= "resumoList".equals(activePage) ? "active" : "" %> menu-items-tab">
                <a href="${pageContext.request.contextPath}/admin/verDisciplinas">
                    <img src="${pageContext.request.contextPath}/assets/img/sidebar-icon-disciplinas.svg">

                    Disciplinas
                </a>
            </li>
        </ul>
    </div>

    <div class="sidebar-bottom-box">
        <img src="${pageContext.request.contextPath}/assets/img/sidebar-main-image.svg">
    </div>

</aside>