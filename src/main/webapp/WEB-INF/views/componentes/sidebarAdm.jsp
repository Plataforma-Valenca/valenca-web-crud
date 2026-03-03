<%@ page import="org.example.projetodiogo.model.Usuario" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/sidebarAdm.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">

<%
    String activePage = request.getParameter("activePage");

    Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");

    String nomeUsuario = usuario != null ? usuario.getNome() : "Usuário";

    String tipoUsuario = "";
    if (usuario != null && usuario.getTipoUsuario() != null) {
        tipoUsuario = usuario.getTipoUsuario().substring(0,1).toUpperCase()
                + usuario.getTipoUsuario().substring(1);
    }
%>

<div id="page">
    <aside id="aside">
        <div class="aside-content">

            <!-- LOGO -->
            <div class="box-info">

                <div id="logo-colegio">
                    <!-- 🔥 COLOQUE SUA LOGO AQUI -->
                    <img src="${pageContext.request.contextPath}/assets/img/logoCB.svg" height="70">
                </div>

                <!-- USUÁRIO -->
                <div class="box-info-2">
                    <div class="user-info">

                        <div class="user-photo">
                            <!-- 🔥 COLOQUE O AVATAR AQUI -->
                            <img src="${pageContext.request.contextPath}/assets/img/icon-avatar.jpg" height="40">
                        </div>

                        <div class="user-info-names">
                            <span id="user-name">
                                <b><%= nomeUsuario %></b>
                            </span>
                            <span id="user-description">
                                <%= tipoUsuario %>
                            </span>
                        </div>

                    </div>
                </div>

            </div>

            <!-- MENU -->
            <div class="box-tabs">
                <ul class="menu-tabs">

                    <li class="<%= "listarAluno".equals(activePage) ? "active" : "" %>">
                        <a href="${pageContext.request.contextPath}/admin/verAlunos">
                            <i class="fa-solid fa-user-graduate"></i> <!-- pode trocar -->
                            <span>Alunos</span>
                        </a>
                    </li>

                    <li class="<%= "listarProfessor".equals(activePage) ? "active" : "" %>">
                        <a href="${pageContext.request.contextPath}/admin/verProfessores">
                            <i class="fa-solid fa-chalkboard-user"></i>
                            <span>Professores</span>
                        </a>
                    </li>
                    <li class="<%= "listarDisciplina".equals(activePage) ? "active" : "" %>">
                        <a href="${pageContext.request.contextPath}/admin/verDisciplinas">
                            <i class="fa-solid fa-book"></i>
                            <span>Disciplinas</span>
                        </a>
                    </li>

                </ul>
            </div>

        </div>
    </aside>
</div>