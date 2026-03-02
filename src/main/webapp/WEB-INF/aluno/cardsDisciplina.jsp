<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.projetodiogo.model.Boletim" %>
<%@ page import="org.example.projetodiogo.model.Disciplina" %>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home - Aluno</title>
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

            /* 🔥 AGORA A MAIN RESPEITA A SIDEBAR */
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
            line-height: 1.4;
        }

        .card-subtitulo {
            font-size: 0.8rem;
            color: #5a5a5a;
            line-height: 1.4;
        }

        .card-footer {
            display: flex;
            justify-content: flex-end;
            margin-top: 20px;
        }

        .card-footer .icon-btn {
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
            transition: background 0.2s;
        }

        .card-footer .icon-btn:hover {
            background-color: #b0d0cc;
        }

        .empty {
            grid-column: 1 / -1;
            text-align: center;
            color: #888;
            font-size: 0.95rem;
            padding: 40px;
        }

        /* RESPONSIVO */

        @media (max-width: 992px) {

            /* Quando a sidebar deixa de ser fixed */
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
</head>
<body>

<jsp:include page="/WEB-INF/views/componentes/sidebarAluno.jsp"/>

<h1>Olá, Aluno, o que você procura?</h1>

<div class="cards-grid">
    <%
        List<Disciplina> disciplinaList = (List<Disciplina>) request.getAttribute("disciplinaList");

        if (disciplinaList != null && !disciplinaList.isEmpty()) {
            for (Disciplina d : disciplinaList ) {
    %>
    <a class="card" href="${pageContext.request.contextPath}/aluno/notasPorDisciplina?idDisciplina=<%= d.getId() %>">
        <div class="card-body">
            <div class="card-titulo"><%= d.getNomeFormatado() %></div>
<%--            <div class="card-subtitulo"><%= b.getDescricao() %></div>--%>
        </div>
        <div class="card-footer">
            <span class="icon-btn">&#x2192;</span>
        </div>
    </a>
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
