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

<jsp:include page="/WEB-INF/views/componentes/sidebarProfessor.jsp">
    <jsp:param name="activePage" value="turmas" />
</jsp:include>

<div class="page-content">
    <header class="page-grid-header">
        <div class="page-grid-header-state">
            <a href="${pageContext.request.contextPath}/admin/verTurmas">Turmas</a>
            <p>></p>
            <a href="${pageContext.request.contextPath}/admin/verAlunos?idTurma=<%= request.getAttribute("idTurma") %>&nomeTurma=<%= request.getAttribute("nomeTurma") %>">Alunos</a>
            <p>></p>
            <b>Detalhes</b>
        </div>

        <h1 class="page-grid-header-title">
            <%= request.getParameter("nomeTurma") != null ? request.getParameter("nomeTurma") : "--" %>
        </h1>
    </header>

    <main class="page-grid-main" style="gap: 5vh; padding-bottom: 80px;">
        <div class="top-box-page-grid-main">
            <form action="${pageContext.request.contextPath}/admin/verAlunos" method="get" class="form-busca">
                <div class="form-control">
                    <h5>Buscar por matrícula</h5>
                    <div class="form-control-action input-primary">
                        <input type="text" name="busca" placeholder="0000000">
                        <button type="submit" class="btn btn-primary">Buscar</button>
                    </div>
                </div>
            </form>
        </div>

        <div class="bottom-box-page-grid-main">
            <div class="page-grid-main-content">
                <div class="table-list-row">
                    <div class="header-table-list-row" style="display: flex">
                        <h4 style="flex: 1;">Nome do aluno</h4>
                        <h4 style="flex: 1;">Matrícula</h4>
                    </div>

                    <div class="body-table-list-row">
                        <%
                            List<AlunoConsultaDTO> alunosList = (List<AlunoConsultaDTO>) request.getAttribute("alunosList");
                            if (alunosList != null && !alunosList.isEmpty()) {
                                for (AlunoConsultaDTO aluno : alunosList) {
                        %>
                        <a href="${pageContext.request.contextPath}/admin/verPerfilAluno?idAluno=<%= aluno.getIdAluno()%>&idTurma=<%= request.getParameter("idTurma")%>&nomeAluno=<%= aluno.getNome() %>&matricula=<%= aluno.getMatricula() %>&nomeTurma=<%= aluno.getTurma() %>"
                           class="itens-per-table">
                            <div style="flex: 1;"><%= aluno.getNome() %></div>
                            <div style="flex: 1;"><%= aluno.getMatricula() %></div>
                        </a>
                        <%
                            }
                        } else {
                        %>
                        <div style="text-align:center; padding: 20px; color: #999;">
                            Nenhum aluno encontrado.
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