<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.projetodiogo.model.Usuario" %>

<%
    List<Usuario> lista = (List<Usuario>) request.getAttribute("listaProfessores");

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

        .table-header {
            display:grid;
            grid-template-columns: 2fr 2fr 1fr;
            padding:10px;
            color:#777;
            font-size:13px;
        }

        .table-row {
            display:grid;
            grid-template-columns: 2fr 2fr 1fr;
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

<div class="table-header">
    <span>Nome</span>
    <span>Email</span>
    <span>CPF</span>
</div>

<%
    if (lista != null && !lista.isEmpty()) {
        for (Usuario p : lista) {
%>
<div class="table-row">
    <span><%= p.getNome() %></span>
    <span><%= p.getEmail() %></span>
    <span><%= p.getCpf() %></span>
</div>
<%
    }
} else {
%>
<div class="empty">Nenhum professor encontrado.</div>
<%
    }
%>

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