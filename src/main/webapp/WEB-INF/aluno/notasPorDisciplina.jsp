<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.projetodiogo.model.Boletim" %>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Notas - <%= request.getAttribute("nomeDisciplina") %></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/aluno.css">
</head>
<body>

<jsp:include page="/WEB-INF/views/componentes/sidebarAluno.jsp"/>

<div class="header">
    <a href="javascript:history.back()" class="btn-voltar">
        <img src="${pageContext.request.contextPath}/assets/img/icon-logout-subject.svg" alt="Sair da disciplina">
    </a>
    <div class="header-info">
        <div class="disciplina-nome"><%= request.getAttribute("nomeDisciplina") %></div>
        <%
            int idDisciplina = (int) request.getAttribute("idDisciplina");
            List<Boletim> boletimList = (List<Boletim>) request.getAttribute("boletimList");
            String nomeProfessor = "";
            if (request.getAttribute("nomeProfessor") != null) {
                nomeProfessor = (String) request.getAttribute("nomeProfessor");
            }
        %>
        <div class="professor-nome">Prof. <%= nomeProfessor %></div>
    </div>
</div>

<div class="secoes">
    <span class="secao-titulo">Avaliações</span>
    <a class="secao-titulo inativo"
       href="${pageContext.request.contextPath}/aluno/verObservacoes?idDisciplina=<%= idDisciplina %>">
        Observações
    </a>
</div>

<div class="tabela-wrapper">
    <table>
        <thead>
        <tr>
            <th>Disciplinas</th>
            <th>Média 1° SEM</th>
            <th>Média 2° SEM</th>
            <th>Média Final</th>
        </tr>
        </thead>
        <tbody>
        <%
            if (boletimList != null && !boletimList.isEmpty()) {
                for (Boletim b : boletimList) {

                    String n1 = b.getMedia1() != null ? String.valueOf(b.getMedia1()) : "--";
                    String n2 = b.getMedia2() != null ? String.valueOf(b.getMedia2()) : "--";
                    String mf = b.getMediaFinal() != null ? String.valueOf(b.getMediaFinal()) : "--";
        %>
        <tr>
            <td><%= request.getAttribute("nomeDisciplina") %></td>
            <td class="<%= !n2.equals("--") ? "nota-vermelho" : "nota-vazia" %>"><%= n1 %></td>
            <td class="<%= !n1.equals("--") ? "nota-media" : "nota-vazia" %>"><%= n2 %></td>
            <td class="<%= !mf.equals("--") ? "nota-azul" : "nota-vazia" %>"><%= mf %></td>
        </tr>
        <%
            }
        } else {
        %>
        <tr>
            <td colspan="4" style="text-align:center; color:#aaa; padding:32px;">
                Nenhuma nota encontrada.
            </td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
</div>

</body>
</html>