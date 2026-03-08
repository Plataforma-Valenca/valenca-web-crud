<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="org.example.projetodiogo.model.Boletim" %>
<%@ page import="org.example.projetodiogo.model.Observacao" %>

<html>
<head>
    <title>Colégio Valença</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/page-grid.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/aluno.css">
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/assets/img/icone-colegio-valenca.svg">
</head>
<body>

<jsp:include page="/WEB-INF/views/componentes/sidebarAluno.jsp">
    <jsp:param name="activePage" value="disciplinas" />
</jsp:include>

<div class="page-content">
    <header class="page-grid-header">
        <div class="page-grid-header-state">
            <a href="${pageContext.request.contextPath}/aluno/VerDisciplinas">Disciplinas</a>
            <p>></p>
            <b>Detalhes</b>
        </div>

        <h1 class="page-grid-header-title">
            <%= request.getAttribute("nomeDisciplina") != null ? request.getAttribute("nomeDisciplina") : "--" %>
        </h1>
        <p class="professor-subtitle" style="color: #535353; margin-top: -10px;">
            Professor: <%= request.getAttribute("nomeProfessor") != null ? request.getAttribute("nomeProfessor") : "--" %>
        </p>
    </header>

    <main class="page-grid-main" style="gap: 5vh; padding-bottom: 80px; margin-top: 4vh;">
        <div class="aluno-details-notas">
            <div class="aluno-details-notas-header">
                <h4>Notas</h4>
            </div>

            <div class="aluno-details-notas-table" style="margin-top: 2vh">
                <div class="header-table-list-row">
                    <h5 style="flex: 1;">N1</h5>
                    <h5 style="flex: 1;">N2</h5>
                    <h5 style="flex: 1.5;">Média 1° SEM</h5>
                    <h5 style="flex: 1;">N1</h5>
                    <h5 style="flex: 1;">N2</h5>
                    <h5 style="flex: 1.5;">Média 1° SEM</h5>
                    <h5 style="flex: 1.5;">Média Final</h5>
                </div>

                <%
                    List<Boletim> boletimList = (List<Boletim>) request.getAttribute("boletimList");
                    if (boletimList != null && !boletimList.isEmpty()) {
                        for (Boletim b : boletimList) {
                            String corN1 = (b.getMedia1() != null && b.getMedia1() >= 7) ? "#4A90E2" : "#E74C3C";
                            String corN2 = (b.getMedia2() != null && b.getMedia2() >= 7) ? "#4A90E2" : "#E74C3C";
                %>
                <div class="row-table-list-row" style="border-bottom: 1px solid #eee;">
                    <p style="flex: 1; color: <%= corN1 %>;"><%= b.getMedia1() != null ? b.getMedia1() : "--" %></p>
                    <p style="flex: 1; color: <%= corN2 %>;"><%= b.getMedia2() != null ? b.getMedia2() : "--" %></p>
                    <p style="flex: 1.5; color: <%= corN2 %>;"><%= b.getMedia1() != null ? b.getMedia2() : "--" %></p>
                    <p style="flex: 1;">--</p>
                    <p style="flex: 1;">--</p>
                    <p style="flex: 1.5;">--</p>
                    <p style="flex: 1.5;">--</p>
                </div>
                <%
                    }
                } else {
                %>
                <div style="text-align: center; padding: 20px; color: #999;">--</div>
                <% } %>
            </div>
        </div>

        <div class="aluno-details-notas" style="margin-top: 5vh;">
            <div class="aluno-details-notas-header">
                <h4>Observações</h4>
            </div>

            <div class="aluno-details-notas-table" style="margin-top: 2vh">
                <div class="header-table-list-row">
                    <h5 style="flex: 1;">Data</h5>
                    <h5 style="flex: 4;">Observação</h5>
                </div>

                <%
                    List<Observacao> obsList = (List<Observacao>) request.getAttribute("obsList");
                    if (obsList != null && !obsList.isEmpty()) {
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                        for (Observacao obs : obsList) {
                %>
                <div class="row-table-list-row" style="border-bottom: 1px solid #eee;">
                    <p style="flex: 1;"><%= obs.getDataEnvio() != null ? sdf.format(obs.getDataEnvio()) : "--" %></p>
                    <p style="flex: 4; color: #535353;"><%= obs.getDescricao() != null ? obs.getDescricao() : "--" %></p>
                </div>
                <%
                    }
                } else {
                %>
                <div style="text-align: center; padding: 20px; color: #999;">Nenhuma observação registrada.</div>
                <% } %>
            </div>
        </div>
    </main>
</div>

</body>
</html>