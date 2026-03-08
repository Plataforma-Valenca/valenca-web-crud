<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.projetodiogo.model.*" %>
<%@ page import="org.example.projetodiogo.model.DTO.AlunoConsultaDTO" %>

<%
    AlunoConsultaDTO aluno = (AlunoConsultaDTO) request.getAttribute("alunoConsulta");
    List<Boletim> boletimList = (List<Boletim>) request.getAttribute("boletimList");
    List<Observacao> obsList = (List<Observacao>) request.getAttribute("obsList");
    String nomeTurma = (String) request.getAttribute("nomeTurma");
    String idTurma = (String) request.getAttribute("idTurma");
%>

<html>
<head>
    <title>Colégio Valença - Perfil do Aluno</title>
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
            <a href="${pageContext.request.contextPath}/admin/verTurmas">Turmas</a>
            <p>></p>
            <a href="${pageContext.request.contextPath}/admin/verAlunos?idTurma=<%= idTurma %>&nomeTurma=<%= nomeTurma %>">Alunos</a>
            <p>></p>
            <b>Detalhes</b>
        </div>

        <h1 class="page-grid-header-title">
            <%= aluno != null ? aluno.getNome() : "Aluno" %>
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
                <p><%= nomeTurma != null ? nomeTurma : "--" %></p>
            </div>
        </div>

        <%-- Tabela de Notas (Estilo Boletim) --%>
        <div class="aluno-details-notas">
            <div class="aluno-details-notas-header">
                <h4>Boletim Escolar</h4>
                <button class="btn-primary" style="width: 150px">Lançar Notas</button>
            </div>

            <div class="aluno-details-notas-table" style="margin-top: 2vh; overflow-x: auto;">
                <div class="header-table-list-row table-list-row">
                    <h5 style="flex:2">Disciplina</h5>
                    <h5 style="flex:0.8">N1</h5>
                    <h5 style="flex:0.8">N2</h5>
                    <h5 style="flex:1.2">Média 1º SEM</h5>
                    <h5 style="flex:0.8">N3</h5>
                    <h5 style="flex:0.8">N4</h5>
                    <h5 style="flex:1.2">Média 2º SEM</h5>
                    <h5 style="flex:1.2">Média Final</h5>
                    <h5 style="flex:1.5">Situação</h5>
                </div>

                <%
                    if (boletimList != null && !boletimList.isEmpty()) {
                        for (Boletim b : boletimList) {
                            // Lógica de cores baseada na nota
                            String corFinal = (b.getMediaFinal() != null && b.getMediaFinal() >= 7) ? "#4A90E2" : "#E74C3C";

                            String corSituacao = "#999";
                            if ("APROVADO".equalsIgnoreCase(b.getSituacao())) corSituacao = "#27ae60";
                            else if ("REPROVADO".equalsIgnoreCase(b.getSituacao())) corSituacao = "#E74C3C";
                            else if ("RECUPERAÇÃO".equalsIgnoreCase(b.getSituacao())) corSituacao = "#f39c12";
                %>
                <div class="row-table-list-row table-list-row" style="border-bottom:1px solid #eee; padding:12px 0;">
                    <p style="flex:2; font-weight:500;"><%= b.getNomeDisciplina() %></p>
                    <p style="flex:0.8;"><%= b.getMedia1() != null ? b.getMedia1() : "--" %></p>
                    <p style="flex:0.8;"><%= b.getMedia2() != null ? b.getMedia2() : "--" %></p>
                    <p style="flex:1.2; font-weight: bold;"><%= (b.getMedia1() != null && b.getMedia2() != null) ? (b.getMedia1()+b.getMedia2())/2 : "--" %></p>
                    <p style="flex:0.8;">--</p> <%-- N3 --%>
                    <p style="flex:0.8;">--</p> <%-- N4 --%>
                    <p style="flex:1.2;">--</p> <%-- Média 2º SEM --%>
                    <p style="flex:1.2; color:<%= corFinal %>; font-weight: bold;"><%= b.getMediaFinal() != null ? b.getMediaFinal() : "--" %></p>
                    <p style="flex:1.5; font-weight:600; color:<%= corSituacao %>;"><%= b.getSituacao() != null ? b.getSituacao() : "EM CURSO" %></p>
                </div>
                <%
                    }
                } else {
                %>
                <div style="text-align:center; padding:30px; color:#999;">Nenhum registro de nota encontrado.</div>
                <% } %>
            </div>
        </div>

        <%-- Seção de Observações --%>
        <div class="aluno-details-notas" style="margin-top: 5vh;">
            <div class="aluno-details-notas-header">
                <h4>Observações Comportamentais</h4>
                <button class="btn-primary" style="width: 100px" onclick="abrirModalObs()">+ Adicionar</button>
            </div>

            <div class="aluno-details-notas-table" style="margin-top: 2vh">
                <div class="header-table-list-row">
                    <h5 style="flex: 1;">Data</h5>
                    <h5 style="flex: 3;">Descrição</h5>
                    <h5 style="flex: 0.5;">Ações</h5>
                </div>

                <%
                    if (obsList != null && !obsList.isEmpty()) {
                        for (Observacao obs : obsList) {
                %>
                <div class="row-table-list-row" style="border-bottom: 1px solid #eee;">
                    <p style="flex: 1;"><%= obs.getDataEnvio() %></p>
                    <p style="flex: 3; color: #535353;"><%= obs.getDescricao() %></p>
                    <div style="flex: 0.5;">
                        <a href="${pageContext.request.contextPath}/admin/deletarObservacao?id=<%= obs.getId() %>&idAluno=<%= aluno.getIdAluno() %>">
                            <img src="${pageContext.request.contextPath}/assets/img/icon-delete.svg" height="25" style="cursor:pointer">
                        </a>
                    </div>
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
        // Lógica para abrir seu modal de cadastro de observação
        alert("Abrir modal de observação");
    }
</script>

</body>
</html>