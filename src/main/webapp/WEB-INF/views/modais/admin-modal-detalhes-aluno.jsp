<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/modal.css">

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- MODAL OBSERVAÇÃO -->

<div id="modalObs" class="modal">

    <div class="modal-content">

        <h3>Adicionar Observação</h3>

        <form action="${pageContext.request.contextPath}/admin/adicionarObservacao" method="post">

            <input type="hidden" name="idAluno" value="<%= aluno.getIdAluno() %>">

            <label>Professor</label>

            <select name="idProfessor" style="width:100%; padding:8px;" required>

                <option value="">Selecione um professor</option>

                <%

                    if(professores != null){

                        for(ProfessorConsultaDTO prof : professores){

                %>

                <option value="<%= prof.getIdProfessor() %>">
                    <%= prof.getNome() %>
                </option>

                <%

                        }

                    }

                %>

            </select>

            <br><br>

            <label>Observação</label>

            <textarea name="descricao"
                      placeholder="Digite a observação..."
                      style="width:100%; height:120px; padding:10px;"
                      required></textarea>

            <br><br>

            <div style="display:flex; justify-content:flex-end; gap:10px">

                <button type="submit" class="btn-acao">
                    Salvar
                </button>

                <button type="button" onclick="fecharModalObs()">
                    Cancelar
                </button>

            </div>

        </form>

    </div>

</div>