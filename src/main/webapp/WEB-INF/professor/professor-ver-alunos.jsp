<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.projetodiogo.model.DTO.AlunoConsultaDTO" %>

<html>
<head>
    <title>Colégio Valença</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/page-grid.css">
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/assets/img/icone-colegio-valenca.svg">
</head>
<body>

<jsp:include page="/WEB-INF/views/componentes/professor-sidebar.jsp">
    <jsp:param name="activePage" value="turmas" />
</jsp:include>

<div class="page-content">

    <div class="page-grid-header-state">
        <div class="page-grid-header-state-content">
            <a href="${pageContext.request.contextPath}/professor/verTurmas">Turmas</a>
            <p>></p>
            <b>Alunos</b>
        </div>
    </div>

    <header class="page-grid-header">
        <h1 class="page-grid-header-title">
            <%= request.getParameter("nomeTurma") != null ? request.getParameter("nomeTurma") : "--" %>
        </h1>
    </header>

    <main class="page-grid-main">

        <div class="top-box-page-grid-main">
            <form action="${pageContext.request.contextPath}/professor/verAlunos" method="get" class="form-busca">
                <input type="hidden" name="idTurma" value="<%= request.getParameter("idTurma") %>">
                <input type="hidden" name="nomeTurma" value="<%= request.getParameter("nomeTurma") %>">
                <div class="form-control">
                    <h5>Buscar por matrícula</h5>
                    <div class="form-control-action input-primary">
                        <input type="text" name="busca" placeholder="0000000"
                               value="<%= request.getParameter("busca") != null ? request.getParameter("busca") : "" %>">
                        <button type="submit" class="btn btn-primary">Buscar</button>
                    </div>
                </div>
            </form>
        </div>

        <div class="bottom-box-page-grid-main">
            <div class="page-grid-main-content">
                <div class="table-list-row">

                    <div class="header-table-list-row" style="display:flex;">
                        <h4 style="flex:1;">Nome do aluno</h4>
                        <h4 style="flex:1;">Matrícula</h4>
                    </div>

                    <div class="body-table-list-row">
                        <%
                            List<AlunoConsultaDTO> alunosList = (List<AlunoConsultaDTO>) request.getAttribute("alunosList");
                            if (alunosList != null && !alunosList.isEmpty()) {
                                for (AlunoConsultaDTO aluno : alunosList) {
                        %>
                        <a href="${pageContext.request.contextPath}/professor/detalhesAluno?idAluno=<%= aluno.getIdAluno() %>&idTurma=<%= request.getParameter("idTurma") %>&nomeTurma=<%= java.net.URLEncoder.encode(aluno.getTurma() != null ? aluno.getTurma() : "", "UTF-8") %>&nomeAluno=<%= java.net.URLEncoder.encode(aluno.getNome() != null ? aluno.getNome() : "", "UTF-8") %>&matricula=<%= aluno.getMatricula() %>"
                           class="itens-per-table">
                            <div style="flex:1;"><%= aluno.getNome() %></div>
                            <div style="flex:1;"><%= aluno.getMatricula() %></div>
                        </a>
                        <%
                            }
                        } else {
                        %>
                        <div style="display:flex; flex-direction:column; align-items:center;">
                            <img src="${pageContext.request.contextPath}/assets/img/search-not-found.svg" height="200">
                            <p style="color:#999; text-align:center; padding:20px;">Nenhum aluno encontrado.</p>
                        </div>
                        <% } %>
                    </div>

                </div>
            </div>
        </div>

    </main>
</div>

</body>
</html>