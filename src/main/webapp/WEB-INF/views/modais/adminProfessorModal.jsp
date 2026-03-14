<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/modal.css">

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.example.projetodiogo.model.Disciplina" %>
<%@ page import="java.util.List" %>

<%
    List<Disciplina> disciplinas = (List<Disciplina>) request.getAttribute("disciplinas");
%>


<div id="modalCadastro" class="modal">
    <div class="modal-content">

        <div class="modal-header">
            <h2>Novo Professor</h2>
            <span class="close-modal" onclick="fecharModalCadastro()">&times;</span>
        </div>

        <form action="${pageContext.request.contextPath}/admin/inserirProfessor" method="post">

            <div class="form">
                <input type="text" name="nome" placeholder="Nome Completo" required>
                <input type="email" name="email" placeholder="E-mail Acadêmico" required>
                <input type="text" name="cpf" placeholder="CPF" required>
                <input type="text" name="senha" placeholder="Senha" required>
                <input type="text" name="username" placeholder="Nome de Usuário" required>
                <input type="text" name="disciplina" placeholder="Disciplina" required>
            </div>

            <div class="modal-footer">
                <button type="submit" class="btn-primary save">Salvar</button>
            </div>

        </form>

    </div>
</div>



<div id="modalEditar" class="modal">
    <div class="modal-content">

        <div class="modal-header">
            <h2>Editar Professor</h2>
            <span class="close-modal" onclick="fecharModalEditar()">&times;</span>
        </div>

        <form action="${pageContext.request.contextPath}/admin/editarProfessor" method="post">

            <input type="hidden" name="id" id="editarId">

            <input type="text" name="nome" id="editarNome" required>
            <input type="email" name="email" id="editarEmail" required>
            <input type="text" name="cpf" id="editarCpf" required>

            <select name="disciplina" id="editarDisciplina" required>

                <% if(disciplinas != null){
                    for(Disciplina d : disciplinas){ %>

                <option value="<%= d.getId() %>"><%= d.getNome() %></option>

                <% }} %>

            </select>

            <div class="modal-footer">
                <button type="submit" class="btn-primary save">Salvar</button>
            </div>

        </form>

    </div>
</div>



<div id="modalExcluir" class="modal">
    <div class="modal-content" style="display: flex; flex-direction: column; gap: 1rem">

        <div>
            <h3>Deseja excluir este professor?</h3>
            <p>Esta ação não poderá ser desfeita.</p>
        </div>

        <div class="modal-footer">
            <a id="btnConfirmarExcluir" class="btn-primary delete">Excluir</a>
            <button onclick="fecharModalExcluir()" class="btn-primary cancel">Cancelar</button>
        </div>

    </div>
</div>