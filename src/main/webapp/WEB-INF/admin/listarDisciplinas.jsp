<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.projetodiogo.model.DTO.DisciplinasResumoDTO" %>

<html>
<head>
    <title>Colégio Valença</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/page-grid.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/list-card.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">

    <link rel="icon" type="image/x-icon"
          href="${pageContext.request.contextPath}/assets/img/icone-colegio-valenca.svg">
</head>

<body>

<jsp:include page="/WEB-INF/views/componentes/sidebarAdm.jsp">
    <jsp:param name="activePage" value="disciplinas" />
</jsp:include>

<div class="page-content">

    <header class="page-grid-header" style="flex-direction: row; justify-content: space-between; align-items: flex-end;">
        <div>
            <h1 class="page-grid-header-title">Disciplinas</h1>
        </div>
    </header>

    <main class="page-grid-main">
        <div class="page-grid-main-content card-grid">
            <%
                List<DisciplinasResumoDTO> disciplinasList = (List<DisciplinasResumoDTO>) request.getAttribute("resumoList");

                if (disciplinasList != null && !disciplinasList.isEmpty()) {
                    for (DisciplinasResumoDTO d : disciplinasList) {
            %>
            <div class="card-items">
                <div class="card-items-infos">
                    <h4><%= d.getNomeFormatado() %></h4>
                    <p style="font-weight: 400; color: #535353">
                        <%= d.getNomeProfessor() != null ? "Prof. " + d.getNomeProfessor() : "Sem professor" %>
                    </p>
                </div>
            </div>


            <%
                }
            } else {
            %>
            <p class="no-data" style="grid-column: span 3; text-align: center; color: #999; margin-top: 20px;">
                Nenhuma disciplina cadastrada.
            </p>
            <%
                }
            %>
        </div>
    </main>

</div>



</body>
</html>