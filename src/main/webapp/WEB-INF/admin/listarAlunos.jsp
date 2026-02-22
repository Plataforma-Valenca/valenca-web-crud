<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.projetodiogo.model.AlunoConsultaDTO" %>

<html>
<head>
    <title>Listar Alunos - Admin</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
</head>
<body>

<jsp:include page="/WEB-INF/admin/sidebar.jsp" />

<div class="main-content">
    <h1>Listar Alunos</h1>

    <!-- FORM DE BUSCA -->
    <form method="get" action="${pageContext.request.contextPath}/admin/verAlunos">
        <input type="text" name="busca" placeholder="Matrícula do aluno"
               value="<%= req.getAttribute("busca") != null ? req.getAttribute("busca") : "" %>">
        <button type="submit">Buscar</button>
    </form>

    <br><br>

    <!-- FORM PARA AÇÃO NA TABELA -->
    <form method="post" action="${pageContext.request.contextPath}/admin/alunos/acao">
        <table border="1" width="100%">
            <tr>
                <th>Selecionar</th>
                <th>Nome</th>
                <th>CPF</th>
                <th>Matrícula</th>
                <th>Turma</th>
            </tr>

            <%
                List<AlunoConsultaDTO> alunosList = (List<AlunoConsultaDTO>) req.getAttribute("alunosList");
                if (alunosList != null && !alunosList.isEmpty()) {
                    for (AlunoConsultaDTO aluno : alunosList) {
            %>
            <tr>
                <td>
                    <input type="checkbox" name="alunoId" value="<%= aluno.getId() %>">
                </td>
                <td><%= aluno.getNome() %></td>
                <td><%= aluno.getCpf() %></td>
                <td><%= aluno.getMatricula() %></td>
                <td><%= aluno.getTurma() %></td>
            </tr>
            <%
                    }
                } else {
            %>
            <tr>
                <td colspan="5" style="text-align:center;">Nenhum aluno encontrado.</td>
            </tr>
            <%
                }
            %>
        </table>

        <br>
        <button type="submit" name="acao" value="excluir">Excluir selecionados</button>
        <button type="submit" name="acao" value="editar">Editar selecionados</button>
    </form>

</div>

</body>
</html>