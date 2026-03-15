<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.projetodiogo.model.DTO.DisciplinasResumoDTO" %>

<html>
<head>
    <title>Colégio Valença</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/page-grid.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/disciplinas.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/assets/img/icone-colegio-valenca.svg">
</head>

<body>

<jsp:include page="/WEB-INF/views/componentes/flash-message.jsp"/>

<jsp:include page="/WEB-INF/views/componentes/admin-sidebar.jsp">
    <jsp:param name="activePage" value="resumoList" />
</jsp:include>

<div class="page-content">

    <header class="page-grid-header" style="display: flex;
    gap: 3vh;
    align-items: flex-end;
    justify-content: space-between;
    flex-direction: row;
    height: 20vh;">
        <h2 class="page-grid-header-title" style="font-size: 2rem; color: #0f2c3d;">Gerenciar disciplinas</h2>
        <button class="btn-primary" onclick="abrirModalCadastro()">+ Cadastrar</button>
    </header>

    <main class="page-grid-main">
        <div class="card-grid">
            <%
                List<DisciplinasResumoDTO> disciplinasList = (List<DisciplinasResumoDTO>) request.getAttribute("resumoList");
                if (disciplinasList != null && !disciplinasList.isEmpty()) {
                    for (DisciplinasResumoDTO d : disciplinasList) {
            %>
            <div class="card-items">
                <div class="card-items-infos">
                    <h4><%= d.getNomeFormatado() %></h4>
                    <p><%= d.getNomeProfessor() != null ? "Prof. " + d.getNomeProfessor() : "Sem professor" %></p>
                </div>

                <div class="card-actions">

                    <i class="fa-solid fa-pen" style="cursor: pointer"
                       onclick="abrirModalEditar(
                               '<%=d.getIdDisciplina()%>',
                               '<%=d.getNomeFormatado()%>',
                               '<%=d.getIdProfessor()%>',
                               '<%=d.getNomeProfessor() != null ? d.getNomeProfessor() : ""%>'
                               )">
                    </i>

                    <i class="fa-solid fa-trash"
                       style="color: var(--color-error); cursor: pointer"
                       onclick="abrirModalExcluir('<%=d.getIdDisciplina()%>')">
                    </i>

                </div>

            </div>
            <%
                }
            } else {
            %>
            <p class="no-data" style="grid-column: span 3; text-align: center; color: #999;">
                Nenhuma disciplina cadastrada.
            </p>
            <% } %>
        </div>
    </main>
</div>

<jsp:include page="/WEB-INF/views/modais/admin-modal-disciplinas.jsp"/>

<script src="${pageContext.request.contextPath}/assets/js/admin-modal-disciplinas.js"></script>

</body>
</html>