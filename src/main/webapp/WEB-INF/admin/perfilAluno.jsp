<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.projetodiogo.model.*" %>
<%@ page import="org.example.projetodiogo.model.DTO.AlunoConsultaDTO" %>

<%
    AlunoConsultaDTO aluno = (AlunoConsultaDTO) request.getAttribute("alunoConsulta");
    String turma = (String) request.getAttribute("turma");
    List<Boletim> boletimList = (List<Boletim>) request.getAttribute("boletimList");
    List<Observacao> obsList = (List<Observacao>) request.getAttribute("obsList");
%>

<html>
<head>
    <title>Colégio Valença - Detalhes do Aluno</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/page-grid.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/aluno.css">
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
            <a href="${pageContext.request.contextPath}/admin/verAlunosTurma?idTurma=<%= request.getAttribute("idTurma") %>">Alunos</a>
            <p>></p>
            <b>Detalhes</b>
        </div>

        <h1 class="page-grid-header-title">
            <%= aluno != null ? aluno.getNome() : "--" %>
        </h1>
    </header>

    <main class="page-grid-main" style="gap: 5vh; padding-bottom: 80px;">

        <%-- Cards de Informação --%>
        <div class="aluno-details-infos">
            <div class="details-infos-box">
                <h4>Matrícula</h4>
                <p><%= aluno != null ? aluno.getMatricula() : "--" %></p>
            </div>

            <div class="details-infos-box">
                <h4>Turma</h4>
                <p><%= turma != null ? turma : "--" %></p>
            </div>
        </div>

        <%-- Seção de Notas --%>
        <div class="aluno-details-notas">
            <div class="aluno-details-notas-header">
                <h4>Notas</h4>
            </div>

            <div class="aluno-details-notas-table" style="margin-top: 2vh">
                <div class="header-table-list-row">
                    <h5 style="flex: 1;">Média 1° SEM</h5>
                    <h5 style="flex: 1;">Média 2° SEM</h5>
                    <h5 style="flex: 1;">Média Final</h5>
                </div>

                <%
                    if (boletimList != null && !boletimList.isEmpty()) {
                        for (Boletim b : boletimList) {
                            String corFinal = (b.getMediaFinal() != null && b.getMediaFinal() >= 7) ? "blue" : "red";
                %>
                <div class="row-table-list-row" style="border-bottom: 1px solid #eee;">
                    <p style="flex: 1;"><%= b.getMedia1() != null ? b.getMedia1() : "--" %></p>
                    <p style="flex: 1;"><%= b.getMedia2() != null ? b.getMedia2() : "--" %></p>
                    <p style="flex: 1; color: <%= b.getMediaFinal() != null ? corFinal : "inherit" %>;">
                        <%= b.getMediaFinal() != null ? b.getMediaFinal() : "--" %>
                    </p>
                </div>
                <%
                    }
                } else {
                %>
                <div style="text-align: center; padding: 20px; color: #999;">Nenhuma nota lançada.</div>
                <% } %>
            </div>
        </div>

        <%-- Seção de Observações --%>
        <div class="aluno-details-notas" style="margin-top: 5vh;">
            <div class="aluno-details-notas-header">
                <h4>Observações</h4>
                <button class="btn-primary" style="width: 100px" onclick="abrirModalObs()">+ Adicionar</button>
            </div>

            <div class="aluno-details-notas-table" style="margin-top: 2vh">
                <div class="header-table-list-row">
                    <h5 style="flex: 1;">Data</h5>
                    <h5 style="flex: 3;">Descrição</h5>
                </div>

                <%
                    if (obsList != null && !obsList.isEmpty()) {
                        for (Observacao obs : obsList) {
                %>
                <div class="row-table-list-row" style="border-bottom: 1px solid #eee;">
                    <p style="flex: 1;"><%= obs.getDataEnvio() != null ? obs.getDataEnvio() : "--" %></p>
                    <p style="flex: 3; color: #535353;"><%= obs.getDescricao() != null ? obs.getDescricao() : "--" %></p>
                    <a href="${pageContext.request.contextPath}/admin/deletarObservacao?id=<%= obs.getId() %>">
                        <img src="${pageContext.request.contextPath}/assets/img/icon-delete.svg" height="30" style="cursor:pointer">
                    </a>
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

<script>
    function abrirModalObs(){
        const modal = document.getElementById("modal-obs-bg");
        if(modal) modal.style.display = "flex";
    }
    function fecharModalObs(){
        const modal = document.getElementById("modal-obs-bg");
        if(modal) modal.style.display = "none";
    }
</script>

</body>
</html>