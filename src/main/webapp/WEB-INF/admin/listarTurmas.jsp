<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.projetodiogo.model.Turma" %>

<html>
<head>
    <title>Colégio Valença - Administração</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/page-grid.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/list-card.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/assets/img/icone-colegio-valenca.svg">
</head>

<body>

<jsp:include page="/WEB-INF/views/componentes/sidebarAdm.jsp">
    <jsp:param name="activePage" value="turmas"/>
</jsp:include>

<div class="page-content">

    <header class="page-grid-header">
        <h1 class="page-grid-header-title">Gerenciar Turmas</h1>
        <button class="btn-primary" onclick="abrirModalCadastro()">
            <i class="fa-solid fa-plus"></i> Cadastrar Turma
        </button>
    </header>

    <main class="page-grid-main">
        <div class="page-grid-main-content card-grid">
            <%
                List<Turma> turmas = (List<Turma>) request.getAttribute("turmas");

                if (turmas != null && !turmas.isEmpty()) {
                    for (Turma t : turmas) {
            %>
            <div class="card-items">
                <span><%= t.getNome() %></span>

                <div class="card-actions">
                    <a href="${pageContext.request.contextPath}/admin/verAlunosTurma?idTurma=<%= t.getId() %>">
                        <i class="fa-solid fa-eye"></i>
                    </a>
                    <a href="javascript:void(0)" onclick="abrirModalExcluir(<%= t.getId() %>)">
                        <i class="fa-solid fa-trash"></i>
                    </a>
                </div>
            </div>
            <%
                }
            } else {
            %>
            <p class="no-data">Nenhuma turma cadastrada no sistema.</p>
            <%
                }
            %>
        </div>
    </main>
</div>

<div id="modalCadastro" class="modal">
    <div class="modal-content">
        <h2>Nova Turma</h2>
        <form action="${pageContext.request.contextPath}/admin/cadastrarTurma" method="post">
            <input type="text" name="serie" placeholder="Ex: 1º Ano" required>
            <input type="text" name="letra" placeholder="Ex: A" required>
            <div class="modal-footer">
                <button type="submit" class="btn-primary">Salvar</button>
                <button type="button" onclick="fecharModalCadastro()" class="btn-secondary">Cancelar</button>
            </div>
        </form>
    </div>
</div>

<div id="modalExcluir" class="modal">
    <div class="modal-content">
        <h3>Deseja excluir esta turma?</h3>
        <p>Esta ação não poderá ser desfeita.</p>
        <div class="modal-footer">
            <a id="btnConfirmarExcluir" class="btn-danger">Excluir</a>
            <button onclick="fecharModalExcluir()" class="btn-secondary">Cancelar</button>
        </div>
    </div>
</div>

<script>
    function abrirModalCadastro() {
        document.getElementById("modalCadastro").style.display = "flex";
    }
    function fecharModalCadastro() {
        document.getElementById("modalCadastro").style.display = "none";
    }
    function abrirModalExcluir(id) {
        document.getElementById("modalExcluir").style.display = "flex";
        document.getElementById("btnConfirmarExcluir").href =
            "${pageContext.request.contextPath}/admin/deletarTurma?id=" + id;
    }
    function fecharModalExcluir() {
        document.getElementById("modalExcluir").style.display = "none";
    }
</script>

</body>
</html>