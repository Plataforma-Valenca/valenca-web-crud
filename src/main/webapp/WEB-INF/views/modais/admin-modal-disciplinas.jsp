<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/modal.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/loading-button.css">
<script src="${pageContext.request.contextPath}/assets/js/loading-button.js" defer></script>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- MODAL CADASTRO DISCIPLINA -->
<div id="modalCadastro" class="modal">
    <div class="modal-content">

        <div class="modal-header">
            <h2>Nova Disciplina</h2>
            <span class="close-modal" onclick="fecharModalCadastro()">&times;</span>
        </div>

        <form action="${pageContext.request.contextPath}/admin/inserirDisciplina" method="post">

            <div class="form">
                <input type="text" name="nome" placeholder="Nome disciplina" required>
                <input type="text" name="professor" placeholder="Nome professor">
            </div>

            <div class="modal-footer">
                <button type="submit" class="btn-primary save btn-loading">Salvar</button>
            </div>

        </form>

    </div>
</div>

<!-- MODAL EDITAR DISCIPLINA -->
<div id="modalEditar" class="modal">
    <div class="modal-content">

        <div class="modal-header">
            <h2>Atualizar disciplina</h2>
            <span class="close-modal" onclick="fecharModalEditar()">&times;</span>
        </div>

        <form action="${pageContext.request.contextPath}/admin/editarDisciplina" method="post">

            <input type="hidden" id="idDisciplina" name="idDisciplina">

            <div class="form">
                <input type="text" id="editarNomeDisciplina" name="nomeDisciplina" readonly value="">
                <input type="hidden" id="editarIdProfessor" name="idProfessor">
                <input type="text" id="editarNomeProfessor" name="nomeProfessor" required>
            </div>

            <div class="modal-footer">
                <button type="submit" class="btn-primary save btn-loading">Salvar</button>
            </div>

        </form>

    </div>
</div>

<!-- MODAL EXCLUIR DISCIPLINA -->
<div id="modalExcluir" class="modal">
    <div class="modal-content">

        <h3>Deseja excluir esta disciplina?</h3>
        <p>Esta ação não poderá ser desfeita.</p>

        <form id="formExcluir" action="${pageContext.request.contextPath}/admin/deletarDisciplina" method="post">
            <input type="hidden" name="idDisciplina" id="excluirDisciplinaId">

            <div class="modal-footer">
                <button type="submit" class="btn-primary delete btn-loading btn-cancel">Excluir</button>
                <button type="button" onclick="fecharModalExcluir()" class="btn-primary cancel">Cancelar</button>
            </div>
        </form>

    </div>
</div>