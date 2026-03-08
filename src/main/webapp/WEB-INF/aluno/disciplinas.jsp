<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.example.projetodiogo.model.Disciplina" %>
<%@ page import="org.example.projetodiogo.model.DTO.DisciplinasResumoDTO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <title>Colégio Valença</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/page-grid.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/list-card.css">

    <link rel="icon" type="image/x-icon"
          href="${pageContext.request.contextPath}/assets/img/icone-colegio-valenca.svg">
</head>
<body>

<jsp:include page="/WEB-INF/views/componentes/sidebarAluno.jsp">
    <jsp:param name="activePage" value="disciplinas" />
</jsp:include>

<div class="page-content">

    <header class="page-grid-header">
        <h1 class="page-grid-header-title">Disciplinas</h1>
    </header>

    <main class="page-grid-main">
        <div class="page-grid-main-content card-grid">
            <%
                List<DisciplinasResumoDTO> disciplinaList = (List<DisciplinasResumoDTO>) request.getAttribute("disciplinaList");

                if (disciplinaList != null && !disciplinaList.isEmpty()) {
                    for (DisciplinasResumoDTO d: disciplinaList) {
            %>
            <a class="card-items"
               href="${pageContext.request.contextPath}/aluno/VerDisciplinas?id=<%= d.getIdDisciplina() %>">
                <%= d.getNomeFormatado() %>
                <img src="${pageContext.request.contextPath}/assets/img/icon-logout.svg">
            </a>
            <%
                }
            } else {
            %>
            <p class="no-data">Nenhuma disciplina encontrada.</p>
            <%
                }
            %>
        </div>
    </main>

</div>

</body>
</html>