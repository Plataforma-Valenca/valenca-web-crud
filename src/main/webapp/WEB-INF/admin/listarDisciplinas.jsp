<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.projetodiogo.model.Disciplina" %>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Disciplinas</title>
</head>

<body>

<jsp:include page="/WEB-INF/views/componentes/sidebarAdm.jsp"/>

<h1>Disciplinas cadastradas</h1>

<div class="cards-grid">
    <%
        List<Disciplina> disciplinasList =
                (List<Disciplina>) request.getAttribute("disciplinasList");

        if (disciplinasList != null && !disciplinasList.isEmpty()) {
            for (Disciplina d : disciplinasList) {
    %>

    <div class="card">
        <div class="card-body">
            <div class="card-titulo">
                <%= d.getNome() %>
            </div>
        </div>

        <div class="card-footer">
            <span class="icon-btn">&#9998;</span>
        </div>
    </div>

    <%
        }
    } else {
    %>

    <div class="empty">Nenhuma disciplina encontrada.</div>

    <%
        }
    %>
</div>

</body>
</html>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Segoe UI', Arial, sans-serif;
            background-color: #f0f2f2;
            min-height: 100vh;

            margin-left: 22vw;
            width: 78vw;

            display: flex;
            flex-direction: column;
            align-items: center;
            padding: 60px 40px;
        }

        h1 {
            font-size: 1.7rem;
            font-weight: 600;
            color: #2e2e2e;
            margin-bottom: 48px;
            text-align: center;
        }

        .cards-grid {
            display: grid;
            grid-template-columns: repeat(3, 1fr);
            gap: 24px;
            width: 100%;
            max-width: 900px;
        }

        .card {
            background-color: #d6e5e3;
            border-radius: 16px;
            padding: 28px 24px 20px;
            display: flex;
            flex-direction: column;
            justify-content: space-between;
            min-height: 170px;
            text-decoration: none;
            transition: transform 0.2s ease, box-shadow 0.2s ease;
            cursor: pointer;
        }

        .card:hover {
            transform: translateY(-4px);
            box-shadow: 0 8px 20px rgba(0, 0, 0, 0.1);
        }

        .card-body {
            flex: 1;
        }

        .card-titulo {
            font-size: 0.95rem;
            font-weight: 700;
            color: #3a3a3a;
            margin-bottom: 6px;
        }

        .card-footer {
            display: flex;
            justify-content: flex-end;
            margin-top: 20px;
        }

        .icon-btn {
            width: 30px;
            height: 30px;
            border: 1.5px solid #7aada7;
            border-radius: 6px;
            display: flex;
            align-items: center;
            justify-content: center;
            color: #4a8c87;
            font-size: 1rem;
            text-decoration: none;
        }

        .icon-btn:hover {
            background-color: #b0d0cc;
        }

        .empty {
            grid-column: 1 / -1;
            text-align: center;
            color: #888;
            font-size: 0.95rem;
            padding: 40px;
        }

        @media (max-width: 992px) {
            body {
                margin-left: 0;
                width: 100%;
            }

            .cards-grid {
                grid-template-columns: repeat(2, 1fr);
            }
        }

        @media (max-width: 480px) {
            .cards-grid {
                grid-template-columns: 1fr;
            }
        }
    </style>
