<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.example.projetodiogo.model.AlunoConsultaDTO" %>
<%@ page import="java.util.List" %>

<%
    List<AlunoConsultaDTO> alunosList =
            (List<AlunoConsultaDTO>) request.getAttribute("alunosList");

    String busca = request.getAttribute("busca") != null
            ? request.getAttribute("busca").toString()
            : "";
%>

<!DOCTYPE html>
<html>
<head>
    <title>Alunos</title>

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

        .table-header {
            display:grid;
            grid-template-columns: 2fr 1fr 1fr 2fr;
            padding:10px;
            color:#777;
            font-size:13px;
        }

        .table-row {
            display:grid;
            grid-template-columns: 2fr 1fr 1fr 2fr;
            background:white;
            padding:18px;
            border-radius:10px;
            margin-bottom:12px;
            box-shadow:0 3px 10px rgba(0,0,0,0.05);
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

        #modalCadastro .modal-conteudo input,
        #modalCadastro .modal-conteudo select {
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

        #modalCadastro .modal-actions {
            display:flex;
            justify-content:space-between;
            margin-top:10px;
        }
    </style>
</head>

<body>

<jsp:include page="/WEB-INF/views/componentes/sidebarAdm.jsp"/>
<div class="topo">
    <h1>Alunos</h1>
    <button class="btn" onclick="abrirModal()">+ Cadastrar</button>
</div>

<form method="get" action="${pageContext.request.contextPath}/admin/verAlunos" class="busca">
    <input type="text" name="busca" placeholder="Buscar por cpf" value="<%= busca %>">
    <button class="btn">Buscar</button>
</form>

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
    <span><%= aluno.getNome() %></span>
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

<!-- Modal de Cadastro -->
<div id="modalCadastro">
    <div class="modal-conteudo">
        <h2>Cadastrar Aluno</h2>
        <form method="post" action="${pageContext.request.contextPath}/admin/inserirAluno">
            <input type="text" name="nome" placeholder="Nome" required>
            <input type="email" name="email" placeholder="Email" required>
            <input type="password" name="senha" placeholder="Senha" required>
            <select name="turma" required>
                <option value="">Selecione uma turma</option>
                <c:forEach var="t" items="${turmas}">
                    <option value="${t.id}">${t.nome}</option>
                </c:forEach>
            </select>
            <div class="modal-actions">
                <button type="button" onclick="fecharModal()">Cancelar</button>
                <button class="btn" type="submit">Cadastrar</button>
            </div>
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