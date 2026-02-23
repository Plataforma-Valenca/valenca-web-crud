<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="model.ProfessorResumo" %>

<html>
<head>
    <title>Listar Professores - Colégio Barão</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
</head>

<body>

<!-- SIDEBAR -->
<jsp:include page="/WEB-INF/views/componentes/sidebar.jsp">
    <jsp:param name="activePage" value="listarProfessor"/>
</jsp:include>

<div class="main-content">

    <h1>Professores</h1>

    <!-- FORM DE BUSCA -->
    <form method="get" action="${pageContext.request.contextPath}/professores">
        <input type="text" name="busca" placeholder="Buscar por nome ou email"
               value="<%= request.getAttribute("busca") != null ? request.getAttribute("busca") : "" %>">
        <button type="submit">Digite o email do professor</button>
    </form>

    <br>

    <!-- FORM PARA AÇÃO NA TABELA -->
    <form method="post" action="${pageContext.request.contextPath}/professores/acao">
        <table class="tabela-listagem">
            <thead>
                <tr>
                    <th>Selecionar</th>
                    <th>Nome</th>
                    <th>Email</th>
                    <th>Telefone</th>
                </tr>
            </thead>
            <tbody>
            <%
                List<ProfessorResumo> lista = (List<ProfessorResumo>) request.getAttribute("listaProfessores");

                if (lista != null && !lista.isEmpty()) {
                    for (ProfessorResumo p : lista) {
            %>
            <tr>
                <td>
                    <input type="checkbox" name="professorId" value="<%= p.getId() %>">
                </td>
                <td><%= p.getNome() %></td>
                <td><%= p.getEmail() %></td>
                <td><%= p.getTelefone() %></td>
            </tr>
            <%
                    }
                } else {
            %>
            <tr>
                <td colspan="4" style="text-align:center;">Nenhum professor encontrado.</td>
            </tr>
            <%
                }
            %>
            </tbody>
        </table>

        <br>
        <button type="submit" name="acao" value="excluir">Excluir selecionados</button>
        <button type="submit" name="acao" value="editar">Editar selecionados</button>
    </form>

</div>

</body>
</html>