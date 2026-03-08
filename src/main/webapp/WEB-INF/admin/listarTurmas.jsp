<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.projetodiogo.model.Turma" %>

<%
    List<Turma> turmas = (List<Turma>) request.getAttribute("turmas");
%>

<!DOCTYPE html>
<html>
<head>

    <title>Turmas</title>

    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">

    <style>

        body{
            font-family: Arial;
            background:#f5f5f5;
        }

        .container{
            padding:40px;
        }

        .top-bar{
            display:flex;
            justify-content:space-between;
            align-items:center;
            margin-bottom:30px;
        }

        .btn-cadastrar{
            background:#2c8da7;
            color:white;
            border:none;
            padding:10px 20px;
            border-radius:8px;
            cursor:pointer;
        }

        .grid{
            display:grid;
            grid-template-columns:repeat(3,1fr);
            gap:20px;
        }

        .card{
            background:white;
            padding:25px;
            border-radius:15px;
            display:flex;
            justify-content:space-between;
            align-items:center;
            box-shadow:0 2px 5px rgba(0,0,0,0.1);
        }

        .acoes i{
            margin-left:10px;
            cursor:pointer;
        }

        /* MODAL */

        .modal{
            display:none;
            position:fixed;
            top:0;
            left:0;
            width:100%;
            height:100%;
            background:rgba(0,0,0,0.4);
            justify-content:center;
            align-items:center;
        }

        .modal-content{
            background:white;
            padding:30px;
            border-radius:20px;
            width:350px;
            text-align:center;
        }

        .modal input{
            width:100%;
            padding:10px;
            margin:10px 0;
            border-radius:8px;
            border:1px solid #ccc;
        }

        .btn-salvar{
            background:#8dc6d7;
            border:none;
            padding:10px 20px;
            border-radius:10px;
            cursor:pointer;
        }

        .botoes{
            margin-top:20px;
        }

        .btn-excluir{
            background:red;
            color:white;
            padding:10px 15px;
            border-radius:8px;
            text-decoration:none;
            margin-right:10px;
        }

        .btn-cancelar{
            padding:10px 15px;
        }

        .close{
            float:right;
            cursor:pointer;
        }

    </style>

</head>

<body>

<jsp:include page="/WEB-INF/views/componentes/sidebarAdm.jsp"/>

<div class="container">

    <div class="top-bar">

        <h2>Turmas</h2>

        <button class="btn-cadastrar" onclick="abrirModalCadastro()">
            <i class="fa-solid fa-plus"></i> Cadastrar
        </button>

    </div>

    <div class="grid">

        <% if (turmas != null && !turmas.isEmpty()) {
            for (Turma t : turmas) { %>

        <div class="card">

            <strong><%= t.getNome() %></strong>

            <div class="acoes">

                <a href="${pageContext.request.contextPath}/admin/verAlunosTurma?idTurma=<%= t.getId() %>">
                    <i class="fa-solid fa-arrow-right"></i>
                </a>

                <a href="#" onclick="abrirModalExcluir(<%= t.getId() %>)">
                    <i class="fa-solid fa-trash"></i>
                </a>

            </div>

        </div>

        <%  }
        } else { %>

        <p>Nenhuma turma encontrada.</p>

        <% } %>

    </div>

</div>

<!-- MODAL CADASTRO -->

<div id="modalCadastro" class="modal">

    <div class="modal-content">

        <span class="close" onclick="fecharModalCadastro()">
            <i class="fa-solid fa-xmark"></i>
        </span>

        <h2>Cadastrar turma</h2>

        <form action="${pageContext.request.contextPath}/admin/cadastrarTurma" method="post">

            <input type="text" name="serie" placeholder="Série" required>

            <input type="text" name="letra" placeholder="Letra" required>

            <br><br>

            <button type="submit" class="btn-salvar">
                Salvar
            </button>

        </form>

    </div>

</div>

<!-- MODAL EXCLUIR -->

<div id="modalExcluir" class="modal">

    <div class="modal-content">

        <h3>Tem certeza que deseja excluir?</h3>

        <div class="botoes">

            <a id="btnConfirmarExcluir" class="btn-excluir">
                Excluir
            </a>

            <button onclick="fecharModalExcluir()" class="btn-cancelar">
                Cancelar
            </button>

        </div>

    </div>

</div>

<script>

    function abrirModalCadastro(){
        document.getElementById("modalCadastro").style.display = "flex";
    }

    function fecharModalCadastro(){
        document.getElementById("modalCadastro").style.display = "none";
    }

    function abrirModalExcluir(id){

        document.getElementById("modalExcluir").style.display = "flex";

        document.getElementById("btnConfirmarExcluir").href =
            "${pageContext.request.contextPath}/admin/deletarTurma?id=" + id;
    }

    function fecharModalExcluir(){
        document.getElementById("modalExcluir").style.display = "none";
    }

</script>

</body>
</html>