<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.projetodiogo.model.DTO.AlunoConsultaDTO" %>
<%@ page import="java.util.ArrayList" %>

<%
    String nomeTurma = (String) request.getAttribute("nomeTurma");
    if (nomeTurma == null) nomeTurma = request.getParameter("nomeTurma");

    String idTurma = (String) request.getAttribute("idTurma");
    if (idTurma == null) idTurma = request.getParameter("idTurma");
%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Colégio Valença</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/page-grid.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/modal.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/assets/img/icone-colegio-valenca.svg">
</head>

<body>
<jsp:include page="/WEB-INF/views/componentes/flash-message.jsp"/>

<jsp:include page="/WEB-INF/views/componentes/admin-sidebar.jsp">
    <jsp:param name="activePage" value="turmas"/>
</jsp:include>

<div class="page-content">

    <div class="page-grid-header-state">
        <div class="page-grid-header-state-content">
            <a href="${pageContext.request.contextPath}/admin/verTurmas">Turmas</a>
            <p>></p>
            <b>Alunos</b>
        </div>
    </div>

    <header class="page-grid-header">
        <div class="page-grid-header-title-plus-btn">
            <h1 class="page-grid-header-title"><%= nomeTurma != null ? nomeTurma : "--" %></h1>
            <button class="btn-primary" onclick="abrirModalCadastroAluno()">+ Cadastrar</button>
        </div>
    </header>

    <main class="page-grid-main">

        <div class="top-box-page-grid-main">
            <form action="${pageContext.request.contextPath}/admin/verAlunos" method="get" class="form-busca">
                <input type="hidden" name="idTurma" value="<%= idTurma %>">
                <input type="hidden" name="nomeTurma" value="<%= nomeTurma %>">
                <div class="form-control">
                    <h5>Buscar por matrícula</h5>
                    <div class="form-control-action input-primary">
                        <input type="text" name="busca" placeholder="0000000"
                               value="<%= request.getParameter("busca") != null ? request.getParameter("busca") : "" %>">
                        <button type="submit" class="btn btn-primary">Buscar</button>
                    </div>
                </div>
            </form>
        </div>

        <div class="bottom-box-page-grid-main">
            <div class="page-grid-main-content">
                <div class="table-list-row">

                    <div class="header-table-list-row" style="display:flex">
                        <h4 style="flex:1;">Nome do aluno</h4>
                        <h4 style="flex:1;">Matrícula</h4>
                        <h4 style="flex:1;">Turma</h4>
                        <h4 style="flex:0.3;"></h4>
                    </div>

                    <div class="body-table-list-row">
                        <%
                            ArrayList<AlunoConsultaDTO> alunosList =
                                    (ArrayList<AlunoConsultaDTO>) request.getAttribute("alunos");

                            if (alunosList != null && !alunosList.isEmpty()) {
                                for (AlunoConsultaDTO aluno : alunosList) {
                                    String nomeEncoded = aluno.getNome() != null ? java.net.URLEncoder.encode(aluno.getNome(), "UTF-8") : "";
                                    String turmaEncoded = aluno.getTurma() != null ? java.net.URLEncoder.encode(aluno.getTurma(), "UTF-8") : "";
                        %>

                        <div class="itens-per-table" style="display:flex; align-items:center;">

                            <a style="display:contents; text-decoration:none; color:inherit;"
                               href="${pageContext.request.contextPath}/admin/detalhesAluno?idAluno=<%= aluno.getIdAluno() %>&idTurma=<%= idTurma %>&nomeTurma=<%= java.net.URLEncoder.encode(nomeTurma != null ? nomeTurma : "", "UTF-8") %>">
                                <div style="flex:1;"><%= aluno.getNome() %></div>
                                <div style="flex:1;"><%= aluno.getMatricula() %></div>
                                <div style="flex:1;"><%= aluno.getTurma() %></div>
                            </a>

                            <div style="display:flex; gap:10px; flex:0.3;">
                                <i class="fa-solid fa-pen" style="cursor:pointer;"
                                   onclick="abrirModalEditarAluno('<%= aluno.getIdAluno() %>', '<%= aluno.getNome() %>', '<%= aluno.getMatricula() %>')">
                                </i>
                                <i class="fa-solid fa-trash" style="color:var(--color-error); cursor:pointer;"
                                   onclick="abrirModalExcluirAluno('<%= aluno.getIdAluno() %>')">
                                </i>
                            </div>

                        </div>

                        <%
                            }
                        } else {
                        %>
                        <div style="display:flex; flex-direction:column; align-items:center;">
                            <img src="${pageContext.request.contextPath}/assets/img/search-not-found.svg" height="200">
                            <p style="color:#999; text-align:center; padding:20px;">Nenhum aluno encontrado.</p>
                        </div>
                        <% } %>

                    </div>
                </div>
            </div>
        </div>

    </main>
</div>

<jsp:include page="/WEB-INF/views/modais/admin-modal-ver-alunos.jsp"/>
<script src="${pageContext.request.contextPath}/assets/js/admin-modal-ver-alunos.js"></script>

</body>
</html>