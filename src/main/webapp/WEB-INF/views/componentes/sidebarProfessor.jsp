<%--
  Created by IntelliJ IDEA.
  User: emanuellymeso-ieg
  Date: 02/03/2026
  Time: 22:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="org.example.projetodiogo.model.Usuario" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/sidebarProfessor.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">

<%
    String activePage = request.getParameter("activePage");
    Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
    String nomeUsuario = usuario.getNome();
    String tipoUsuario = usuario.getTipoUsuario().substring(0, 1).toUpperCase() + usuario.getTipoUsuario().substring(1);
%>

<div id="page">
    <aside id="aside">
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

            <div class="box-tabs">
                <ul class="menu-tabs">

                    <li class="<%= "alunos".equals(activePage) ? "active" : "" %>">
                        <a href="${pageContext.request.contextPath}/professor/verAlunos">
                            <i class="fa-solid fa-user-graduate"></i>
                            <span>Alunos</span>
                        </a>
                    </li>

                </ul>
            </div>
            <div id="btn-logout">
                <a href="${pageContext.request.contextPath}/logout"><img src="${pageContext.request.contextPath}/assets/img/btn-logout.svg" height="40"></a>
            </div>

        </div>
    </aside>
</div>
