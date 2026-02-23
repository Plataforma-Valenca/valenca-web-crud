<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/sidebar.css">

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


        <li class="<%= "listarAluno".equals(activePage) ? "active" : "" %>">
            <a href="${pageContext.request.contextPath}/admin/verAlunos">
                <img src="${pageContext.request.contextPath}/assets/img/aluno.png" class="icon">
                <span>Alunos</span>
            </a>
        </li>

        <li class="<%= "listarProfessores".equals(activePage) ? "active" : "" %>">
            <a href="${pageContext.request.contextPath}/admin/verProfessores">
                <img src="${pageContext.request.contextPath}/assets/img/professor.svg" class="icon">
                <span>Professores</span>
            </a>
        </li>

        <li class="<%= "listarDisciplinas".equals(activePage) ? "active" : "" %>">
            <a href="${pageContext.request.contextPath}/admin/verDisciplinas">
                <img src="${pageContext.request.contextPath}/assets/img/disciplinas.png" class="icon">
                <span>Disciplinas</span>
            </a>
        </li>

    

    </ul>

</div>