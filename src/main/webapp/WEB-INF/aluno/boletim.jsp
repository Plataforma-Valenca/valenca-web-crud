<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.example.projetodiogo.model.Boletim" %>
<%@ page import="org.example.projetodiogo.model.Disciplina" %>

<%
    ArrayList<Disciplina> disciplinasList = (ArrayList<Disciplina>) request.getAttribute("disciplinasList");
    ArrayList<Boletim> boletimList = (ArrayList<Boletim>) request.getAttribute("boletimList");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Boletim</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/aluno.css">
</head>

<body>

<jsp:include page="/WEB-INF/views/componentes/sidebarAluno.jsp"/>

<div class="topo">
    <h1>Boletim</h1>
    <a class="btn" href="${pageContext.request.contextPath}/aluno/gerarBoletim">Gerar boletim</a>
</div>

<div class="container">

    <p><strong>Disciplina:</strong> <%= disciplinasList.get(0).getNomeFormatado() %></p>
    <p><strong>Professor:</strong></p>

    <table>
        <thead>
        <tr>
            <th>Disciplina</th>
            <th>N1</th>
            <th>N2</th>
            <th>Média Final</th>
            <th>Situação</th>
        </tr>
        </thead>
        <tbody>

        <%
            if (boletimList != null) {
                for (int i = 0; i < boletimList.size(); i++) {
                    Boletim b = boletimList.get(i);
                    String nomeDisciplina = disciplinasList.get(i).getNome();

                    double mediaFinal = b.getMediaFinal();
                    boolean aprovado = mediaFinal >= 7;
        %>

        <tr>
            <td><%= nomeDisciplina %></td>

            <td class="azul"><%= b.getMedia1() %></td>

            <td class="vermelho"><%= b.getMedia2() %></td>

            <td class="<%= aprovado ? "azul" : "vermelho" %>">
                <%= mediaFinal %>
            </td>

            <td class="<%= aprovado ? "azul" : "vermelho" %>">
                <%= b.getSituacao() %>
            </td>
        </tr>

        <%
                }
            }
        %>

        </tbody>
    </table>

</div>

</body>
</html>