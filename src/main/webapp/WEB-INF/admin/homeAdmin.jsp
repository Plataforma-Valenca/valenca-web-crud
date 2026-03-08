<%@ page import="org.example.projetodiogo.model.Usuario" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");

    String nomeUsuario = (usuario != null) ? usuario.getNome() : "Usuário";

    String tipoUsuario = (usuario != null) ?
            usuario.getTipoUsuario().substring(0,1).toUpperCase() +
                    usuario.getTipoUsuario().substring(1).toLowerCase() : "--";
%>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Colégio Valença</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/home.css">
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/assets/img/icone-colegio-valenca.svg">
</head>

<body>

<jsp:include page="/WEB-INF/views/componentes/sidebarAdm.jsp">
    <jsp:param name="activePage" value="home"/>
</jsp:include>

<div class="page-content">

    <header class="header-home">

        <div class="header-profile" id="profileButton">

            <img src="${pageContext.request.contextPath}/assets/img/icon-profile.svg" height="50">

            <div class="header-profile-infos">
                <b><%= nomeUsuario %></b>
                <p><%= tipoUsuario %></p>
            </div>

            <div id="popupMenu" class="popup-menu">
                <a href="${pageContext.request.contextPath}/logout">
                    Sair da conta
                    <img src="${pageContext.request.contextPath}/assets/img/icon-logout.svg" class="btn-logout">
                </a>
            </div>

        </div>

    </header>

    <main class="main-home">
        <div class="main-home-title">
            <h1>Olá, o que você procura?</h1>
        </div>
    </main>

</div>

<script>

    const profileButton = document.getElementById("profileButton");
    const popup = document.getElementById("popupMenu");

    profileButton.addEventListener("click", function(event){
        event.stopPropagation();

        if(popup.style.display === "block"){
            popup.style.display = "none";
        }else{
            popup.style.display = "block";
        }
    });

    document.addEventListener("click", function(){
        popup.style.display = "none";
    });

</script>

</body>
</html>