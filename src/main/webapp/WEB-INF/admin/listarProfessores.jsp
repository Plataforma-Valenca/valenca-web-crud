<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.example.projetodiogo.model.DTO.ProfessorConsultaDTO" %>
<%@ page import="org.example.projetodiogo.model.Disciplina" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>

<%
    List<ProfessorConsultaDTO> professoresList = (List<ProfessorConsultaDTO>) request.getAttribute("professoresList");
    List<Disciplina> disciplinas = (List<Disciplina>) request.getAttribute("disciplinas");
    String busca = request.getAttribute("busca") != null ? request.getAttribute("busca").toString() : "";
%>

<html>
<head>
    <title>Colégio Valença</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/page-grid.css">
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/assets/img/icone-colegio-valenca.svg">
</head>
<body>

<jsp:include page="/WEB-INF/views/componentes/sidebarAdm.jsp">
    <jsp:param name="activePage" value="professores" />
</jsp:include>

<div class="page-content">
    <header class="page-grid-header">
        <div class="page-grid-header-state">

        </div>

        <div style="display: flex; justify-content: space-between; align-items: center;">
            <h1 class="page-grid-header-title">Professores</h1>
            <button class="btn-primary" style="width: 150px" onclick="abrirModal()">+ Cadastrar</button>
        </div>
    </header>

    <main class="page-grid-main" style="gap: 5vh; padding-bottom: 80px;">
        <div class="top-box-page-grid-main">
            <form action="${pageContext.request.contextPath}/admin/verProfessores" method="get" class="form-busca">
                <div class="form-control">
                    <h5>Buscar professor</h5>
                    <div class="form-control-action input-primary">
                        <input type="text" name="busca" placeholder="Nome ou CPF" value="<%= busca %>">
                        <button type="submit" class="btn btn-primary">Buscar</button>
                    </div>
                </div>
            </form>
        </div>

        <div class="bottom-box-page-grid-main">
            <div class="page-grid-main-content">
                <div class="table-list-row">
                    <div class="header-table-list-row" style="display: flex">
                        <h4 style="flex: 2;">Nome</h4>
                        <h4 style="flex: 2;">Email</h4>
                        <h4 style="flex: 1.5;">CPF</h4>
                        <h4 style="flex: 1.5;">Disciplina</h4>
                    </div>

                    <div class="body-table-list-row">
                        <%
                            if (professoresList != null && !professoresList.isEmpty()) {
                                for (ProfessorConsultaDTO p : professoresList) {
                        %>
                        <div class="itens-per-table" style="display: flex; align-items: center">
                            <div style="flex: 2;"><%= p.getNome() %></div>
                            <div style="flex: 2;"><%= p.getEmail() %></div>
                            <div style="flex: 1.5;"><%= p.getCpf() %></div>
                            <div style="flex: 1.5;"><%= p.getDisciplina() %></div>
                            <div><i class="fa-solid fa-trash" style="cursor: pointer"></i></div>
                        </div>
                        <%
                            }
                        } else {
                        %>
                        <div style="text-align:center; padding: 40px; color: #999;">
                            Nenhum professor encontrado.
                        </div>
                        <% } %>
                    </div>
                </div>
            </div>
        </div>
    </main>
</div>

<div id="modalCadastro" class="modal" style="display: none;">
    <div class="modal-content">
        <div class="modal-header">
            <h2>Cadastrar Professor</h2>
        </div>
        <form method="post" action="${pageContext.request.contextPath}/admin/cadastrarProfessor">
            <div class="form-control">
                <input type="text" name="nome" placeholder="Nome Completo" required>
                <input type="email" name="email" placeholder="E-mail Acadêmico" required>
                <input type="text" name="cpf" placeholder="CPF" required>

                <select name="idDisciplina" class="input-primary" style="width: 100%; margin-top: 10px; padding: 10px; border-radius: 8px; border: 1px solid #ddd;">
                    <option value="">Selecione a disciplina</option>
                    <% if(disciplinas != null) {
                        for(Disciplina d : disciplinas) { %>
                    <option value="<%= d.getId() %>"><%= d.getNome() %></option>
                    <% } } %>
                </select>
            </div>

            <div class="modal-footer" style="margin-top: 20px; display: flex; gap: 10px;">
                <button type="submit" class="btn-primary" style="flex: 1;">Salvar</button>
                <button type="button" class="btn-secondary" onclick="fecharModal()" style="flex: 1;">Cancelar</button>
            </div>
        </form>
    </div>
</div>

<script>
    function abrirModal() {
        document.getElementById("modalCadastro").style.display = "flex";
    }
    function fecharModal() {
        document.getElementById("modalCadastro").style.display = "none";
    }
    // Fechar modal ao clicar fora dele
    window.onclick = function(event) {
        let modal = document.getElementById("modalCadastro");
        if (event.target == modal) {
            fecharModal();
        }
    }
</script>

</body>
</html>