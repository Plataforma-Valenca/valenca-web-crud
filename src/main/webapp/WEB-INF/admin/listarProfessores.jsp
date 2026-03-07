<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.example.projetodiogo.model.DTO.ProfessorConsultaDTO" %>
<%@ page import="org.example.projetodiogo.model.Disciplina" %>
<%@ page import="java.util.ArrayList" %>

<%
    ArrayList<ProfessorConsultaDTO> professoresList =
            (ArrayList<ProfessorConsultaDTO>) request.getAttribute("professoresList");

    ArrayList<Disciplina> disciplinas =
            (ArrayList<Disciplina>) request.getAttribute("disciplinas");

    String busca = request.getAttribute("busca") != null
            ? request.getAttribute("busca").toString()
            : "";
%>

<!DOCTYPE html>
<html>
<head>

    <title>Professores</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/css/tabelaSistema.css">

    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">

</head>

<body>

<jsp:include page="/WEB-INF/views/componentes/sidebarAdm.jsp"/>

<div class="topo">
    <h1>Professores</h1>
    <button class="btn" onclick="abrirModal()">+ Cadastrar</button>
</div>

<form method="get"
      action="${pageContext.request.contextPath}/admin/verProfessores"
      class="busca">

    <input type="text"
           name="busca"
           placeholder="Buscar professor"
           value="<%= busca %>">

    <button class="btn">Buscar</button>

</form>

<div class="tabela">

    <div class="table-header">
        <span>Nome</span>
        <span>Email</span>
        <span>CPF</span>
        <span>Disciplina</span>
    </div>

    <%
        if (professoresList != null && !professoresList.isEmpty()) {

            for (ProfessorConsultaDTO p : professoresList) {
    %>

    <div class="table-row">

        <span><%= p.getNome() %></span>

        <span><%= p.getEmail() %></span>

        <span><%= p.getCpf() %></span>

        <span>
    <%= p.getDisciplina() %>
</span>

    </div>

    <%
        }
    } else {
    %>

    <div class="empty">Nenhum professor encontrado.</div>

    <%
        }
    %>

</div>


<div id="modalCadastro" class="modal">

    <div class="modal-conteudo">

        <h2>Cadastrar Professor</h2>

        <form method="post"
              action="${pageContext.request.contextPath}/admin/cadastrarProfessor">

            <input type="text"
                   name="nome"
                   placeholder="Nome">

            <input type="email"
                   name="email"
                   placeholder="Email">

            <input type="text"
                   name="cpf"
                   placeholder="CPF">

            <select name="idDisciplina">

                <option value="">Selecione a disciplina</option>

                <%
                    if(disciplinas != null){
                        for(Disciplina d : disciplinas){
                %>

                <option value="<%= d.getId() %>">
                    <%= d.getNome() %>
                </option>

                <%
                        }
                    }
                %>

            </select>

            <button type="submit" class="btn">
                Cadastrar
            </button>

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