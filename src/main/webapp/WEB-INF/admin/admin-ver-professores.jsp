<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.example.projetodiogo.model.DTO.ProfessorConsultaDTO" %>
<%@ page import="org.example.projetodiogo.model.Disciplina" %>
<%@ page import="java.util.List" %>

<%
    List<ProfessorConsultaDTO> professoresList = (List<ProfessorConsultaDTO>) request.getAttribute("professoresList");
    List<Disciplina> disciplinas = (List<Disciplina>) request.getAttribute("disciplinas");
    String busca = request.getAttribute("busca") != null ? request.getAttribute("busca").toString() : "";
%>

<html>
<head>
    <title>Colégio Valença</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/modal.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/page-grid.css">
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/assets/img/icone-colegio-valenca.svg">
</head>

<body>
<jsp:include page="/WEB-INF/views/componentes/flash-message.jsp"/>

<jsp:include page="/WEB-INF/views/componentes/admin-sidebar.jsp">
    <jsp:param name="activePage" value="busca"/>
</jsp:include>

<div class="page-content">

    <header class="page-grid-header" style="
    display: flex;
    justify-content: space-between;
    flex-direction: row;
    gap: 3vh;
    align-items: flex-end;
    height: 20vh;
    margin-top: 5rem;
    margin-bottom: 2rem">
        <h1 class="page-grid-header-title">Gerenciar Professores</h1>

        <button class="btn-primary" onclick="abrirModalCadastro()">
            + Cadastrar
        </button>
    </header>

    <main class="page-grid-main" style="gap: 5vh; padding-bottom: 80px;">

        <div class="top-box-page-grid-main">
            <form action="${pageContext.request.contextPath}/admin/verProfessores" method="get" class="form-busca">

                <div class="form-control">
                    <h5>Buscar professor</h5>

                    <div class="form-control-action input-primary">
                        <input type="text" name="busca" placeholder="Nome" value="<%= busca %>">
                        <button type="submit" class="btn btn-primary">Buscar</button>
                    </div>

                </div>

            </form>
        </div>

        <div class="bottom-box-page-grid-main">
            <div class="page-grid-main-content">

                <div class="table-list-row">

                    <div class="header-table-list-row" style="display:flex">
                        <h4 style="flex:2;">Nome</h4>
                        <h4 style="flex:2;">Email</h4>
                        <h4 style="flex:1.5;">CPF</h4>
                        <h4 style="flex:1.5;">Disciplina</h4>
                        <h4 style="flex:0.5;"></h4>
                    </div>

                    <div class="body-table-list-row">

                        <%
                            if (professoresList != null && !professoresList.isEmpty()) {
                                for (ProfessorConsultaDTO p : professoresList) {
                        %>

                        <div class="itens-per-table" style="display:flex; align-items:center">

                            <div style="flex:2;"><%= p.getNome() %></div>
                            <div style="flex:2;"><%= p.getEmail() %></div>
                            <div style="flex:1.5;"><%= p.getCpf() %></div>
                            <div style="flex:1.5;"><%= p.getDisciplina() %></div>

                            <i class="fa-solid fa-trash"
                               style="color: var(--color-error); cursor:pointer;"
                               onclick="abrirModalExcluir('<%= p.getIdProfessor() %>')">
                            </i>

                        </div>

                        <%
                            }
                        } else {
                        %>

                        <div style="text-align:center; padding:40px; color:#999;">
                            Nenhum professor encontrado.
                        </div>

                        <% } %>

                    </div>
                </div>

            </div>
        </div>

    </main>

</div>

<jsp:include page="/WEB-INF/views/modais/admin-modal-professor.jsp"/>

<script src="${pageContext.request.contextPath}/assets/js/admin-modal-professor.js"></script>

</body>
</html>