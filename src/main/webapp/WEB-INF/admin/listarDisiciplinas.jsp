<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="model.DisciplinaResumo" %>

<html>
<head>
    <title>Listar Disciplinas</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
</head>

<body>

<jsp:include page="/WEB-INF/views/componentes/sidebar.jsp">
    <jsp:param name="activePage" value="listarDisciplina"/>
</jsp:include>

<div class="main-content">

    <h1>Disciplinas</h1>

    <!-- FORM DE BUSCA -->
    <form method="get" action="${pageContext.request.contextPath}/disciplinas">
        <input type="text" name="busca" placeholder="Buscar por disciplina ou professor"
               value="<%= request.getAttribute("busca") != null ? request.getAttribute("busca") : "" %>">
        <button type="submit">Buscar</button>
    </form>

    <br>

    <!-- FORM PARA A TABELA -->
    <form method="post" action="${pageContext.request.contextPath}/disciplinas/acao">
        <table class="tabela-listagem">
            <thead>
                <tr>
                    <th>Selecionar</th>
                    <th>Disciplina</th>
                    <th>Professor</th>
                    <th>Qtd. Turmas</th>
                    <th>Média Geral</th>
                </tr>
            </thead>
            <tbody>
            <%
                List<DisciplinaResumo> lista = (List<DisciplinaResumo>) request.getAttribute("listaDisciplinas");

                if (lista != null && !lista.isEmpty()) {
                    for (DisciplinaResumo d : lista) {
            %>
            <tr>
                <td>
                    <!-- input para enviar o ID da disciplina se quiser fazer ação -->
                    <input type="checkbox" name="disciplinaId" value="<%= d.getId() %>">
                </td>
                <td><%= d.getNome() %></td>
                <td><%= d.getProfessor() %></td>
                <td><%= d.getQuantidadeTurmas() %></td>
                <td style="<%= d.getMediaGeral() < 6 ? "color:red;" : "color:green;" %>">
                    <%= d.getMediaGeral() %>
                </td>
            </tr>
            <%
                    }
                } else {
            %>
            <tr>
                <td colspan="5" style="text-align:center;">
                    Nenhuma disciplina encontrada.
                </td>
            </tr>
            <%
                }
            %>
            </tbody>
        </table>

        <br>
        <button type="submit" name="acao" value="excluir">Excluir selecionadas</button>
        <button type="submit" name="acao" value="editar">Editar selecionadas</button>
    </form>

</div>

</body>
</html>