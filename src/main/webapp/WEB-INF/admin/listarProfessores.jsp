<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.example.projetodiogo.model.DTO.ProfessorConsultaDTO" %>
<%@ page import="org.example.projetodiogo.model.Disciplina" %>
<%@ page import="java.util.List" %>

<%
    List<ProfessorConsultaDTO> professoresList = (List<ProfessorConsultaDTO>) request.getAttribute("professoresList");
    List<Disciplina> disciplinas = (List<Disciplina>) request.getAttribute("disciplinas");
    String busca = request.getAttribute("busca") != null ? request.getAttribute("busca").toString() : "";
%>

<html>
<head>
    <title>Colégio Valença</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/modal.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/page-grid.css">

    <link rel="icon" type="image/x-icon"
          href="${pageContext.request.contextPath}/assets/img/icone-colegio-valenca.svg">
</head>

<body>

<jsp:include page="/WEB-INF/views/componentes/sidebarAdm.jsp">
    <jsp:param name="activePage" value="professores"/>
</jsp:include>

<div class="page-content">

    <header class="page-grid-header">
        <h1 class="page-grid-header-title">Gerenciar Professores</h1>

        <button class="btn-primary" onclick="abrirModalCadastro()">
            Cadastrar
        </button>
    </header>

    <main class="page-grid-main" style="gap: 5vh; padding-bottom: 80px;">

        <div class="top-box-page-grid-main">

            <form action="${pageContext.request.contextPath}/admin/verProfessores"
                  method="get"
                  class="form-busca">

                <div class="form-control">

                    <h5>Buscar professor</h5>

                    <div class="form-control-action input-primary">

                        <input type="text"
                               name="busca"
                               placeholder="Nome ou CPF"
                               value="<%= busca %>">

                        <button type="submit"
                                class="btn btn-primary">
                            Buscar
                        </button>

                    </div>

                </div>

            </form>

        </div>


        <div class="bottom-box-page-grid-main">

            <div class="page-grid-main-content">

                <div class="table-list-row">

                    <div class="header-table-list-row" style="display:flex">

                        <h4 style="flex:2;">Nome</h4>
                        <h4 style="flex:2;">Email</h4>
                        <h4 style="flex:1.5;">Username</h4>
                        <h4 style="flex:1.5;">Disciplina</h4>

                    </div>


                    <div class="body-table-list-row">

                        <%
                            if (professoresList != null && !professoresList.isEmpty()) {
                                for (ProfessorConsultaDTO p : professoresList) {
                        %>

                        <div class="itens-per-table"
                             style="display:flex; align-items:center">

                            <div style="flex:2;"><%= p.getNome() %></div>
                            <div style="flex:2;"><%= p.getEmail() %></div>
                            <div style="flex:1.5;"><%= p.getUsername() %></div>
                            <div style="flex:1.5;"><%= p.getDisciplina() %></div>

                            <div style="display:flex; gap:10px;">

                                <i class="fa-solid fa-pen"
                                   style="cursor:pointer;"
                                   onclick="abrirModalEditar(
                                           '<%= p.getIdUsuario() %>',
                                           '<%= p.getIdUsuario() %>',
                                           '<%= p.getNome() %>',
                                           '<%= p.getEmail() %>',
                                           '<%= p.getCpf() %>',
                                           '<%= p.getUsername() %>',
                                           '<%= p.getDisciplina() %>'
                                           )">
                                </i>

                                <i class="fa-solid fa-trash"
                                   style="color: var(--color-error); cursor:pointer;"
                                   onclick="abrirModalExcluir('<%= p.getIdUsuario() %>', '<%= p.getIdProfessor() %>')">
                                </i>

                            </div>

                        </div>

                        <%
                            }
                        } else {
                        %>

                        <div style="text-align:center; padding:40px; color:#999;">
                            Nenhum professor encontrado.
                        </div>

                        <% } %>

                    </div>

                </div>

            </div>

        </div>

    </main>

</div>


<!-- MODAL CADASTRO -->

