<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.projetodiogo.model.DTO.AlunoConsultaDTO" %>

<%
    List<AlunoConsultaDTO> alunos =
            (List<AlunoConsultaDTO>) request.getAttribute("alunos");
%>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Alunos da Turma</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/css/tabelaSistema.css">

    <style>
        table{
            width:100%;
            border-collapse: collapse;
            margin-top:20px;
        }

        th, td{
            padding:10px;
            border-bottom:1px solid #ddd;
            text-align:left;
        }

        th{
            background:#f5f5f5;
        }
    </style>
</head>

<body>

<jsp:include page="/WEB-INF/views/componentes/sidebarAdm.jsp"/>

<div class="page-container">

    <h1>Alunos da Turma</h1>

    <%
        if(alunos != null && !alunos.isEmpty()){
    %>

    <table>

        <thead>
        <tr>
            <th>Nome</th>
            <th>Matrícula</th>
            <th>CPF</th>
            <th>Turma</th>
        </tr>
        </thead>

        <tbody>

        <%
            for(AlunoConsultaDTO a : alunos){
        %>

        <tr>
            <td><a href="${pageContext.request.contextPath}/admin/verPerfilAluno?cpf=<%= a.getCpf() %>">
            <%=a.getNome()%></td>
            <td><%= a.getMatricula() %></td>
            <td><%= a.getCpf() %></td>
            <td><%= a.getTurma() %></td>
        </tr>

        <%
            }
        %>

        </tbody>

    </table>

    <%
    } else {
    %>

    <p>Nenhum aluno encontrado.</p>

    <%
        }
    %>

</div>

</body>
</html>