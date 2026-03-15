<%@ page import="org.example.projetodiogo.model.Boletim" %>
<%@ page import="java.util.List" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/modal.css">

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    Integer idAluno = (Integer) request.getAttribute("idAluno");
    Integer idProfessor = (Integer) request.getAttribute("idProfessor");
%>


<div id="modalNotas" class="modal">
    <div class="modal-content">

        <div class="modal-header">
            <h2>Lançar Notas</h2>
            <span class="close-modal" onclick="fecharModalNotas()">&times;</span>
        </div>

        <form action="${pageContext.request.contextPath}/professor/lancarNotas" method="post">
            <input type="hidden" name="idAluno" id="idAlunoNotas" value="<%= idAluno %>">

            <%
                List<Boletim> boletimList = (List<Boletim>) request.getAttribute("boletimList");
                if (boletimList != null) {
                    for (Boletim b : boletimList) {
            %>
            <div class="form">
                <label> N1:</label>
                <input type="number" step="0.1" name="n1_<%= b.getIdDisciplina() %>" value="<%= b.getMedia1() != null ? b.getMedia1() : "" %>" required>

                <label> N2:</label>
                <input type="number" step="0.1" name="n2_<%= b.getIdDisciplina() %>" value="<%= b.getMedia2() != null ? b.getMedia2() : "" %>" required>
            </div>
            <%
                    }
                }
            %>

            <div class="modal-footer">
                <button type="submit" class="btn-primary save">Salvar</button>
            </div>
        </form>

    </div>
</div>



<div id="modalObservacao" class="modal">
    <div class="modal-content">

        <div class="modal-header">
            <h2>Nova Observação</h2>
            <span class="close-modal" onclick="fecharModalObservacao()">&times;</span>
        </div>

        <form action="${pageContext.request.contextPath}/professor/adicionarObservacao"
              method="post">

            <input type="hidden" name="idAluno" id="obsIdAluno" value="<%= idAluno %>">
            <input type="hidden" name="idProfessor" id="obsIdProfessor" value="<%= idProfessor %>">

            <div class="form">
                <textarea name="descricao" placeholder="Digite a observação..." required></textarea>
            </div>

            <div class="modal-footer">
                <button type="submit" class="btn-primary save">Salvar</button>
            </div>

        </form>

    </div>
</div>
