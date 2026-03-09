<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.projetodiogo.model.*" %>
<%@ page import="org.example.projetodiogo.model.DTO.AlunoConsultaDTO" %>

<%
    AlunoConsultaDTO aluno = (AlunoConsultaDTO) request.getAttribute("alunoConsulta");
    List<Boletim> boletimList = (List<Boletim>) request.getAttribute("boletimList");
    List<Observacao> obsList = (List<Observacao>) request.getAttribute("obsList");
    String nomeTurma = (String) request.getAttribute("nomeTurma");
    String idTurma = (String) request.getAttribute("idTurma");
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
                <p><%= nomeTurma != null ? nomeTurma : "--" %></p>
            </div>

        </div>

        <!-- BOLETIM -->

        <div class="aluno-details-notas">

            <div class="aluno-details-notas-header">

                <h4>Notas</h4>

            </div>

            <div class="aluno-details-notas-table">

                <div class="header-table-list-row">


                    <h5 style="flex:1.5">Média 1º SEM</h5>

                    <h5 style="flex:1.5">Média 2º SEM</h5>

                    <h5 style="flex:1.2">Média Final</h5>

                </div>

                <%

                    if(boletimList != null && !boletimList.isEmpty()){

                        for(Boletim b : boletimList){

                            Double media1 = (b.getMedia1()!=null && b.getMedia2()!=null)
                                    ? (b.getMedia1()+b.getMedia2())/2 : null;

                %>

                <div class="row-table-list-row">

                    <p style="flex:1"><%= b.getMedia1()!=null ? b.getMedia1() : "--" %></p>
                    <p style="flex:1"><%= b.getMedia2()!=null ? b.getMedia2() : "--" %></p>

                    <p style="flex:1.2; font-weight:600;">
                        <%= b.getMediaFinal()!=null ? b.getMediaFinal() : "--" %>
                    </p>

                </div>

                <%
                    }
                } else {
                %>

                <div style="text-align:center; padding:30px; color:#999;">
                    Nenhuma nota cadastrada.
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
