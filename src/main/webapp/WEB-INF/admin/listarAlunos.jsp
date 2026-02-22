<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.projetodiogo.model.AlunoConsultaDTO" %>

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
    <form method="get" action="${pageContext.request.contextPath}/admin/VerAlunos">
        <input type="text" name="busca" placeholder="Matrícula do aluno">
        <button type="submit">Buscar</button>
    </form>

    <br><br>

    <a href="${pageContext.request.contextPath}/admin/cadastrarAluno">
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
            List<AlunoConsultaDTO> lista = (List<AlunoConsultaDTO>) request.getAttribute("alunosList");

            if(lista != null){
                for(AlunoConsultaDTO a : lista){
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