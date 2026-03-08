<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.projetodiogo.model.DTO.DisciplinasResumoDTO" %>

<%
    List<DisciplinasResumoDTO> disciplinasList =
            (List<DisciplinasResumoDTO>) request.getAttribute("resumoList");
%>

<!DOCTYPE html>
<html>

<head>

    <title>Disciplinas</title>

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

        .info{
            display:flex;
            flex-direction:column;
        }

        .professor{
            font-size:14px;
            color:#666;
        }

        .turmas{
            font-size:13px;
            color:#999;
        }

        .acoes i{
            margin-left:10px;
            cursor:pointer;
        }

    </style>

</head>

<body>

<jsp:include page="/WEB-INF/views/componentes/sidebarAdm.jsp"/>

<div class="container">

    <div class="top-bar">

        <h2>Disciplinas</h2>

        <button class="btn-cadastrar">
            <i class="fa-solid fa-plus"></i> Cadastrar
        </button>

    </div>

    <div class="grid">

        <% if (disciplinasList != null && !disciplinasList.isEmpty()) {
            for (DisciplinasResumoDTO d : disciplinasList) { %>

        <div class="card">

            <div class="info">

                <strong><%= d.getNomeFormatado() %></strong>

                <div class="professor">
                    Professor: <%= d.getNomeProfessor() %>
                </div>

                <div class="turmas">
                    Turmas: <%= d.getQuantidadeTurmas() %>
                </div>

            </div>

            <div class="acoes">

                <i class="fa-solid fa-pen"></i>

                <i class="fa-solid fa-trash"></i>

            </div>

        </div>

        <%  }
        } else { %>

        <p>Nenhuma disciplina encontrada.</p>

        <% } %>

    </div>

</div>

</body>
</html>