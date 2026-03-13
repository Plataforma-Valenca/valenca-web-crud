<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.projetodiogo.model.Turma" %>

<html>
<head>
    <title>Colégio Valença</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/page-grid.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/admin.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/list-card.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/modal.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head>

<body>

<jsp:include page="/WEB-INF/views/componentes/sidebarAdm.jsp">
    <jsp:param name="activePage" value="turmas"/>
</jsp:include>

<div class="page-content">

    <header class="page-grid-header">
        <h1 class="page-grid-header-title">Gerenciar Turmas</h1>
        <button class="btn-primary" onclick="abrirModalCadastro()">Cadastrar</button>
    </header>

    <main class="page-grid-main">
        <div class="page-grid-main-content card-grid">

            <%
                List<Turma> turmas = (List<Turma>) request.getAttribute("turmas");

                if (turmas != null && !turmas.isEmpty()) {
                    for (Turma t : turmas) {
            %>

            <a class="card-items"
               href="${pageContext.request.contextPath}/admin/verAlunosTurma?idTurma=<%= t.getId() %>&nomeTurma=<%= t.getNome() %>">
<span class="card-title">
 <%= t.getNome() %>
</span>

                <div class="card-actions">

                    <i class="fa-solid fa-pen"
                       onclick="event.stopPropagation();event.preventDefault();
                               abrirModalEditar('<%=t.getId()%>','<%=t.getAno()%>','<%=t.getNome()%>')">
                    </i>

                    <i class="fa-solid fa-trash"
                       style="color: var(--color-error);"
                       onclick="event.stopPropagation();event.preventDefault();
                               abrirModalExcluir('<%=t.getId()%>')">
                    </i>

                </div>
            </a>

            <%
                }
            }else{
            %>

            <p class="no-data">Nenhuma turma cadastrada no sistema.</p>

            <%
                }
            %>

        </div>
    </main>
</div>

<!-- MODAL CADASTRO -->
<div id="modalCadastro" class="modal">
    <div class="modal-content">

        <div class="modal-header">
            <h2>Nova Turma</h2>
            <span class="close-modal" onclick="fecharModalCadastro()">&times;</span>
        </div>

        <form action="${pageContext.request.contextPath}/admin/inserirTurma" method="post">
            <input type="text" name="ano" placeholder="Ex: 2025" required>
            <input type="text" name="nome" placeholder="Ex: 6° Ano A" required>

            <div class="modal-footer">
                <button type="submit" class="btn-primary">Salvar</button>
            </div>

        </form>
    </div>
</div>

<!-- MODAL EDITAR -->
<div id="modalEditar" class="modal">
    <div class="modal-content">

        <div class="modal-header">
            <h2>Atualizar turma</h2>
            <span class="close-modal" onclick="fecharModalEditar()">&times;</span>
        </div>

        <form action="${pageContext.request.contextPath}/admin/editarTurma" method="post">

            <input type="hidden" id="editarId" name="id">

            <input type="text" id="editarAno" name="ano" required>
            <input type="text" id="editarNome" name="nome" required>

            <div class="modal-footer">
                <button type="submit" class="btn-secondary">Salvar</button>
            </div>

        </form>
    </div>
</div>

<!-- MODAL EXCLUIR -->
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

    function abrirModalCadastro(){
        document.getElementById("modalCadastro").style.display="flex";
    }

    function fecharModalCadastro(){
        document.getElementById("modalCadastro").style.display="none";
    }

    function abrirModalEditar(id,ano,nome){

        document.getElementById("modalEditar").style.display="flex";

        document.getElementById("editarId").value=id;
        document.getElementById("editarAno").value=ano;
        document.getElementById("editarNome").value=nome;

    }

    function fecharModalEditar(){
        document.getElementById("modalEditar").style.display="none";
    }

    function abrirModalExcluir(id){

        document.getElementById("modalExcluir").style.display="flex";

        document.getElementById("btnConfirmarExcluir").href=
            "${pageContext.request.contextPath}/admin/deletarTurma?id="+id;

    }

    function fecharModalExcluir(){
        document.getElementById("modalExcluir").style.display="none";
    }

    window.onclick=function(event){

        if(event.target.className==='modal'){
            event.target.style.display="none";
        }

    }

</script>

</body>
</html>