<%@ page import="org.example.projetodiogo.model.Boletim" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/modal.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/loading-button.css">
<script src="${pageContext.request.contextPath}/assets/js/loading-button.js" defer></script>

<%
    Integer idAluno = Integer.parseInt(request.getParameter("idAluno"));
    Integer idProfessor = (Integer) request.getAttribute("idProfessor");
    Integer idTurma     = (Integer) request.getAttribute("idTurma");
    String  nomeTurma   = (String)  request.getAttribute("nomeTurma");
%>

<!-- MODAL: LANÇAR NOTAS -->
<div id="modalNotas" class="modal">
    <div class="modal-content">

        <div class="modal-header">
            <h2>Lançar Notas</h2>
            <span class="close-modal" onclick="fecharModalNotas()">&times;</span>
        </div>

        <form action="${pageContext.request.contextPath}/professor/inserirNota" method="post">
            <input type="hidden" name="idAluno"   value="<%= idAluno %>">
            <input type="hidden" name="idTurma"   value="<%= idTurma %>">
            <input type="hidden" name="nomeTurma" value="<%= nomeTurma %>">

            <%
                List<Boletim> boletimList = (List<Boletim>) request.getAttribute("boletimList");
                if (boletimList != null && !boletimList.isEmpty()) {
                    for (Boletim b : boletimList) {
            %>
            <div class="form">
                <label><%= b.getNomeDisciplina() %></label>

                <label>N1:</label>
                <input type="number" step="0.1" min="0" max="10"
                       name="n1_<%= b.getIdDisciplina() %>"
                       value="<%= b.getMedia1() != null ? b.getMedia1() : "" %>">

                <label>N2:</label>
                <input type="number" step="0.1" min="0" max="10"
                       name="n2_<%= b.getIdDisciplina() %>"
                       value="<%= b.getMedia2() != null ? b.getMedia2() : "" %>">
            </div>
            <%
                }
            } else {
            %>
            <div style="text-align:center; padding:20px; color:#999;">
                Nenhuma disciplina encontrada.
            </div>
            <% } %>

            <div class="modal-footer">
                <button type="submit" class="btn-primary save btn-loading">Salvar</button>
            </div>
        </form>

    </div>
</div>

<!-- MODAL: NOVA OBSERVAÇÃO -->
<div id="modalObservacao" class="modal">
    <div class="modal-content">

        <div class="modal-header">
            <h2>Nova Observação</h2>
            <span class="close-modal" onclick="fecharModalObservacao()">&times;</span>
        </div>

        <form action="${pageContext.request.contextPath}/professor/inserirObservacao" method="post">
            <input type="hidden" name="idAluno" value="<%= idAluno %>">
<%--            <input type="hidden" name="idProfessor" value="<%= idProfessor %>">--%>
<%--            <input type="hidden" name="idTurma" value="<%= idTurma %>">--%>
<%--            <input type="hidden" name="nomeTurma" value="<%= nomeTurma %>">--%>

            <div class="form">
                <textarea name="descricao" placeholder="Digite a observação..." required></textarea>
            </div>

            <div class="modal-footer">
                <button type="submit" class="btn-primary save btn-loading">Salvar</button>
            </div>
        </form>

    </div>
</div>

<!-- MODAL: EXCLUIR OBSERVAÇÃO -->
<div id="modalExcluirObservacao" class="modal">
    <div class="modal-content">

        <div class="modal-header">
            <h2>Excluir Observação</h2>
            <span class="close-modal" onclick="fecharModalExcluirObservacao()">&times;</span>
        </div>

        <form action="${pageContext.request.contextPath}/professor/excluirObservacao" method="post">
            <input type="hidden" name="idObservacao" id="idObservacaoExcluir">
            <input type="hidden" name="idAluno"      value="<%= idAluno %>">
            <input type="hidden" name="idTurma"      value="<%= idTurma %>">
            <input type="hidden" name="nomeTurma"    value="<%= nomeTurma %>">

            <p>Tem certeza que deseja excluir esta observação?</p>

            <div class="modal-footer">
                <button type="button" class="btn-secondary" onclick="fecharModalExcluirObservacao()">Cancelar</button>
                <button type="submit" class="btn-primary save btn-loading">Excluir</button>
            </div>
        </form>

    </div>
</div>

<!-- MODAL: EDITAR NOTA -->
<div id="modalEditarNota" class="modal">
    <div class="modal-content">

        <div class="modal-header">
            <h2>Editar Nota</h2>
            <span class="close-modal" onclick="fecharModalEditarNota()">&times;</span>
        </div>

        <form action="${pageContext.request.contextPath}/professor/editarNota" method="post">
            <input type="hidden" name="idNota"    id="editarIdNota">
            <input type="hidden" name="idAluno"   value="<%= idAluno %>">
            <input type="hidden" name="idTurma"   value="<%= idTurma %>">
            <input type="hidden" name="nomeTurma" value="<%= nomeTurma %>">

            <div class="form">
                <label>N1:</label>
                <input type="number" step="0.1" min="0" max="10"
                       name="n1" id="editarN1">

                <label>N2:</label>
                <input type="number" step="0.1" min="0" max="10"
                       name="n2" id="editarN2">
            </div>

            <div class="modal-footer">
                <button type="submit" class="btn-primary save btn-loading">Salvar</button>
            </div>
        </form>

    </div>
</div>