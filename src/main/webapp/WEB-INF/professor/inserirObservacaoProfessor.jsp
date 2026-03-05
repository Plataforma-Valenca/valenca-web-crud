<%@ page import="org.example.projetodiogo.model.Usuario" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/modalObsProfessor.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">

<div id="modal-obs-bg">

    <div id="add-obs-modal">

        <div id="add-obs-title">
            <h2>Adicionar observação</h2>
            <i class="fa-solid fa-x"  id="exit-btn" onclick="fecharModalObs()"></i>
        </div>

        <form method="post"
              action="<%= request.getContextPath() %>/professor/inserirObservacao">

            <input type="hidden" name="idAluno" value="${param.idAluno}">

            <div id="inside-forms">

                <input type="date" name="data">

                <textarea name="descricao"
                          id="form-descricao"
                          placeholder="Descrição"></textarea>

            </div>

            <div id="inside-buttons">
                <button type="submit">Adicionar</button>
            </div>

        </form>

    </div>

</div>