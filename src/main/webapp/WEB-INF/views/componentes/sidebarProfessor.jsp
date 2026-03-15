<%--
  Created by IntelliJ IDEA.
  User: emanuellymeso-ieg
  Date: 02/03/2026
  Time: 22:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="org.example.projetodiogo.model.Usuario" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/sidebar.css">

<%
    String activePage = request.getParameter("activePage");
    Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
    String nomeUsuario = usuario.getNome();
    String tipoUsuario = usuario.getTipoUsuario().substring(0, 1).toUpperCase() + usuario.getTipoUsuario().substring(1);
%>

<aside class="sidebar">
    <div class="sidebar-valenca-logo">
        <div class="sidebar-valenca-logo-content">
            <img src="${pageContext.request.contextPath}/assets/img/icone-colegio-valenca.svg" height="30">
            <h3>Colégio Valença</h3>
        </div>

    </div>

    <div class="sidebar-top-box">
        <ul class="sidebar-tabs-menu">
            <li class="<%= "home".equals(activePage) ? "active" : "" %> menu-items-tab">
                <a href="${pageContext.request.contextPath}/professor/home">
                    <img src="${pageContext.request.contextPath}/assets/img/sidebar-icon-home.svg">
                    Home
                </a>
            </li>

            <li class="<%= "turmas".equals(activePage) ? "active" : "" %> menu-items-tab" >
                <a href="${pageContext.request.contextPath}/professor/verTurmas">
                    <img src="${pageContext.request.contextPath}/assets/img/sidebar-icon-aluno.svg">
                    Turmas
                </a>
            </li>

            <li class="<%= "dashboards".equals(activePage) ? "active" : "" %> menu-items-tab">
                <a href="${pageContext.request.contextPath}/professor/dashboard">
                    <img src="${pageContext.request.contextPath}/assets/img/sidebar-icon-dashboards.svg">
                    Dashboards
                </a>
            </li>
        </ul>
    </div>

    <div class="sidebar-bottom-box">
        <img src="${pageContext.request.contextPath}/assets/img/sidebar-main-image.svg">
    </div>

</aside>
