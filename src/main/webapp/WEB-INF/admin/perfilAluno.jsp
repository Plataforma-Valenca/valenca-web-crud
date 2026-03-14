<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.projetodiogo.model.*" %>
<%@ page import="org.example.projetodiogo.model.DTO.AlunoConsultaDTO" %>
<%@ page import="org.example.projetodiogo.model.DTO.ProfessorConsultaDTO" %>
<%@ page import="java.util.ArrayList" %>

<%

    AlunoConsultaDTO aluno = (AlunoConsultaDTO) request.getAttribute("alunoConsulta");

    ArrayList<Disciplina> disciplinasList =
            (ArrayList<Disciplina>) request.getAttribute("disciplinasList");

    ArrayList<Boletim> boletimList =
            (ArrayList<Boletim>) request.getAttribute("boletimList");

    List<Observacao> obsList =
            (List<Observacao>) request.getAttribute("obsList");

    List<ProfessorConsultaDTO> professores =
            (List<ProfessorConsultaDTO>) request.getAttribute("professores");

    String nomeTurma = (String) request.getAttribute("nomeTurma");

    int idTurma = (Integer) request.getAttribute("idTurma");

%>

<html>

<head>

    <title>Colégio Valença - Perfil do Aluno</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/page-grid.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/aluno.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/modal.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/perfilAluno.css">

</head>

<body>

<jsp:include page="/WEB-INF/views/componentes/sidebarAdm.jsp">
    <jsp:param name="activePage" value="turmas"/>
</jsp:include>

<div class="page-content">

    <header class="page-grid-header">

        <div class="page-grid-header-state">

            <a href="${pageContext.request.contextPath}/admin/verTurmas">Turmas</a>

            <p>></p>

            <a href="${pageContext.request.contextPath}/admin/verAlunosTurma?idTurma=<%= idTurma %>&nomeTurma=<%= nomeTurma %>">
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

        <!-- NOTAS -->

        <div class="aluno-details-notas">

            <div class="aluno-details-notas-header"
                 style="display:flex; justify-content:space-between; align-items:center;">

                <h4>Notas</h4>

                <button onclick="alert('Aqui você pode abrir o modal de atualizar notas')" class="btn-acao">
                    Atualizar Notas
                </button>

            </div>

            <div class="aluno-details-notas-table" style="margin-top:2vh">

                <div class="header-table-list-row table-list-row">

                    <h5 style="flex:2">Disciplina</h5>
                    <h5 style="flex:1">N1</h5>
                    <h5 style="flex:1">N2</h5>
                    <h5 style="flex:1">Média</h5>
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
                                corSituacao = "green";
                            } else if ("RECUPERAÇÃO".equals(b.getSituacao())) {
                                corSituacao = "orange";
                            } else if ("REPROVADO".equals(b.getSituacao())) {
                                corSituacao = "red";
                            }

                %>

                <div class="row-table-list-row table-list-row">

                    <p style="flex:2; font-weight:500;">
                        <%= nomeDisciplina %>
                    </p>

                    <p style="flex:1; color:<%= corN1 %>;">
                        <%= (n1 != null ? n1 : "--") %>
                    </p>

                    <p style="flex:1; color:<%= corN2 %>;">
                        <%= (n2 != null ? n2 : "--") %>
                    </p>

                    <p style="flex:1; color:<%= corFinal %>;">
                        <%= (n1 != null && n2 != null ? ((n1 + n2) / 2) : "--") %>
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

            <div class="aluno-details-notas-header"
                 style="display:flex; justify-content:space-between; align-items:center;">

                <h4>Observações</h4>

                <button onclick="abrirModalObs()" class="btn-acao">
                    + Nova Observação
                </button>

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

</div>

<!-- MODAL OBSERVAÇÃO -->

<div id="modalObs" class="modal">

    <div class="modal-content">

        <h3>Adicionar Observação</h3>

        <form action="${pageContext.request.contextPath}/admin/adicionarObservacao" method="post">

            <input type="hidden" name="idAluno" value="<%= aluno.getIdAluno() %>">

            <label>Professor</label>

            <select name="idProfessor" style="width:100%; padding:8px;" required>

                <option value="">Selecione um professor</option>

                <%

                    if(professores != null){

                        for(ProfessorConsultaDTO prof : professores){

                %>

                <option value="<%= prof.getIdProfessor() %>">
                    <%= prof.getNome() %>
                </option>

                <%

                        }

                    }

                %>

            </select>

            <br><br>

            <label>Observação</label>

            <textarea name="descricao"
                      placeholder="Digite a observação..."
                      style="width:100%; height:120px; padding:10px;"
                      required></textarea>

            <br><br>

            <div style="display:flex; justify-content:flex-end; gap:10px">

                <button type="submit" class="btn-acao">
                    Salvar
                </button>

                <button type="button" onclick="fecharModalObs()">
                    Cancelar
                </button>

            </div>

        </form>

    </div>

</div>

<script>

    function abrirModalObs(){

        document.getElementById("modalObs").style.display="flex";

    }

    function fecharModalObs(){

        document.getElementById("modalObs").style.display="none";

    }

    window.onclick = function(event){

        let modal = document.getElementById("modalObs");

        if(event.target === modal){

            modal.style.display="none";

        }

    }

</script>

</body>

</html>
