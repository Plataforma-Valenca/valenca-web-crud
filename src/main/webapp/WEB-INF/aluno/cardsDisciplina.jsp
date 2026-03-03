<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.projetodiogo.model.Boletim" %>
<%@ page import="org.example.projetodiogo.model.Disciplina" %>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home - Aluno</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/aluno.css">
</head>
<body>

<jsp:include page="/WEB-INF/views/componentes/sidebarAluno.jsp"/>

<h1 id="home-title">Olá, Aluno, o que você procura?</h1>

<div class="cards-grid">
    <%
        String nomeProfessor = "";
        if (request.getAttribute("nomeProfessor") != null) {
            nomeProfessor = (String) request.getAttribute("nomeProfessor");
        }

        List<Disciplina> disciplinaList = (List<Disciplina>) request.getAttribute("disciplinaList");

        if (disciplinaList != null && !disciplinaList.isEmpty()) {
            for (Disciplina d : disciplinaList ) {
    %>
    <a class="card" href="${pageContext.request.contextPath}/aluno/notasPorDisciplina?idDisciplina=<%= d.getId() %>">
        <div class="card-body">
            <div class="card-titulo"><%= d.getNomeFormatado() %></div>
            <div class="card-subtitulo"><%= request.getAttribute("nomeDisciplina") %></div>
        </div>
        <div class="card-footer">
            <img src="${pageContext.request.contextPath}/assets/img/icon-card.svg" alt="Entrar na disciplina">
        </div>
    </a>
    <%
        }
    } else {
    %>
    <div class="empty">Nenhuma disciplina encontrada.</div>
    <%
        }
    %>
</div>

</body>
</html>