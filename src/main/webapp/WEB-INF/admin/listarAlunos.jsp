<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.projetodiogo.model.DTO.AlunoConsultaDTO" %>

<html>
<head>
    <title>Listar Alunos - Admin</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
</head>
<body>

<jsp:include page="/WEB-INF/views/componentes/sidebarAdm.jsp" />

<div class="main-content">

<%--    <h1>Listar Alunos</h1>--%>

<%--    <!-- FORM DE BUSCA -->--%>
<%--    <form action="${pageContext.request.contextPath}/admin/verAlunos"--%>
<%--          method="get"--%>
<%--          class="form-busca">--%>

<%--        <input type="text"--%>
<%--               name="busca"--%>
<%--               placeholder="Matrícula do aluno"--%>
<%--               value="<%= request.getAttribute("busca") != null ? request.getAttribute("busca") : "" %>">--%>

<%--        <button type="submit" class="btn btn-primary">Buscar</button>--%>
<%--    </form>--%>

    <!-- Substitua o h1 + form-busca por isso: -->
    <h1>Buscar Aluno(a)</h1>

    <div class="top-bar">
        <form action="${pageContext.request.contextPath}/admin/verAlunos" method="get" class="form-busca">
            <input type="text" name="busca" placeholder="Matrícula do aluno">
            <button type="submit" class="btn btn-primary">Buscar</button>
        </form>
        <a href="/admin/cadastrarAluno" class="btn btn-cadastrar">+ Cadastrar</a>
    </div>

    <!-- FORM PARA AÇÕES NA TABELA -->
    <form method="get"
          action="${pageContext.request.contextPath}/admin/verAlunos">

        <table class="tabela-listagem">

            <thead>
            <tr>
                <th>Selecionar</th>
                <th>Nome</th>
                <th>CPF</th>
                <th>Matrícula</th>
                <th>Turma</th>
            </tr>
            </thead>

            <tbody>
            <%
                List<AlunoConsultaDTO> alunosList =
                        (List<AlunoConsultaDTO>) request.getAttribute("alunosList");

                if (alunosList != null && !alunosList.isEmpty()) {
                    for (AlunoConsultaDTO aluno : alunosList) {
            %>
            <tr>
                <td>
                    <input type="checkbox"
                           name="alunoId"
                           value="<%= aluno.getMatricula() %>">
                </td>
                <td><%= aluno.getNome() %></td>
                <td><%= aluno.getCpf() %></td>
                <td><%= aluno.getMatricula() %></td>
                <td><%= aluno.getTurma() %></td>
                <td><a><img src="${pageContext.request.contextPath}/assets/img/editBtn.svg"></a></td>
            </tr>
            <%
                }
            } else {
            %>
            <tr>
                <td colspan="5" style="text-align:center;">
                    Nenhum aluno encontrado.
                </td>
            </tr>
            <%
                }
            %>
            </tbody>

        </table>

        <br>

        <button type="submit"
                name="acao"
                value="excluir"
                class="btn btn-danger">
            Excluir selecionados
        </button>

        <button type="submit"
                name="acao"
                value="editar"
                class="btn btn-primary">
            Editar selecionados
        </button>

        <div class="acoes-tabela">
            <button class="btn btn-danger">Excluir selecionados</button>
            <button class="btn btn-primary">Editar selecionados</button>
        </div>

    </form>

</div>

</body>
</html>