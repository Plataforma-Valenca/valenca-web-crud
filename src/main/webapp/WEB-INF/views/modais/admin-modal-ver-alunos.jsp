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

            <input type="hidden" name="idTurma" value="<%= request.getParameter("idTurma") %>">

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