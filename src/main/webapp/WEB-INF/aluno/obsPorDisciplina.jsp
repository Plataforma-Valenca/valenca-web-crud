<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.example.projetodiogo.model.Observacao" %>
<%@ page import="java.text.SimpleDateFormat" %>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Observações</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/aluno.css">
</head>
<body>

<jsp:include page="/WEB-INF/views/componentes/sidebarAluno.jsp"/>

<%
    ArrayList<Observacao> obsList = (ArrayList<Observacao>) request.getAttribute("obsList");
    String nomeDisciplina = request.getAttribute("nomeDisciplina") != null
            ? (String) request.getAttribute("nomeDisciplina") : "";
    String nomeProfessor = (String) request.getAttribute("nomeProfessor");
    int idDisciplina = request.getAttribute("idDisciplina") != null
            ? (int) request.getAttribute("idDisciplina") : 0;
%>

<div class="header">
    <a href="javascript:history.back()" class="btn-voltar">&#x2BA8;</a>
    <div class="header-info">
        <div class="disciplina-nome"><%= nomeDisciplina %></div>
        <div class="professor-nome">Prof. <%= nomeProfessor %></div>
    </div>
</div>

<div class="secoes">
    <a href="${pageContext.request.contextPath}/aluno/notasPorDisciplina?idDisciplina=<%= idDisciplina %>"
       class="secao-link inativo">Avaliações</a>
    <span class="secao-link ativo">Observações</span>
</div>

<div class="tabela-wrapper">
    <table>
        <thead>
        <tr>
            <th>Data</th>
            <th>Observação</th>
        </tr>
        </thead>
        <tbody>
        <%
            if (obsList != null && !obsList.isEmpty()) {
                for (Observacao obs : obsList) {
        %>
        <tr>
            <td><%= new SimpleDateFormat("dd/MM/yyyy").format(obs.getDataEnvio()) %></td>
            <td><%= obs.getDescricao() %></td>
        </tr>
        <%
            }
        } else {
        %>
        <tr>
            <td colspan="2" class="nota-vazia">Nenhuma observação encontrada.</td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
</div>

</body>
</html>