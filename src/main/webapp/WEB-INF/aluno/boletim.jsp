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
    <title>Colégio Valença - Boletim</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/page-grid.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/aluno.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/modal.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/detalhes-aluno.css">

    <link rel="icon" type="image/x-icon"
          href="${pageContext.request.contextPath}/assets/img/icone-colegio-valenca.svg">
</head>

<body>

<jsp:include page="/WEB-INF/views/componentes/sidebarAluno.jsp">
    <jsp:param name="activePage" value="boletim"/>
</jsp:include>

<div class="page-content">

    <header class="page-grid-header">

        <h1 class="page-grid-header-title">
            Boletim
        </h1>

        <p class="professor-subtitle" style="color:#535353;">
            Acompanhamento de notas por disciplina.
        </p>

    </header>

    <main class="page-grid-main" style="gap:5vh; padding-bottom:80px; margin-top:4vh;">

        <div class="aluno-details-notas">

            <div class="aluno-details-notas-header">
                <h4>Notas</h4>
            </div>

            <div class="aluno-details-notas-table" style="margin-top:2vh">


                <div class="header-table-list-row table-list-row">

                    <h5 style="flex:2">Disciplina</h5>
                    <h5 style="flex:0.8">N1</h5>
                    <h5 style="flex:0.8">N2</h5>
                    <h5 style="flex:1.2">Média Final</h5>
                    <h5 style="flex:1.5">Situação</h5>

                </div>

                <%
                    if (boletimList != null && disciplinasList != null && !boletimList.isEmpty()) {

                        for (int i = 0; i < boletimList.size(); i++) {

                            Boletim b = boletimList.get(i);

                            String nomeDisciplina =
                                    (i < disciplinasList.size())
                                            ? disciplinasList.get(i).getNome()
                                            : "N/A";

                            Double n1 = b.getMedia1();
                            Double n2 = b.getMedia2();
                            double mediaFinal = b.getMediaFinal();

                            String corN1 = (n1 != null && n1 >= 7) ? "#4A90E2" : "#E74C3C";
                            String corN2 = (n2 != null && n2 >= 7) ? "#4A90E2" : "#E74C3C";
                            String corFinal = (mediaFinal >= 7) ? "#4A90E2" : "#E74C3C";

                            String corSituacao = "#E74C3C";

                            if ("APROVADO".equals(b.getSituacao())) {
                                corSituacao = "#green";
                            } else if ("RECUPERAÇÃO".equals(b.getSituacao())) {
                                corSituacao = "orange";
                            }else if ("RECUPERAÇÃO".equals(b.getSituacao())) {
                                corSituacao = "red";
                            }
                %>


                <div class="row-table-list-row table-list-row"
                     style="border-bottom:1px solid #eee; padding:12px 0;">

                    <p style="flex:2; font-weight:500;">
                        <%= nomeDisciplina %>
                    </p>

                    <p style="flex:0.8; color:<%= corN1 %>;">
                        <%= (n1 != null ? n1 : "--") %>
                    </p>

                    <p style="flex:0.8; color:<%= corN2 %>;">
                        <%= (n2 != null ? n2 : "--") %>
                    </p>

                    <p style="flex:1.2; color:<%= corFinal %>;">
                        <%= mediaFinal %>
                    </p>

                    <p style="flex:1.5; font-weight:500; color:<%= corSituacao %>;">
                        <%= b.getSituacao() %>
                    </p>

                </div>

                <%
                    }
                } else {
                %>

                <div style="text-align:center; padding:40px; color:#999;">
                    Nenhum registro de boletim encontrado.
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