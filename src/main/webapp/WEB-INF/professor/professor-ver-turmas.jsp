<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.projetodiogo.model.Turma" %>

<html>
<head>
    <title>Colégio Valença</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/page-grid.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/list-card.css">

    <link rel="icon" type="image/x-icon"
          href="${pageContext.request.contextPath}/assets/img/icone-colegio-valenca.svg">
</head>

<body>

<jsp:include page="/WEB-INF/views/componentes/sidebarProfessor.jsp">
    <jsp:param name="activePage" value="turmas"/>
</jsp:include>

<div class="page-content">

    <header class="page-grid-header" style="height: 5rem; margin-top: 5rem">
        <h1 class="page-grid-header-title">Turmas</h1>
    </header>

    <main class="page-grid-main">
        <div class="page-grid-main-content card-grid">
            <%
                List<Turma> turmasList = (List<Turma>) request.getAttribute("turmasList");

                if (turmasList != null && !turmasList.isEmpty()) {
                    for (Turma turma : turmasList) {
            %>
            <a class="card-items"
               href="${pageContext.request.contextPath}/professor/verAlunos?idTurma=<%= turma.getId() %>&nomeTurma=<%= turma.getNome()%>">
                <%= turma.getNome() %>
            </a>
            <%
                }
            } else {
            %>
            <p class="no-data">Nenhuma turma encontrada.</p>
            <%
                }
            %>
        </div>
    </main>

</div>

</body>
</html>