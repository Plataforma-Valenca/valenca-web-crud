<%--
  Created by IntelliJ IDEA.
  User: emanuellymeso-ieg
  Date: 14/03/2026
  Time: 16:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>404 - Página não encontrada</title>

    <style>

        *{
            margin:0;
            padding:0;
            box-sizing:border-box;
            font-family:Arial, Helvetica, sans-serif;
        }

        body{
            height:100vh;
            display:flex;
            align-items:center;
            justify-content:center;
            background:#f6f6f6;
        }

        .container{
            display:flex;
            align-items:center;
            gap:80px;
            max-width:1000px;
        }

        /* imagem */

        .img404 img{
            width:350px;
            animation: flutuar 3s ease-in-out infinite;
        }

        @keyframes flutuar{
            0%{transform:translateY(0px);}
            50%{transform:translateY(-10px);}
            100%{transform:translateY(0px);}
        }

        /* texto */

        .text404 h1{
            font-size:120px;
            color:#ef476f;
        }

        .text404 h2{
            font-size:32px;
            color:#0f2a32;
            margin-bottom:10px;
        }

        .text404 p{
            color:#777;
            margin-bottom:20px;
        }

        .btn{
            background:#ef476f;
            color:white;
            padding:12px 30px;
            border-radius:8px;
            text-decoration:none;
            font-weight:bold;
            transition:0.3s;
        }

        .btn:hover{
            background:#d63d5f;
        }

    </style>

</head>

<body>

<div class="container">

    <div class="img404">
        <img src="${pageContext.request.contextPath}/assets/img/fio404.png">
    </div>

    <div class="text404">

        <h1>404</h1>

        <h2>Página não encontrada</h2>

        <p>Sentimos muito pelo ocorrido, por favor volte para a página inicial.</p>

        <a class="btn"
           href="${pageContext.request.contextPath}/index.jsp">
            Voltar
        </a>

    </div>

</div>

</body>
</html>
