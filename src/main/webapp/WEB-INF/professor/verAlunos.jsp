<%--
  Created by IntelliJ IDEA.
  User: emanuellymeso-ieg
  Date: 03/03/2026
  Time: 07:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.example.projetodiogo.model.DTO.AlunoConsultaDTO" %>
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


</head>



<jsp:include page="/WEB-INF/views/componentes/sidebarProfessor.jsp">
    <jsp:param name="activePage" value="professor"/>
</jsp:include>


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