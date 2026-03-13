<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.projetodiogo.model.DTO.AlunoConsultaDTO" %>

<html>
<head>
    <title>Colégio Valença</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/page-grid.css">
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/assets/img/icone-colegio-valenca.svg">
</head>
<body>

<jsp:include page="/WEB-INF/views/componentes/sidebarAdm.jsp">
    <jsp:param name="activePage" value="turmas" />
</jsp:include>

<div class="page-content">

    <header class="page-grid-header">

        <div class="page-grid-header-state">
            <a href="${pageContext.request.contextPath}/admin/verTurmas">Turmas</a>
            <p>></p>
            <b>Alunos</b>
        </div>

        <h1 class="page-grid-header-title">
            <%= request.getParameter("nomeTurma") != null ? request.getParameter("nomeTurma") : "--" %>
        </h1>

        <button class="btn-primary" onclick="abrirModalCadastroAluno()">
            Cadastrar aluno
        </button>

    </header>

    <main class="page-grid-main" style="gap: 5vh; padding-bottom: 80px;">

        <div class="top-box-page-grid-main">
            <form action="${pageContext.request.contextPath}/admin/verAlunosTurma" method="get" class="form-busca">

                <input type="hidden" name="idTurma" value="<%= request.getParameter("idTurma") %>">
                <input type="hidden" name="nomeTurma" value="<%= request.getParameter("nomeTurma") %>">

                <div class="form-control">
                    <h5>Buscar por matrícula</h5>
                    <div class="form-control-action input-primary">
                        <input type="text" name="busca" placeholder="0000000" value="<%= request.getParameter("busca") != null ? request.getParameter("busca") : "" %>">
                        <button type="submit" class="btn btn-primary">Buscar</button>
                    </div>
                </div>

            </form>
        </div>

        <div class="bottom-box-page-grid-main">
            <div class="page-grid-main-content">

                <div class="table-list-row">

                    <div class="header-table-list-row" style="display: flex">
                        <h4 style="flex: 1;">Nome do aluno</h4>
                        <h4 style="flex: 1;">Matrícula</h4>
                        <h4 style="flex:1;">Turma</h4>
                        <h4 style="flex: .3;"></h4>
                    </div>

                    <div class="body-table-list-row">

                        <%
                            List<AlunoConsultaDTO> alunosList = (List<AlunoConsultaDTO>) request.getAttribute("alunos");
                            if (alunosList != null && !alunosList.isEmpty()) {
                                for (AlunoConsultaDTO aluno : alunosList) {
                        %>

                        <div class="itens-per-table" style="display:flex; align-items:center">

                            <div style="flex:1;">
                                <a href="${pageContext.request.contextPath}/admin/detalhesAluno?idAluno=<%= aluno.getIdAluno()%>&idTurma=<%= request.getParameter("idTurma")%>&nomeAluno=<%= aluno.getNome() %>&matricula=<%= aluno.getMatricula() %>&turma=<%= aluno.getTurma() %>">
                                    <%= aluno.getNome() %>
                                </a>
                            </div>

                            <div style="flex:1;"><%= aluno.getMatricula() %></div>
                            <div style="flex:1;"> <%=aluno.getTurma()%></div>

                            <div style="display:flex; gap:10px;">

                                <i class="fa-solid fa-pen"
                                   style="cursor:pointer;">
                                </i>

                                <i class="fa-solid fa-trash"
                                   style="color: var(--color-error); cursor:pointer;">
                                </i>

                            </div>

                        </div>

                        <%
                            }
                        } else {
                        %>

                        <div style="text-align:center; padding: 20px; color: #999;">
                            Nenhum aluno encontrado.
                        </div>

                        <% } %>

                    </div>
                </div>

            </div>
        </div>

    </main>
</div>


<div id="modalCadastroAluno" class="modal">
    <div class="modal-content">

        <div class="modal-header">
            <h2>Novo Aluno</h2>
            <span class="close-modal" onclick="fecharModalCadastroAluno()">&times;</span>
        </div>

        <form action="${pageContext.request.contextPath}/admin/inserirAluno" method="post">

            <input type="hidden" name="idTurma" value="<%= request.getParameter("idTurma") %>">
            <input type="text" name="cpf" placeholder="Cpf" required>
            <input type="text" name="senhaProvisoria" placeholder="senhaProvisoria" required>

            <div class="modal-footer">
                <button type="submit" class="btn-primary">Salvar</button>
            </div>

        </form>

    </div>
</div>



<script>

    function abrirModalCadastroAluno() {
        document.getElementById("modalCadastroAluno").style.display = "flex";
    }

    function fecharModalCadastroAluno() {
        document.getElementById("modalCadastroAluno").style.display = "none";
    }

    window.onclick = function(event) {
        if (event.target.className === 'modal') {
            event.target.style.display = "none";
        }
    }

</script>

</body>
</html>