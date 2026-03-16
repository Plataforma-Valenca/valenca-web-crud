<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.projetodiogo.model.Boletim" %>
<%@ page import="org.example.projetodiogo.model.Observacao" %>
<%@ page import="java.time.format.DateTimeFormatter" %>

<html>
<head>
    <title>Colégio Valença</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/page-grid.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/aluno.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/modal.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/detalhes-aluno.css">
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
            <a href="${pageContext.request.contextPath}/professor/verAlunos?idTurma=<%= request.getAttribute("idTurma") %>&nomeTurma=<%= java.net.URLEncoder.encode(request.getAttribute("nomeTurma") != null ? (String) request.getAttribute("nomeTurma") : "", "UTF-8") %>">Alunos</a>
            <p>></p>
            <b>Detalhes</b>
        </div>
    </div>

    <header class="page-grid-header">
        <h1 class="page-grid-header-title">
            <%= request.getAttribute("nomeAluno") != null ? request.getAttribute("nomeAluno") : "--" %>
        </h1>
    </header>

    <main class="page-grid-main" style="gap:5vh; padding-bottom:80px;">

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

        <!-- NOTAS -->
        <div class="aluno-details-notas">
            <div class="aluno-details-notas-header">
                <h4>Notas</h4>
                <button class="btn-primary"
                        onclick="abrirModalNotas('<%= request.getAttribute("idAluno") %>')">
                    + Adicionar
                </button>
            </div>

            <div class="aluno-details-notas-table" style="margin-top:2vh;">
                <div class="header-table-list-row">
                    <h5 style="flex:1;">N1</h5>
                    <h5 style="flex:1;">N2</h5>
                    <h5 style="flex:1;">Média Final</h5>
                    <h5 style="flex:0.3;"></h5>
                </div>

                <%
                    List<Boletim> boletimList = (List<Boletim>) request.getAttribute("boletimList");
                    if (boletimList != null && !boletimList.isEmpty()) {
                        for (Boletim b : boletimList) {
                            String corN1    = (b.getMedia1()     != null && b.getMedia1()     >= 7) ? "blue" : "red";
                            String corN2    = (b.getMedia2()     != null && b.getMedia2()     >= 7) ? "blue" : "red";
                            String corFinal = (b.getMediaFinal() != null && b.getMediaFinal() >= 7) ? "blue" : "red";
                            String n1Str    = b.getMedia1()     != null ? String.valueOf(b.getMedia1())     : "null";
                            String n2Str    = b.getMedia2()     != null ? String.valueOf(b.getMedia2())     : "null";
                %>
                <div class="row-table-list-row" style="border-bottom:1px solid #eee;">
                    <p style="flex:1; color:<%= b.getMedia1()     != null ? corN1    : "inherit" %>;"><%= b.getMedia1()     != null ? b.getMedia1()     : "--" %></p>
                    <p style="flex:1; color:<%= b.getMedia2()     != null ? corN2    : "inherit" %>;"><%= b.getMedia2()     != null ? b.getMedia2()     : "--" %></p>
                    <p style="flex:1; color:<%= b.getMediaFinal() != null ? corFinal : "inherit" %>;"><%= b.getMediaFinal() != null ? b.getMediaFinal() : "--" %></p>
                    <i class="fa-solid fa-pen" style="cursor:pointer; flex:0.3;"
                       onclick="abrirModalEditarNota('<%= b.getIdNota() %>', '<%= n1Str %>', '<%= n2Str %>')">
                    </i>
                </div>
                <%
                    }
                } else {
                %>
                <div style="text-align:center; padding:20px; color:#999;">Nenhuma nota registrada.</div>
                <% } %>
            </div>
        </div>

        <!-- OBSERVAÇÕES -->
        <div class="aluno-details-notas">
            <div class="aluno-details-notas-header">
                <h4>Observações</h4>
                <button class="btn-primary"
                        onclick="abrirModalObservacao('<%= request.getAttribute("idAluno") %>', '<%= request.getAttribute("idProfessor") %>')">
                    + Adicionar
                </button>
            </div>

            <div class="aluno-details-notas-table" style="margin-top:2vh;">
                <div class="header-table-list-row">
                    <h5 style="flex:1;">Data</h5>
                    <h5 style="flex:3;">Descrição</h5>
                    <h5 style="flex:0.3;"></h5>
                </div>

                <%
                    List<Observacao> obsList = (List<Observacao>) request.getAttribute("obsList");
                    if (obsList != null && !obsList.isEmpty()) {
                        for (Observacao obs : obsList) {
                            String data = obs.getDataEnvio()
                                    .toLocalDateTime()
                                    .format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                %>
                <div class="row-table-list-row" style="border-bottom:1px solid #eee;">
                    <p style="flex:1;"><%= obs.getDataEnvio() != null ? data : "--" %></p>
                    <p style="flex:3; color:#535353;"><%= obs.getDescricao() != null ? obs.getDescricao() : "--" %></p>
                    <img src="${pageContext.request.contextPath}/assets/img/icon-delete.svg"
                         height="40" style="cursor:pointer;"
                         onclick="abrirModalExcluirObservacao('<%= obs.getId() %>')">
                </div>
                <%
                    }
                } else {
                %>
                <div style="text-align:center; padding:20px; color:#999;">Nenhuma observação registrada.</div>
                <% } %>
            </div>
        </div>

    </main>
</div>

<jsp:include page="/WEB-INF/views/modais/professor-modal-detalhes-aluno.jsp"/>
<script src="${pageContext.request.contextPath}/assets/js/professor-modal-detalhes-aluno.js"></script>

</body>
</html>