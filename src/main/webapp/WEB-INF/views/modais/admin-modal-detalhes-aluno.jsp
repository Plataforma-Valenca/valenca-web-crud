<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/modal.css">

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.example.projetodiogo.model.DTO.AlunoConsultaDTO" %>
<%@ page import="org.example.projetodiogo.model.DTO.ProfessorConsultaDTO" %>
<%@ page import="org.example.projetodiogo.model.Disciplina" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>

<%
    AlunoConsultaDTO aluno = (AlunoConsultaDTO) request.getAttribute("alunoConsulta");
    List<ProfessorConsultaDTO> professores = (List<ProfessorConsultaDTO>) request.getAttribute("professores");
    ArrayList<Disciplina> disciplinasList = (ArrayList<Disciplina>) request.getAttribute("disciplinasList");
%>

<!-- MODAL ADICIONAR NOTAS -->
<div id="modalNotas" class="modal">
    <div class="modal-content">

        <div class="modal-header">
            <h2>Atualizar Notas</h2>
            <span class="close-modal" onclick="fecharModalNotas()">&times;</span>
        </div>

        <form action="${pageContext.request.contextPath}/admin/atualizarNotas" method="post">

            <input type="hidden" id="notasIdAluno" name="idAluno" value="<%= aluno != null ? aluno.getIdAluno() : "" %>">
            <input type="hidden" id="notasIdDisciplina" name="idDisciplina">

            <div class="form">
                <select id="notasDisciplina" onchange="selecionarDisciplina(this)">
                    <option value="" disabled selected>Selecione a disciplina</option>
                    <%
                        if (disciplinasList != null) {
                            for (Disciplina d : disciplinasList) {
                    %>
                    <option value="<%= d.getId() %>"><%= d.getNome() %></option>
                    <%
                            }
                        }
                    %>
                </select>

                <input type="number" name="n1" id="inputN1" placeholder="N1" step="0.1" min="0" max="10" required>
                <input type="number" name="n2" id="inputN2" placeholder="N2" step="0.1" min="0" max="10" required>
            </div>

            <div class="modal-footer">
                <button type="submit" class="btn-primary save">Salvar</button>
            </div>

        </form>

    </div>
</div>

<!-- MODAL ADICIONAR OBSERVAÇÃO -->
<div id="modalObs" class="modal">
    <div class="modal-content">

        <div class="modal-header">
            <h2>Nova Observação</h2>
            <span class="close-modal" onclick="fecharModalObs()">&times;</span>
        </div>

        <form action="${pageContext.request.contextPath}/admin/inserirObservacao" method="post">

            <input type="hidden" name="idAluno" value="<%= aluno != null ? aluno.getIdAluno() : "" %>">

            <div class="form" style="display: grid; grid-template-columns: 1fr">

                <textarea name="descricao"
                          placeholder="Digite a observação..."
                          style="height:120px; padding:10px;"
                          required></textarea>
            </div>

            <div class="modal-footer">
                <button type="submit" class="btn-primary save">Salvar</button>
            </div>

        </form>

    </div>
</div>