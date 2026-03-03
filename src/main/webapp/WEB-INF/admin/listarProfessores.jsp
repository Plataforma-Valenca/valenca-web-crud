<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.projetodiogo.model.Usuario" %>
<%@ page import="org.example.projetodiogo.model.DTO.ProfessorConsultaDTO" %>
<%@ page import="java.util.ArrayList" %>

<%
    ArrayList<ProfessorConsultaDTO> professoresList = (ArrayList<ProfessorConsultaDTO>) request.getAttribute("professoresList");

    String busca = request.getAttribute("busca") != null
            ? request.getAttribute("busca").toString()
            : "";
%>

<!DOCTYPE html>
<html>
<head>
    <title>Professores</title>

    <style>
        * { margin:0; padding:0; box-sizing:border-box; }

        body {
            font-family: 'Segoe UI', Arial;
            background:#f1f1f1;
            margin-left:22vw;
            width:78vw;
            padding:60px 40px;
        }

        .topo {
            display:flex;
            justify-content:space-between;
            margin-bottom:20px;
        }

        h1 { font-size:26px; }

        .busca {
            display:flex;
            gap:10px;
            margin-bottom:25px;
        }

        .busca input {
            padding:8px;
            border-radius:6px;
            border:1px solid #ccc;
        }

        .btn {
            background:#3d6f66;
            color:white;
            border:none;
            padding:8px 14px;
            border-radius:6px;
            cursor:pointer;
        }

        .tabela {
            width: 100%;
            border-collapse: collapse;
            background: white;
            border-radius: 10px;
            overflow: hidden;
            box-shadow: 0 3px 10px rgba(0,0,0,0.05);
        }

        .tabela th {
            text-align: left;
            padding: 14px;
            font-size: 13px;
            color: #777;
            background: #f8f8f8;
        }

        .tabela td {
            padding: 16px;
            border-top: 1px solid #eee;
        }

        .tabela tr:hover {
            background: #fafafa;
        }

        .empty {
            text-align:center;
            margin-top:30px;
            color:#888;
        }

        /* Estilo do modal */
        #modalCadastro {
            display:none;
            position:fixed;
            top:0;
            left:0;
            width:100%;
            height:100%;
            background: rgba(0,0,0,0.5);
            justify-content:center;
            align-items:center;
        }

        #modalCadastro .modal-conteudo {
            background:white;
            padding:20px;
            border-radius:10px;
            width:400px;
            position:relative;
        }

        #modalCadastro .modal-conteudo input {
            width:100%;
            margin-bottom:10px;
            padding:8px;
        }

        #modalCadastro .fechar {
            position:absolute;
            top:10px;
            right:10px;
            background:none;
            border:none;
            font-size:18px;
            cursor:pointer;
        }
    </style>
</head>

<body>

<jsp:include page="/WEB-INF/views/componentes/sidebarAdm.jsp"/>
<div class="topo">
    <h1>Professores</h1>
    <button class="btn" onclick="abrirModal()">+ Cadastrar</button>
</div>

<form method="get" action="${pageContext.request.contextPath}/admin/verProfessores" class="busca">
    <input type="text" name="busca" placeholder="Nome ou email" value="<%= busca %>">
    <button class="btn">Buscar</button>
</form>

<table class="tabela">
    <thead>
    <tr>
        <th>Nome</th>
        <th>Email</th>
        <th>CPF</th>
        <th>Disciplina</th>
    </tr>
    </thead>
    <tbody>
    <%
        if (professoresList != null && !professoresList.isEmpty()) {
            for (ProfessorConsultaDTO p : professoresList) {
    %>
    <tr>
        <td><%= p.getNome() %></td>
        <td><%= p.getEmail() %></td>
        <td><%= p.getCpf() %></td>
        <td><%= p.getDisciplina() %></td>
    </tr>
    <%
        }
    } else {
    %>
    <tr>
        <td colspan="4" class="empty">Nenhum professor encontrado.</td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>

<!-- Modal de Cadastro -->
<div id="modalCadastro">
    <div class="modal-conteudo">
        <h2>Cadastrar Professor</h2>
        <form method="post" action="${pageContext.request.contextPath}/admin/cadastrarProfessor">
            <input type="text" name="nome" placeholder="Nome">
            <input type="email" name="email" placeholder="Email">
            <input type="text" name="cpf" placeholder="CPF">
            <button type="submit" class="btn">Cadastrar</button>
        </form>
        <button class="fechar" onclick="fecharModal()">&times;</button>
    </div>
</div>

<script>
    function abrirModal() {
        document.getElementById("modalCadastro").style.display = "flex";
    }
    function fecharModal() {
        document.getElementById("modalCadastro").style.display = "none";
    }
</script>

</body>
</html>