<div id="modalCadastro" class="modal">

    <div class="modal-content">

        <div class="modal-header">

            <h2>Novo Professor</h2>

            <span class="close-modal"
                  onclick="fecharModalCadastro()">&times;</span>

        </div>


        <form action="${pageContext.request.contextPath}/admin/inserirProfessor"
              method="post">

            <input type="text"
                   name="nome"
                   placeholder="Nome Completo"
                   required>

            <input type="email"
                   name="email"
                   placeholder="E-mail Acadêmico"
                   required>

            <input type="text"
                   name="cpf"
                   placeholder="CPF"
                   required>

            <input type="text"
                   name="senha"
                   placeholder="Senha"
                   required>

            <input type="text"
                   name="username"
                   placeholder="Username"
                   required>

            <input type="text"
                   name="nomeDisciplina"
                   placeholder="Disciplina"
                   required>

            <div class="modal-footer">

                <button type="submit"
                        class="btn-primary">
                    Salvar
                </button>

            </div>

        </form>

    </div>

</div>


<!-- MODAL EDITAR -->

<div id="modalEditar" class="modal">

    <div class="modal-content">

        <div class="modal-header">

            <h2>Editar Professor</h2>

            <span class="close-modal"
                  onclick="fecharModalEditar()">&times;</span>

        </div>


        <form action="${pageContext.request.contextPath}/admin/editarProfessor"
              method="post">

            <input type="hidden"
                   name="idUsuario"
                   id="editarIdUsuario">

            <input type="hidden"
                   name="idProfessor"
                   id="editarIdProfessor">

            <label>Nome</label>
            <input type="text"
                   name="nome"
                   id="editarNome"
                   required>

            <label>Email</label>
            <input type="email"
                   name="email"
                   id="editarEmail"
                   required>

            <label>CPF</label>
            <input type="text"
                   name="cpf"
                   id="editarCpf"
                   required>

            <label>Username</label>
            <input type="text"
                   name="username"
                   id="editarUsername"
                   required>

            <label>Disciplina</label>
            <input type="text"
                   name="nomeDisciplina"
                   id="editarDisciplina"
                   required>

            <div class="modal-footer">

                <button type="submit"
                        class="btn-primary">
                    Salvar
                </button>

            </div>

        </form>

    </div>

</div>


<!-- MODAL EXCLUIR -->

<div id="modalExcluir" class="modal">

    <div class="modal-content">

        <h3>Deseja excluir este professor?</h3>

        <p>Esta ação não poderá ser desfeita.</p>

        <div class="modal-footer">

            <a id="btnConfirmarExcluir"
               class="btn-danger">
                Excluir
            </a>

            <button onclick="fecharModalExcluir()"
                    class="btn-secondary">
                Cancelar
            </button>

        </div>

    </div>

</div>


<script>

    function abrirModalCadastro() {
        document.getElementById("modalCadastro").style.display = "flex";
    }

    function fecharModalCadastro() {
        document.getElementById("modalCadastro").style.display = "none";
    }

    function abrirModalEditar(idUsuario,idProfessor,nome,email,cpf,username,disciplina){

        document.getElementById("modalEditar").style.display="flex";

        document.getElementById("editarIdUsuario").value=idUsuario;
        document.getElementById("editarIdProfessor").value=idProfessor;

        document.getElementById("editarNome").value=nome;
        document.getElementById("editarEmail").value=email;
        document.getElementById("editarCpf").value=cpf;
        document.getElementById("editarUsername").value=username;
        document.getElementById("editarDisciplina").value=disciplina;
    }

    function fecharModalEditar(){
        document.getElementById("modalEditar").style.display="none";
    }

    function abrirModalExcluir(idProfessor, idUsuario) {

        document.getElementById("modalExcluir").style.display="flex";

        document.getElementById("btnConfirmarExcluir").href =
            "${pageContext.request.contextPath}/admin/deletarProfessor?idProfessor="
            + idProfessor + "&idUsuario=" + idUsuario;
    }

    function fecharModalExcluir() {
        document.getElementById("modalExcluir").style.display = "none";
    }

    window.onclick = function(event) {
        if (event.target.className === 'modal') {
            event.target.style.display = "none";
        }
    }

</script>

</body>
</html>