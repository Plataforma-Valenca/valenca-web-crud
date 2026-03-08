<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.example.projetodiogo.model.DTO.AlunoConsultaDTO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.example.projetodiogo.model.Turma" %>
<%@ page import="java.util.List" %>

<%
    ArrayList<AlunoConsultaDTO> alunosList =
            (ArrayList<AlunoConsultaDTO>) request.getAttribute("alunosList");

    List<Turma> turmasList = (List<Turma>) request.getAttribute("turmasList");

    String busca = request.getAttribute("busca") != null
            ? request.getAttribute("busca").toString()
            : "";
%>

<!DOCTYPE html>
<html>
<head>

    <title>Alunos</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/css/tabelaSistema.css">

</head>

<body>

<jsp:include page="/WEB-INF/views/componentes/sidebarAdm.jsp"/>

<div class="topo">
    <h1>Alunos</h1>
    <button class="btn" onclick="abrirModal()">+ Cadastrar</button>
</div>

<form method="get"
      action="${pageContext.request.contextPath}/admin/verAlunos"
      class="busca">

    <input type="text"
           name="busca"
           placeholder="Buscar por cpf"
           value="<%= busca %>">

    <button class="btn">Buscar</button>

</form>

<div class="tabela">

    <div class="table-header">
        <span>Nome</span>
        <span>Matrícula</span>
        <span>CPF</span>
        <span>Turma</span>
    </div>

    <%
        if (alunosList != null && !alunosList.isEmpty()) {

            for (AlunoConsultaDTO aluno : alunosList) {
    %>

    <div class="table-row">

<span>
<a href="${pageContext.request.contextPath}/admin/verPerfilAluno?matricula=<%= aluno.getMatricula() %>">
<%= aluno.getNome() %>
</a>
</span>

        <span><%= aluno.getMatricula() %></span>
        <span><%= aluno.getCpf() %></span>
        <span><%= aluno.getTurma() %></span>

    </div>

    <%
        }
    } else {
    %>

    <div class="empty">Nenhum aluno encontrado.</div>

    <%
        }
    %>

</div>


<div id="modalCadastro" class="modal">

    <div class="modal-conteudo">

        <h2>Cadastrar Aluno</h2>

        <form method="post"
              action="${pageContext.request.contextPath}/admin/inserirAluno">

            <input type="text" name="cpf" placeholder="CPF" required>

            <input type="password"
                   name="senhaProvisoria"
                   placeholder="Senha Provisória"
                   required>

            <select name="idTurma" required>

                <%
                    for(Turma t : turmasList){
                %>

                <option value="<%= t.getId() %>">
                    <%= t.getNome().substring(0,1).toUpperCase() %>
                </option>

                <%
                    }
                %>

            </select>

            <div class="modal-actions">

                <button type="button" onclick="fecharModal()">Cancelar</button>

                <button class="btn" type="submit">Cadastrar</button>

            </div>

        </form>

        <button class="fechar" onclick="fecharModal()">×</button>

    </div>

</div>

<script>

    function abrirModal(){
        document.getElementById("modalCadastro").style.display="flex";
    }

    function fecharModal(){
        document.getElementById("modalCadastro").style.display="none";
    }

</script>

</body>
</html>