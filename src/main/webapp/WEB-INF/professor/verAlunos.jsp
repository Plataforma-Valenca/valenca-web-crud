<%--
  Created by IntelliJ IDEA.
  User: emanuellymeso-ieg
  Date: 03/03/2026
  Time: 07:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.example.projetodiogo.model.AlunoConsultaDTO" %>
<%@ page import="java.util.List" %>

<%
    List<AlunoConsultaDTO> alunosList =
            (List<AlunoConsultaDTO>) request.getAttribute("alunosList");

    String busca = request.getAttribute("busca") != null
            ? request.getAttribute("busca").toString()
            : "";
%>

<!DOCTYPE html>
<html>
<head>
    <title>Alunos</title>

    <style>
        * { margin:0; padding:0; box-sizing:border-box; }

        body {
            font-family: 'Segoe UI', Arial;
            background:#f1f1f1;
            margin-left:22vw;
            width:78vw;
            padding:60px 40px;
        }

        h1 {
            font-size:26px;
            margin-bottom:25px;
        }

        .busca {
            display:flex;
            gap:10px;
            margin-bottom:30px;
        }

        .busca input {
            padding:8px 12px;
            border-radius:6px;
            border:1px solid #ccc;
            width:220px;
        }

        .btn {
            background:#3d6f66;
            color:white;
            border:none;
            padding:8px 14px;
            border-radius:6px;
            cursor:pointer;
        }

        .btn:hover {
            background:#2f5d59;
        }

        .table-header {
            display:grid;
            grid-template-columns: 2fr 1fr 1fr 2fr;
            padding:10px;
            color:#777;
            font-size:13px;
            font-weight:600;
        }

        .table-row {
            display:grid;
            grid-template-columns: 2fr 1fr 1fr 2fr;
            background:white;
            padding:18px;
            border-radius:10px;
            margin-bottom:12px;
            box-shadow:0 3px 10px rgba(0,0,0,0.05);
            text-decoration:none;
            color:black;
            transition:0.2s;
        }

        .table-row:hover {
            transform: translateY(-3px);
            box-shadow:0 6px 18px rgba(0,0,0,0.08);
            cursor:pointer;
        }

        .empty {
            text-align:center;
            margin-top:30px;
            color:#888;
        }
    </style>
</head>

<body>

<jsp:include page="/WEB-INF/views/componentes/sidebarProfessor.jsp"/>

<!-- BUSCA -->
<form method="get"
      action="<%= request.getContextPath() %>/professor/verAlunos"
      class="busca">

    <input type="text"
           name="busca"
           placeholder="Buscar por matrícula"
           value="<%= busca %>">

    <button class="btn">Buscar</button>
</form>

<div class="table-header">
    <span>Nome</span>
    <span>Matrícula</span>
    <span>CPF</span>
    <span>Turma</span>
</div>

<%
    if (alunosList != null && !alunosList.isEmpty()) {

        for (AlunoConsultaDTO aluno : alunosList) {
%>

<a class="table-row"
   href="<%= request.getContextPath() %>/professor/verPerfilAluno?idAluno=<%= aluno.getIdAluno() %>">

    <span><%= aluno.getNome() %></span>
    <span><%= aluno.getMatricula() %></span>
    <span><%= aluno.getCpf() %></span>
    <span><%= aluno.getTurma() %></span>

</a>

<%
    }

} else {
%>

<div class="empty">
    Nenhum aluno encontrado.
</div>

<%
    }
%>

</body>
</html>