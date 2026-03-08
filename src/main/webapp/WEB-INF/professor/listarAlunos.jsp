<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.projetodiogo.model.DTO.AlunoConsultaDTO" %>

<html>
<head>
    <title>Colégio Valença</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/page-grid.css">
    <link rel="icon" type="image/x-icon"
          href="${pageContext.request.contextPath}/assets/img/icone-colegio-valenca.svg">
</head>
<body>

    <jsp:include page="/WEB-INF/views/componentes/sidebarProfessor.jsp">
        <jsp:param name="activePage" value="listarAlunos" />
    </jsp:include>

    <div class="page-content">
        <header class="page-grid-header">

            <div class="page-grid-header-state">
                <a href="${pageContext.request.contextPath}/professor/turmasProfessor">Turmas</a>
                <p>></p>
                <b>Alunos</b>
            </div>

            <h1 class="page-grid-header-title">
                <%
                    String url = request.getParameter("nomeTurma");
                    if (url != null) {
                        out.print(url);
                    } else {
                        out.print("Turma: ");
                    }
                %>
            </h1>
        </header>

        <main class="page-grid-main" style="gap: 8%">
            <div class="top-box-page-grid-main">
                <form action="${pageContext.request.contextPath}/professor/verAlunos" method="get" class="form-busca">
                    <div class="form-control">
                        <h5>Buscar por matrícula</h5>

                        <div class="form-control-action input-primary" >
                            <input type="text" name="busca" placeholder="0000000" class="">
                            <button type="submit" class="btn btn-primary">Buscar</button>
                        </div>
                    </div>
                </form>
            </div>

            <div class="bottom-box-page-grid-main">
                <form method="get"
                      action="${pageContext.request.contextPath}/professor/verAlunos" class="page-grid-main-content">

                    <table class="table-list-row">
                        <thead class="header-table-list-row">
                        <tr>
                            <th>Nome do aluno</th>
                            <th>Matrícula</th>
                        </tr>
                        </thead>

                        <tbody class="body-table-list-row" >
                        <%
                            List<AlunoConsultaDTO> alunosList =
                                    (List<AlunoConsultaDTO>) request.getAttribute("alunosList");

                            if (alunosList != null && !alunosList.isEmpty()) {
                                for (AlunoConsultaDTO aluno : alunosList) {
                        %>
                        <tr>
                            <td>
                                <input type="checkbox"
                                       name="alunoId"
                                       value="<%= aluno.getMatricula() %>">
                            </td>
                            <td><%= aluno.getNome() %></td>
                            <td><%= aluno.getMatricula() %></td>
                        </tr>
                        <%
                            }
                        } else {
                        %>
                        <tr>
                            <td colspan="5" style="text-align:center;">
                                Nenhum aluno encontrado.
                            </td>
                        </tr>
                        <%
                            }
                        %>
                        </tbody>

                    </table>
                </form>
            </div>

        </main>
</div>

</body>
</html>