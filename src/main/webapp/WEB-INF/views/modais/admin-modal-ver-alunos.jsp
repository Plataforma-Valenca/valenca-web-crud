<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/modal.css">

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- MODAL CADASTRO ALUNO -->
<div id="modalCadastroAluno" class="modal">
    <div class="modal-content">

        <div class="modal-header">
            <h2>Novo Aluno</h2>
            <span class="close-modal" onclick="fecharModalCadastroAluno()">&times;</span>
        </div>

        <form action="${pageContext.request.contextPath}/admin/inserirAluno" method="post">

            <input type="hidden" name="idTurma" value="<%= request.getAttribute("idTurma") %>">
            <input type="hidden" name="nomeTurma" value="<%= request.getAttribute("nomeTurma") %>">
            <div class="form">
                <input type="text" name="cpf" placeholder="CPF" required>
                <input type="text" name="senhaProvisoria" placeholder="Senha provisória" required>
            </div>

            <div class="modal-footer">
                <button type="submit" class="btn-primary save">Salvar</button>
            </div>

        </form>

    </div>
</div>

<!-- MODAL EDITAR ALUNO -->
<div id="modalEditarAluno" class="modal">
    <div class="modal-content">

        <div class="modal-header">
            <h2>Editar Aluno</h2>
            <span class="close-modal" onclick="fecharModalEditarAluno()">&times;</span>
        </div>

        <form action="${pageContext.request.contextPath}/admin/editarAluno" method="post">

            <input type="hidden" name="idAluno" id="editIdAluno">
            <input type="hidden" name="idTurma" value="<%= request.getAttribute("idTurma") %>">
            <input type="hidden" name="nomeTurma" value="<%= request.getAttribute("nomeTurma") %>">

            <div class="form">
                <input type="text" name="nome" id="editNome" placeholder="Nome" required>
                <input type="text" name="matricula" id="editMatricula" placeholder="Matrícula" required>
            </div>

            <div class="modal-footer">
                <button type="submit" class="btn-primary save">Salvar</button>
            </div>

        </form>

    </div>
</div>

<!-- MODAL EXCLUIR ALUNO -->
<div id="modalExcluirAluno" class="modal">
    <div class="modal-content">

        <h3>Deseja excluir este aluno?</h3>
        <p>Esta ação não poderá ser desfeita.</p>

        <form action="${pageContext.request.contextPath}/admin/deletarAluno" method="post">
            <input type="hidden" name="idAluno" id="excluirAlunoId">
            <input type="hidden" name="idTurma" value="<%= request.getAttribute("idTurma") %>">
            <input type="hidden" name="nomeTurma" value="<%= request.getAttribute("nomeTurma") %>">

            <div class="modal-footer">
                <button type="submit" class="btn-primary delete">Excluir</button>
                <button type="button" onclick="fecharModalExcluirAluno()" class="btn-primary cancel">Cancelar</button>
            </div>
        </form>
    </div>
</div>