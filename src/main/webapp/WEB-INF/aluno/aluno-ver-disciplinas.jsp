<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.example.projetodiogo.model.Disciplina" %>
<%@ page import="org.example.projetodiogo.model.DTO.DisciplinasResumoDTO" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <title>Colégio Valença</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/page-grid.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/list-card.css">
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/assets/img/icone-colegio-valenca.svg">
</head>
<body>

<jsp:include page="/WEB-INF/views/componentes/aluno-sidebar.jsp">
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
                    for (DisciplinasResumoDTO d : disciplinaList) {
            %>
            <a class="card-items"
               href="${pageContext.request.contextPath}/aluno/detalhesDisciplina?idDisciplina=<%= d.getIdDisciplina() %>">
                <div class="card-items-infos">
                    <h4><%= d.getNomeFormatado() %></h4>
                    <p style="font-weight: 400; color: #535353"><%= d.getNomeProfessor() %></p>
                </div>

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