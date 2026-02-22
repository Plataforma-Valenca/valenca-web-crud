<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String activePage = request.getParameter("activePage");
%>

<div class="sidebar">

    <!-- LOGO -->
    <div class="logo-area">
        <img src="${pageContext.request.contextPath}/assets/img/logoCB.svg" alt="Logo">
    </div>

    <!-- MENU -->
    <ul class="menu">

        <li class="<%= "homeAluno".equals(activePage) ? "active" : "" %>">
            <a href="${pageContext.request.contextPath}/home">
                <img src="${pageContext.request.contextPath}/assets/icons/home.svg" class="icon">
                <span>Home</span>
            </a>
        </li>

        <li class="<%= "listarAluno".equals(activePage) ? "active" : "" %>">
            <a href="${pageContext.request.contextPath}/alunos">
                <img src="${pageContext.request.contextPath}/assets/icons/aluno.svg" class="icon">
                <span>Alunos</span>
            </a>
        </li>

        <li class="<%= "configuracoes".equals(activePage) ? "active" : "" %>">
            <a href="${pageContext.request.contextPath}/config">
                <img src="${pageContext.request.contextPath}/assets/icons/config.svg" class="icon">
                <span>Configurações</span>
            </a>
        </li>

        <li>
            <a href="${pageContext.request.contextPath}/logout">
                <img src="${pageContext.request.contextPath}/assets/icons/logout.svg" class="icon">
                <span>Sair</span>
            </a>
        </li>

    </ul>

</div>