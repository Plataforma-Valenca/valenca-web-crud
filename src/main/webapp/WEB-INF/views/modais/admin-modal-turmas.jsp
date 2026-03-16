<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/modal.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/loading-button.css">
<script src="${pageContext.request.contextPath}/assets/js/loading-button.js" defer></script>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- MODAL CADASTRO TURMA -->
<div id="modalCadastro" class="modal">
    <div class="modal-content">

        <div class="modal-header">
            <h2>Nova Turma</h2>
            <span class="close-modal" onclick="fecharModalCadastro()">&times;</span>
        </div>

        <form action="${pageContext.request.contextPath}/admin/inserirTurma" method="post">

            <div class="form">
                <input type="text" name="ano" placeholder="Ex: 2025" required>
                <input type="text" name="nome" placeholder="Ex: 6° Ano A" required>
            </div>

            <div class="modal-footer">
                <button type="submit" class="btn-primary save btn-loading">Salvar</button>
            </div>

        </form>

    </div>
</div>

<!-- MODAL EDITAR TURMA -->
<div id="modalEditar" class="modal">
    <div class="modal-content">

        <div class="modal-header">
            <h2>Atualizar Turma</h2>
            <span class="close-modal" onclick="fecharModalEditar()">&times;</span>
        </div>

        <form action="${pageContext.request.contextPath}/admin/editarTurma" method="post">

            <input type="hidden" id="editarId" name="id">

            <div class="form">
                <input type="text" id="editarAno" name="ano" required>
                <input type="text" id="editarNome" name="nome" required>
            </div>

            <div class="modal-footer">
                <button type="submit" class="btn-primary save btn-loading">Salvar</button>
            </div>

        </form>

    </div>
</div>

<!-- MODAL EXCLUIR TURMA -->
<div id="modalExcluir" class="modal">
    <div class="modal-content">

        <h3>Deseja excluir esta turma?</h3>
        <p>Esta ação não poderá ser desfeita.</p>

        <form id="formExcluir" action="${pageContext.request.contextPath}/admin/deletarTurma" method="post">
            <input type="hidden" name="idTurma" id="excluirTurmaId">

            <div class="modal-footer">
                <button type="submit" class="btn-primary delete btn-loading">Excluir</button>
                <button type="button" onclick="fecharModalExcluir()" class="btn-primary cancel">Cancelar</button>
            </div>
        </form>

    </div>
</div>