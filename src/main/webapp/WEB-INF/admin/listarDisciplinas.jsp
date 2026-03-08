<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.projetodiogo.model.DTO.DisciplinasResumoDTO" %>

<html>
<head>
    <title>Colégio Valença</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/page-grid.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/list-card.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">

    <link rel="icon" type="image/x-icon"
          href="${pageContext.request.contextPath}/assets/img/icone-colegio-valenca.svg">
</head>

<body>

<jsp:include page="/WEB-INF/views/componentes/sidebarAdm.jsp">
    <jsp:param name="activePage" value="disciplinas" />
</jsp:include>

<div class="page-content">

    <header class="page-grid-header" style="flex-direction: row; justify-content: space-between; align-items: flex-end;">
        <div>
            <h1 class="page-grid-header-title">Disciplinas</h1>
        </div>
        <button class="btn-primary" style="width: 150px; margin-bottom: 5px;" onclick="abrirModal()">
            + Cadastrar
        </button>
    </header>

    <main class="page-grid-main">
        <div class="page-grid-main-content card-grid">
            <%
                List<DisciplinasResumoDTO> disciplinasList = (List<DisciplinasResumoDTO>) request.getAttribute("resumoList");

                if (disciplinasList != null && !disciplinasList.isEmpty()) {
                    for (DisciplinasResumoDTO d : disciplinasList) {
            %>
            <div class="card-items">
                <div class="card-items-infos">
                    <h4><%= d.getNomeFormatado() %></h4>
                    <p style="font-weight: 400; color: #535353">
                        <%= d.getNomeProfessor() != null ? "Prof. " + d.getNomeProfessor() : "Sem professor" %>
                    </p>
                </div>

                <div class="card-actions">
                    <a href="${pageContext.request.contextPath}/admin/editarDisciplina?id=<%= d.getIdDisciplina() %>"
                       title="Editar" style="color: #535353; font-size: 1.1rem;">
                        <i class="fa-solid fa-pen"></i>
                    </a>
                    <a href="${pageContext.request.contextPath}/admin/excluirDisciplina?id=<%= d.getIdDisciplina() %>"
                       onclick="return confirm('Tem certeza que deseja excluir esta disciplina?')"
                       title="Excluir" style="color: #E74C3C; font-size: 1.1rem;">
                        <i class="fa-solid fa-trash"></i>
                    </a>
                </div>
            </div>
            <%
                }
            } else {
            %>
            <p class="no-data" style="grid-column: span 3; text-align: center; color: #999; margin-top: 20px;">
                Nenhuma disciplina cadastrada.
            </p>
            <%
                }
            %>
        </div>
    </main>

</div>

<div id="modalCadastro" class="modal">
    <div class="modal-content">
        <h2 style="margin-bottom: 20px; color: var(--blue-primary);">Nova Disciplina</h2>

        <form action="${pageContext.request.contextPath}/admin/cadastrarDisciplina" method="post">
            <div class="form-control">
                <label>Nome da Disciplina</label>
                <input type="text" name="nome" placeholder="Ex: Matemática, História..." required
                       style="padding: 12px; border: 1px solid #ddd; border-radius: 10px; margin-bottom: 15px;">
            </div>

            <div class="modal-footer" style="display: flex; gap: 10px; margin-top: 10px;">
                <button type="submit" class="btn-primary" style="flex: 1;">Salvar</button>
                <button type="button" class="btn-secondary" onclick="fecharModal()"
                        style="flex: 1; background: #eee; border: none; border-radius: 10px; cursor: pointer;">
                    Cancelar
                </button>
            </div>
        </form>
    </div>
</div>

<script>
    function abrirModal() {
        const modal = document.getElementById("modalCadastro");
        modal.style.display = "flex";
        document.body.style.overflow = "hidden";
    }

    function fecharModal() {
        const modal = document.getElementById("modalCadastro");
        modal.style.display = "none";
        document.body.style.overflow = "auto";
    }

    window.onclick = function(event) {
        const modal = document.getElementById("modalCadastro");
        if (event.target === modal) {
            fecharModal();
        }
    }
</script>
</body>
</html>