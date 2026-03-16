<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.projetodiogo.model.Turma" %>

<html>
<head>
    <title>Colégio Valença</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/admin.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/page-grid.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/list-card.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/assets/img/icone-colegio-valenca.svg">
</head>

<body>

<jsp:include page="/WEB-INF/views/componentes/flash-message.jsp"/>

<jsp:include page="/WEB-INF/views/componentes/admin-sidebar.jsp">
    <jsp:param name="activePage" value="turmas"/>
</jsp:include>

<div class="page-content">

    <header class="page-grid-header" style="margin-top: 3rem; margin-bottom: 0rem">
        <h1 class="page-grid-header-title">Gerenciar Turmas</h1>
        <button class="btn-primary" onclick="abrirModalCadastro()">+ Cadastrar</button>
    </header>

    <main class="page-grid-main">
        <div class="page-grid-main-content card-grid">

            <%
                List<Turma> turmas = (List<Turma>) request.getAttribute("turmas");

                if (turmas != null && !turmas.isEmpty()) {
                    for (Turma t : turmas) {
            %>

            <a class="card-items"
               href="${pageContext.request.contextPath}/admin/verAlunos?idTurma=<%= t.getId() %>&nomeTurma=<%= t.getNome() %>">

                <span class="card-title">
                    <%= t.getNome() %>
                </span>

                <div class="card-actions">

                    <i class="fa-solid fa-pen"
                       onclick="abrirModalEditar('<%=t.getId()%>','<%=t.getAno()%>','<%=t.getNome().replace("'", "\\'")%>')">
                    </i>

                    <i class="fa-solid fa-trash"
                       style="color: var(--color-error);"
                       onclick="abrirModalExcluir('<%=t.getId()%>')">
                    </i>

                </div>

            </a>

            <%
                }
            } else {
            %>
            <img src="${pageContext.request.contextPath}/assets/img/search-not-found.svg" height="200">
            <p class="no-data">Nenhuma turma cadastrada no sistema.</p>

            <%
                }
            %>

        </div>
    </main>
</div>

<jsp:include page="/WEB-INF/views/modais/admin-modal-turmas.jsp"/>

<script src="${pageContext.request.contextPath}/assets/js/admin-modal-turmas.js"></script>

</body>
</html>