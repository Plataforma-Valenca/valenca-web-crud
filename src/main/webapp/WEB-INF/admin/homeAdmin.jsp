<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.example.projetodiogo.model.Usuario" %>

<%
  Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
  String nomeUsuario = usuario != null ? usuario.getNome() : "Usuário";
  String cargoUsuario = usuario != null ? usuario.getTipoUsuario() : "";
%>
<!DOCTYPE html>
<html lang="pt-BR">

<head>

  <meta charset="UTF-8">
  <title>Dashboard</title>

  <style>

    body{
      font-family: Arial;
      background:#f5f5f5;
      margin:0;
    }

    .top-bar{
      display:flex;
      justify-content:flex-end;
      align-items:center;
      padding:20px 40px;
    }

    .usuario{
      display:flex;
      align-items:center;
      gap:10px;
      cursor:pointer;
      position:relative;
    }

    .usuario img{
      width:45px;
      height:45px;
      border-radius:50%;
    }

    .nome{
      font-weight:bold;
      color:#2a2356;
    }

    .cargo{
      font-size:13px;
      color:#777;
    }

    /* POPUP */

    .popup{
      display:none;
      position:absolute;
      top:60px;
      right:0;
      background:white;
      border-radius:10px;
      box-shadow:0 2px 10px rgba(0,0,0,0.15);
      padding:15px;
      width:150px;
    }

    .popup a{
      text-decoration:none;
      color:#333;
      display:block;
      padding:8px;
      border-radius:6px;
    }

    .popup a:hover{
      background:#f0f0f0;
    }

    .titulo{
      text-align:center;
      margin-top:100px;
      font-size:32px;
      color:#2a2356;
    }

  </style>

</head>

<body>
<jsp:include page="/WEB-INF/views/componentes/sidebarAdm.jsp"/>


<div class="top-bar">

  <div class="usuario" onclick="togglePopup()">


    <img src="${pageContext.request.contextPath}/assets/img/personagem.png">

    <div>
      <div class="nome"><%= nomeUsuario %></div>
      <div class="cargo"><%= cargoUsuario %></div>
    </div>

    <div id="popupMenu" class="popup">

      <a href="${pageContext.request.contextPath}/logout">
        Sair
      </a>

    </div>

  </div>

</div>

<h1 class="titulo">
  Olá, o que você procura?
</h1>

<script>

  function togglePopup(){

    let popup = document.getElementById("popupMenu");

    if(popup.style.display === "block"){
      popup.style.display = "none";
    }else{
      popup.style.display = "block";
    }

  }

  window.onclick = function(event){

    if(!event.target.closest(".usuario")){
      document.getElementById("popupMenu").style.display = "none";
    }

  }

</script>

</body>
</html>