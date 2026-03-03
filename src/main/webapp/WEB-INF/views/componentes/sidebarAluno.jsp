<%@ page import="org.example.projetodiogo.model.Usuario" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/sidebarAluno.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">

<%
    String activePage = request.getParameter("activePage");
    Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
    String nomeUsuario = usuario.getNome();
    String tipoUsuario = usuario.getTipoUsuario().substring(0, 1).toUpperCase() + usuario.getTipoUsuario().substring(1);
%>

<div id="page">
    <aside id="aside">

        <!-- LOGO -->
        <div class="aside-content">

            <div class="box-info">
                <div id="logo-colegio">
                    <img src="${pageContext.request.contextPath}/assets/img/logo-colegio-barao.png" height="70">
                </div>

                <div class="box-info-2">
                    <div class="user-info">
                        <div class="user-photo">
                            <img src="${pageContext.request.contextPath}/assets/img/icon-avatar.png" height="40">
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
                    <li class="<%= "disciplinas".equals(activePage) ? "active" : "" %>">
                        <a href="${pageContext.request.contextPath}/aluno/VerDisciplinas">
                            <i class="fa-solid fa-book"></i>
                            <span>Disciplinas</span>
                        </a>
                    </li>

                    <li class="<%= "boletim".equals(activePage) ? "active" : "" %>">
                        <a href="${pageContext.request.contextPath}/aluno/VerBoletim">
                            <i class="fa-solid fa-newspaper"></i>
                            <span>Boletim</span>
                        </a>
                    </li>
                </ul>

                <div id="btn-logout">
                    <img src="${pageContext.request.contextPath}/assets/img/btn_logout.png" height="40">
                </div>
            </div>

        </div>

    </aside>
</div>