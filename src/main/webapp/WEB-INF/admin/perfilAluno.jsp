<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.projetodiogo.model.*" %>
<%@ page import="org.example.projetodiogo.model.DTO.AlunoConsultaDTO" %>
<%@ page import="java.util.ArrayList" %>

<%
    AlunoConsultaDTO aluno = (AlunoConsultaDTO) request.getAttribute("alunoConsulta");
    ArrayList<Disciplina> disciplinasList = (ArrayList<Disciplina>) request.getAttribute("disciplinasList");
    ArrayList<Boletim> boletimList = (ArrayList<Boletim>) request.getAttribute("boletimList");
    List<Observacao> obsList = (List<Observacao>) request.getAttribute("obsList");
    String nomeTurma = (String) request.getAttribute("nomeTurma");
    int idTurma = (Integer) request.getAttribute("idTurma");
%>

<html>
<head>
    <title>Colégio Valença - Perfil do Aluno</title>

    ```
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/page-grid.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/aluno.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/modal.css">


    <link rel="icon" type="image/x-icon"
          href="${pageContext.request.contextPath}/assets/img/icone-colegio-valenca.svg">
    ```

</head>

<body>

<jsp:include page="/WEB-INF/views/componentes/sidebarAdm.jsp">
    <jsp:param name="activePage" value="turmas"/>
</jsp:include>

<div class="page-content">

    ```
    <header class="page-grid-header">

        <div class="page-grid-header-state">

            <a href="${pageContext.request.contextPath}/admin/verTurmas">Turmas</a>
            <p>></p>

            <a href="${pageContext.request.contextPath}/admin/verAlunos?idTurma=<%= idTurma %>&nomeTurma=<%= nomeTurma %>">
                Alunos
            </a>

            <p>></p>
            <b>Detalhes</b>

        </div>

        <h1 class="page-grid-header-title">
            <%= aluno != null ? aluno.getNome() : "Aluno" %>
        </h1>

    </header>

    <main class="page-grid-main" style="gap:5vh; padding-bottom:80px;">

        <div class="aluno-details-infos">

            <div class="details-infos-box">
                <h4>Matrícula</h4>
                <p><%= aluno != null ? aluno.getMatricula() : "--" %></p>
            </div>

            <div class="details-infos-box">
                <h4>Turma</h4>
                <p><%= aluno != null ? aluno.getTurma() : "--" %></p>
            </div>

        </div>

        <!-- BOLETIM -->

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

                    <p style="flex:1.2;">
                        <%= (n1 != null && n2 != null ? ((n1 + n2) / 2) : "--") %>
                    </p>

                    <p style="flex:0.8;">--</p>

                    <p style="flex:0.8;">--</p>
                    <p style="flex:1.2;">--</p>
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

        <!-- OBSERVAÇÕES -->

        <div class="aluno-details-notas">

            <div class="aluno-details-notas-header">

                <h4>Observações</h4>


            </div>

            <div class="aluno-details-notas-table">

                <div class="header-table-list-row">

                    <h5 style="flex:1">Data</h5>
                    <h5 style="flex:3">Observação</h5>

                </div>

                <%

                    if(obsList != null && !obsList.isEmpty()){

                        for(Observacao obs : obsList){
                %>

                <div class="row-table-list-row">

                    <p style="flex:1">
                        <%= obs.getDataEnvio() %>
                    </p>

                    <p style="flex:3">
                        <%= obs.getDescricao() %>
                    </p>

                </div>

                <%
                    }
                } else {
                %>

                <div style="text-align:center; padding:20px; color:#999;">
                    Nenhuma observação registrada.
                </div>

                <%
                    }
                %>

            </div>

        </div>

    </main>
    ```

</div>


<script>


    function abrirModalObs() {
        document.getElementById("modalObs").style.display = "flex";
    }

    function fecharModalCadastroAluno() {
        document.getElementById("modalObs").style.display = "none";
    }

</script>

</body>
</html>
