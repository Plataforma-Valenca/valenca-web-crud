<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="model.DisciplinaResumo" %>

<html>
<head>
    <title>Listar Disciplinas</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/css/style.css">
</head>

<body>

<jsp:include page="/WEB-INF/views/componentes/sidebar.jsp">
    <jsp:param name="activePage" value="listarDisciplina"/>
</jsp:include>

<div class="main-content">

    <h1>Disciplinas</h1>

    <table class="tabela-listagem">

        <thead>
        <tr>
            <th>Disciplina</th>
            <th>Professor</th>
            <th>Qtd. Turmas</th>
            <th>Média Geral</th>
        </tr>
        </thead>

        <tbody>
        <%
            List<DisciplinaResumo> lista =
                    (List<DisciplinaResumo>) request.getAttribute("listaDisciplinas");

            if (lista != null && !lista.isEmpty()) {
                for (DisciplinaResumo d : lista) {
        %>
        <tr>
            <td><%= d.getNome() %></td>
            <td><%= d.getProfessor() %></td>
            <td><%= d.getQuantidadeTurmas() %></td>

           <td style ="<%= d.getMediaGeral() < 6 ? 'color:red;' : 'color:green;' %>">
    <%= d.getMediaGeral() %>
</td>
        </tr>
        <%
                }
            } else {
        %>
        <tr>
            <td colspan="4" style="text-align:center;">
                Nenhuma disciplina encontrada.
            </td>
        </tr>
        <%
            }
        %>
        </tbody>

    </table>

</div>

</body>
</html>