<%@ page import="org.example.projetodiogo.model.Usuario" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/sidebarAdm.css">

<%
    String activePage = request.getParameter("activePage");

    Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");

    String nomeUsuario = usuario != null ? usuario.getNome() : "Usuário";

%>

<div id="page">
    <aside id="aside">
        <div class="aside-content">

            <!-- LOGO -->

            <!-- MENU -->
            <div class="box-tabs">
                <ul class="menu-tabs">

                    <li class="<%= "homeAdmin".equals(activePage) ? "active" : "" %>">
                        <a href="${pageContext.request.contextPath}/admin/homeAdmin">
                            <img src="${pageContext.request.contextPath}/assets/img/homeIcon.png">
                            <span>Home</span>
                        </a>
                    </li>

                    <li class="<%= "listarTurmas".equals(activePage) ? "active" : "" %>">
                        <a href="${pageContext.request.contextPath}/admin/verTurmas">
                            <img src="${pageContext.request.contextPath}/assets/img/alunoIcon.png">

                            <span>Turmas</span>
                        </a>
                    </li>

                    <li class="<%= "listarProfessor".equals(activePage) ? "active" : "" %>">
                        <a href="${pageContext.request.contextPath}/admin/verProfessores">
                            <img src="${pageContext.request.contextPath}/assets/img/professoresIcon.png">

                            <span>Professores</span>
                        </a>
                    </li>
                    <li class="<%= "listarDisciplinas".equals(activePage) ? "active" : "" %>">
                        <a href="${pageContext.request.contextPath}/admin/verDisciplinasResumo">
                            <img src="${pageContext.request.contextPath}/assets/img/disciplinasIcon.png">

                            <span>Disciplinas</span>
                        </a>
                    </li>
                </ul>
            </div>
          <img src="${pageContext.request.contextPath}/assets/img/salaAulaImagem.png">

        </div>
    </aside>
</div>