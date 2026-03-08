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
    <title>Colégio Valença</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/page-grid.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/list-card.css">

    <link rel="icon" type="image/x-icon"
          href="${pageContext.request.contextPath}/assets/img/icone-colegio-valenca.svg">
</head>

<body>

<jsp:include page="/WEB-INF/views/componentes/sidebarAluno.jsp">
    <jsp:param name="activePage" value="boletim" />
</jsp:include>

<div class="page-content">

    <header class="page-grid-header">
        <h1 class="page-grid-header-title">Boletim</h1>
    </header>

    <main class="page-grid-main">
        <div class="page-grid-main-content card-grid">

            <div class="aluno-details-notas-table">

                <div class="header-table-list-row">
                    <h5 style="flex: 2;">Disciplina</h5>
                    <h5 style="flex: 1;">N1</h5>
                    <h5 style="flex: 1;">N2</h5>
                    <h5 style="flex: 1;">Média Final</h5>
                    <h5 style="flex: 1;">Situação</h5>
                </div>

                <%
                    if (boletimList != null && disciplinasList != null) {
                        for (int i = 0; i < boletimList.size(); i++) {

                            Boletim b = boletimList.get(i);
                            String nomeDisciplina = disciplinasList.get(i).getNome();

                            double mediaFinal = b.getMediaFinal();
                            boolean aprovado = mediaFinal >= 7;

                            String corFinal = aprovado ? "blue" : "red";

                            String corSituacao =
                                    b.getSituacao().equals("APROVADO") ? "blue" :
                                            b.getSituacao().equals("RECUPERAÇÃO") ? "orange" :
                                                    "red";
                %>

                <div class="row-table-list-row" style="border-bottom: 1px solid #eee;">

                    <p style="flex: 2;"><%= nomeDisciplina %></p>

                    <p style="flex: 1; color: <%= b.getMedia1() >= 7 ? "blue" : "red" %>;">
                        <%= b.getMedia1() %>
                    </p>

                    <p style="flex: 1; color: <%= b.getMedia2() >= 7 ? "blue" : "red" %>;">
                        <%= b.getMedia2() %>
                    </p>

                    <p style="flex: 1; color: <%= corFinal %>;">
                        <%= mediaFinal %>
                    </p>

                    <p style="flex: 1; color: <%= corSituacao %>;">
                        <%= b.getSituacao() %>
                    </p>

                </div>

                <%
                    }
                } else {
                %>

                <div style="text-align:center; padding:20px; color:#999;">
                    Nenhuma nota encontrada
                </div>

                <%
                    }
                %>

            </div>

        </div>
    </main>

</div>

</body>
</html>