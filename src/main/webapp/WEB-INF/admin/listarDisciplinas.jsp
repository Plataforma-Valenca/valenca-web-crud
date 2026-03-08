<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.projetodiogo.model.DTO.DisciplinasResumoDTO" %>

<html>
<head>
    <title>Colégio Valença - Administração</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/page-grid.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/list-card.css">
    <%-- FontAwesome para ícones de edição e lixeira --%>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">

    <link rel="icon" type="image/x-icon"
          href="${pageContext.request.contextPath}/assets/img/icone-colegio-valenca.svg">
</head>

<body>

<jsp:include page="/WEB-INF/views/componentes/sidebarAdm.jsp">
    <jsp:param name="activePage" value="disciplinas" />
</jsp:include>

<div class="page-content">

    <header class="page-grid-header" style="display: flex; justify-content: space-between; align-items: center;">
        <h1 class="page-grid-header-title">Disciplinas</h1>
        <button class="btn-primary" style="width: 150px;">
            <i class="fa-solid fa-plus"></i> Cadastrar
        </button>
    </header>

    <main class="page-grid-main">
        <div class="page-grid-main-content card-grid">
            <%
                List<DisciplinasResumoDTO> disciplinasList = (List<DisciplinasResumoDTO>) request.getAttribute("resumoList");

                if (disciplinasList != null && !disciplinasList.isEmpty()) {
                    for (DisciplinasResumoDTO d : disciplinasList) {
            %>
            <div class="card-items" style="display: flex; justify-content: space-between; align-items: center;">
                <div class="card-info">
                    <strong style="display: block; font-size: 1.1rem;"><%= d.getNomeFormatado() %></strong>
                    <p style="font-size: 0.85rem; color: #666; margin: 4px 0;">
                        Prof. <%= d.getNomeProfessor() != null ? d.getNomeProfessor() : "Não atribuído" %>
                    </p>
                    <p style="font-size: 0.75rem; color: #999;">
                        Turmas: <%= d.getQuantidadeTurmas() %>
                    </p>
                </div>

                <div class="card-actions" style="display: flex; gap: 15px; color: #535353;">
                    <a href="#" title="Editar" style="color: inherit;"><i class="fa-solid fa-pen"></i></a>
                    <a href="#" title="Excluir" style="color: #E74C3C;"><i class="fa-solid fa-trash"></i></a>
                </div>
            </div>
            <%
                }
            } else {
            %>
            <p class="no-data">Nenhuma disciplina cadastrada.</p>
            <%
                }
            %>
        </div>
    </main>

</div>

</body>
</html>