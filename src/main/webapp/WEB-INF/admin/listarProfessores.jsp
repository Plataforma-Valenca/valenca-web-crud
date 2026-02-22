<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="model.ProfessorResumo" %>

<html>
<head>
    <title>Listar Professores - Colégio Barão</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/css/style.css">
</head>

<body>

<!-- SIDEBAR -->
<jsp:include page="/WEB-INF/views/componentes/sidebar.jsp">
    <jsp:param name="activePage" value="listarProfessor"/>
</jsp:include>

<div class="main-content">

    <h1>Professores</h1>

    <table class="tabela-listagem">

        <thead>
        <tr>
            <th>Nome</th>
            <th>Email</th>
            <th>Telefone</th>
        </tr>
        </thead>

        <tbody>
        <%
            List<ProfessorResumo> lista =
                    (List<ProfessorResumo>) request.getAttribute("listaProfessores");

            if (lista != null && !lista.isEmpty()) {
                for (ProfessorResumo p : lista) {
        %>
        <tr>
            <td><%= p.getNome() %></td>
            <td><%= p.getEmail() %></td>
            <td><%= p.getTelefone() %></td>
        </tr>
        <%
                }
            } else {
        %>
        <tr>
            <td colspan="3" style="text-align:center;">
                Nenhum professor encontrado.
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