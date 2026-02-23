<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.projetodiogo.model.Disciplina" %>

<html>
<head>
    <title>Visualizar Disciplinas</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
</head>

<body>

<jsp:include page="/WEB-INF/views/componentes/sidebar.jsp">
    <jsp:param name="activePage" value="listarDisciplina"/>
</jsp:include>

<div class="main-content">

    <h1>Disciplinas</h1>

    <!-- FORM (GET porque o servlet só tem doGet) -->
    <form method="get" action="${pageContext.request.contextPath}/VerDisciplinas">

        <table class="tabela-listagem">
            <thead>
            <tr>
                <th>Selecionar</th>
                <th>Disciplina</th>
                <th>Professor</th>
            </tr>
            </thead>

            <tbody>
            <%
                List<Disciplina> lista =
                        (List<Disciplina>) request.getAttribute("disciplinasList");

                if (lista != null && !lista.isEmpty()) {
                    for (Disciplina d : lista) {
            %>
            <tr>
                <td>
                    <input type="checkbox" name="disciplinaId" value="<%= d.getId() %>">
                </td>
                <td><%= d.getNome() %></td>
                <td><%= d.getProfessor() != null ? d.getProfessor().getNome() : "-" %></td>
            </tr>
            <%
                }
            } else {
            %>
            <tr>
                <td colspan="3" style="text-align:center;">
                    Nenhuma disciplina encontrada.
                </td>
            </tr>
            <%
                }
            %>
            </tbody>
        </table>

        <br>
        <button type="submit">Atualizar Lista</button>

    </form>

</div>

</body>
</html>