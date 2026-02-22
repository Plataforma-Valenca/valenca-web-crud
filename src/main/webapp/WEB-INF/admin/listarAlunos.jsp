<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Aluno" %>

<html>
<head>
    <title>Buscar Aluno(a) - Colégio Barão</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
</head>
<body>

<jsp:include page="/WEB-INF/views/componentes/sidebarAdm.jsp">
    <jsp:param name="activePage" value="listarAluno"/>
</jsp:include>

<div class="main-content">

    <h1>Buscar Aluno(a)</h1>

    <!-- FORM DE BUSCA -->
    <form method="get" action="${pageContext.request.contextPath}/alunos">
        <input type="text" name="cpf" placeholder="CPF do aluno">
        <button type="submit">Buscar</button>
    </form>

    <br><br>

    <a href="${pageContext.request.contextPath}/alunos?acao=cadastrar">
        <button>+ Cadastrar</button>
    </a>

    <br><br>

    <!-- TABELA -->
    <table border="1" width="100%">
        <tr>
            <th>Nome</th>
            <th>CPF</th>
            <th>Matrícula</th>
            <th>Turma</th>
        </tr>

        <%
            List<Aluno> lista = (List<Aluno>) request.getAttribute("listaAlunos");

            if(lista != null){
                for(Aluno a : lista){
        %>
        <tr>
            <td><%= a.getNome() %></td>
            <td><%= a.getCpf() %></td>
            <td><%= a.getMatricula() %></td>
            <td><%= a.getTurma() %></td>
        </tr>
        <%
                }
            }
        %>

    </table>

</div>

</body>
</html>