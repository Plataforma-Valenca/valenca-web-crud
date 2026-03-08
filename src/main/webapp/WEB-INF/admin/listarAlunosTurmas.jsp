<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.projetodiogo.model.DTO.AlunoConsultaDTO" %>

<html>
<head>
    <title>Colégio Valença - Administração</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/page-grid.css">
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/assets/img/icone-colegio-valenca.svg">
</head>
<body>

<jsp:include page="/WEB-INF/views/componentes/sidebarAdm.jsp">
    <jsp:param name="activePage" value="turmas" />
</jsp:include>

<div class="page-content">
    <header class="page-grid-header">
        <div class="page-grid-header-state">
            <a href="${pageContext.request.contextPath}/admin/turmas">Turmas</a>
            <p>></p>
            <b>Alunos</b>
        </div>

        <h1 class="page-grid-header-title">
            Alunos da Turma
        </h1>
    </header>

    <main class="page-grid-main" style="gap: 5vh; padding-bottom: 80px;">
        <%-- Box de busca seguindo o padrão do sistema --%>
        <div class="top-box-page-grid-main">
            <form action="${pageContext.request.contextPath}/admin/verAlunosTurma" method="get" class="form-busca">
                <input type="hidden" name="idTurma" value="<%= request.getParameter("idTurma") %>">
                <div class="form-control">
                    <h5>Buscar aluno</h5>
                    <div class="form-control-action input-primary">
                        <input type="text" name="busca" placeholder="Nome ou matrícula">
                        <button type="submit" class="btn btn-primary">Buscar</button>
                    </div>
                </div>
            </form>
        </div>

        <div class="bottom-box-page-grid-main">
            <div class="page-grid-main-content">
                <div class="table-list-row">

                    <div class="header-table-list-row" style="display: flex">
                        <h4 style="flex: 2;">Nome do aluno</h4>
                        <h4 style="flex: 1;">Matrícula</h4>
                        <h4 style="flex: 1;">CPF</h4>
                        <h4 style="flex: 1;">Turma</h4>
                    </div>

                    <div class="body-table-list-row">
                        <%
                            List<AlunoConsultaDTO> alunosList = (List<AlunoConsultaDTO>) request.getAttribute("alunos");
                            if (alunosList != null && !alunosList.isEmpty()) {
                                for (AlunoConsultaDTO aluno : alunosList) {
                        %>
                        <a href="${pageContext.request.contextPath}/admin/verPerfilAluno?cpf=<%= aluno.getCpf() %>"
                           class="itens-per-table">
                            <div style="flex: 2;"><%= aluno.getNome() %></div>
                            <div style="flex: 1;"><%= aluno.getMatricula() %></div>
                            <div style="flex: 1;"><%= aluno.getCpf() %></div>
                            <div style="flex: 1;"><%= aluno.getTurma() %></div>
                        </a>
                        <%
                            }
                        } else {
                        %>
                        <div style="text-align:center; padding: 40px; color: #999;">
                            Nenhum aluno encontrado nesta turma.
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