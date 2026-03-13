<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.projetodiogo.model.Boletim" %>
<%@ page import="org.example.projetodiogo.model.Observacao" %>
<%@ page import="org.example.projetodiogo.model.Turma" %>

<html>
<head>
    <title>Colégio Valença</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/page-grid.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/aluno.css">
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/assets/img/icone-colegio-valenca.svg">
</head>
<body>
<jsp:include page="/WEB-INF/views/componentes/sidebarProfessor.jsp">
    <jsp:param name="activePage" value="turmas" />
</jsp:include>

<div class="page-content">
    <header class="page-grid-header">
        <header class="page-grid-header">
            <div class="page-grid-header-state">
                <div class="page-grid-header-state">
                    <a href="${pageContext.request.contextPath}/professor/turmasProfessor">Turmas</a>
                    <p>></p>
                    <a href="${pageContext.request.contextPath}/professor/verAlunos?idTurma=<%= request.getAttribute("idTurma") %>&nomeTurma=<%= request.getAttribute("nomeTurma") %>">Alunos</a>
                    <p>></p>
                    <b>Detalhes</b>
                </div>
        </header>

        <h1 class="page-grid-header-title">
            <%= request.getAttribute("nomeAluno") != null ? request.getAttribute("nomeAluno") : "--" %>
        </h1>
    </header>

    <main class="page-grid-main" style="gap: 5vh; padding-bottom: 80px;">
        <div class="aluno-details-infos">
            <div class="details-infos-box">
                <h4>Matrícula</h4>
                <p><%= request.getAttribute("matricula") != null ? request.getAttribute("matricula") : "--" %></p>
            </div>

            <div class="details-infos-box">
                <h4>Turma</h4>
                <p><%= request.getAttribute("nomeTurma") != null ? request.getAttribute("nomeTurma") : "--" %></p>
            </div>
        </div>

        <div class="aluno-details-notas">
            <div class="aluno-details-notas-header">
                <h4>Notas</h4>
                <button class="btn-primary" style="width: 100px">+ Adicionar</button>
            </div>

            <div class="aluno-details-notas-table" style="margin-top: 2vh">
                <div class="header-table-list-row">
                    <h5 style="flex: 1;">N1</h5>
                    <h5 style="flex: 1;">N2</h5>
                    <h5 style="flex: 1;">Média Final</h5>
                </div>

                <%
                    List<Boletim> boletimList = (List<Boletim>) request.getAttribute("boletimList");
                    if (boletimList != null && !boletimList.isEmpty()) {
                        for (Boletim b : boletimList) {
                            String corN1 = (b.getMedia1() != null && b.getMedia1() >= 7) ? "blue" : "red";
                            String corN2 = (b.getMedia2() != null && b.getMedia2() >= 7) ? "blue" : "red";
                            String corFinal = (b.getMediaFinal() != null && b.getMediaFinal() >= 7) ? "blue" : "red";
                %>
                <div class="row-table-list-row" style="border-bottom: 1px solid #eee;">
                    <p style="flex: 1; color: <%= b.getMedia1() != null ? corN1 : "inherit" %>;">
                        <%= b.getMedia1() != null ? b.getMedia1() : "--" %>
                    </p>
                    <p style="flex: 1; color: <%= b.getMedia2() != null ? corN2 : "inherit" %>;">
                        <%= b.getMedia2() != null ? b.getMedia2() : "--" %>
                    </p>
                    <p style="flex: 1; color: <%= b.getMediaFinal() != null ? corFinal : "inherit" %>;">
                        <%= b.getMediaFinal() != null ? b.getMediaFinal() : "--" %>
                    </p>
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
                <button class="btn-primary" style="width: 100px">+ Adicionar</button>
            </div>

            <div class="aluno-details-notas-table" style="margin-top: 2vh">
                <div class="header-table-list-row">
                    <h5 style="flex: 1;">Data</h5>
                    <h5 style="flex: 3;">Descrição</h5>
                </div>

                <%
                    List<Observacao> obsList = (List<Observacao>) request.getAttribute("obsList");
                    if (obsList != null && !obsList.isEmpty()) {
                        for (Observacao obs : obsList) {
                %>
                <div class="row-table-list-row" style="border-bottom: 1px solid #eee;">
                    <p style="flex: 1;"><%= obs.getDataEnvio() != null ? obs.getDataEnvio() : "--" %></p>
                    <p style="flex: 3; color: #535353;"><%= obs.getDescricao() != null ? obs.getDescricao() : "--" %></p>
                    <img src="${pageContext.request.contextPath}/assets/img/icon-delete.svg" height="40">
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