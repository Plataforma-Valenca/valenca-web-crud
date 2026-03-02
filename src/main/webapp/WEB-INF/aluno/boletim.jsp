<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.example.projetodiogo.model.Boletim" %>
<%@ page import="org.example.projetodiogo.model.Disciplina" %>

<%
    ArrayList<Disciplina> disciplinasList = (ArrayList<Disciplina>) request.getAttribute("disciplinasList");
    ArrayList<Boletim> boletimList = (ArrayList<Boletim>) request.getAttribute("boletimList");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Boletim</title>

    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;

            margin-left: 22vw;
            width: 78vw;

            min-height: 100vh;
            padding: 60px 40px;
        }

        /* ===== TOPO ===== */

        .topo {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 30px;
        }

        h1 {
            font-size: 28px;
        }

        /* ===== BOTÃO ===== */

        .btn {
            background-color: #1f6f5c;
            color: white;
            padding: 8px 18px;
            border-radius: 6px;
            text-decoration: none;
            transition: 0.2s;
        }

        .btn:hover {
            opacity: 0.9;
        }

        /* ===== CONTAINER ===== */

        .container {
            background: white;
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 4px 20px rgba(0,0,0,0.08);
            width: 100%;
        }

        /* ===== TABELA ===== */

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th {
            text-align: left;
            padding: 12px;
            border-bottom: 1px solid #ddd;
            color: #555;
            font-size: 14px;
        }

        td {
            padding: 12px;
            font-size: 14px;
        }

        tr:hover {
            background-color: #f9f9f9;
        }

        /* ===== CORES ===== */

        .azul {
            color: #2563eb;
            font-weight: bold;
        }

        .vermelho {
            color: #dc2626;
            font-weight: bold;
        }

        /* ===== RESPONSIVO ===== */

        @media (max-width: 992px) {

            body {
                margin-left: 0;
                width: 100%;
                padding: 40px 20px;
            }
        }

        @media (max-width: 600px) {

            .topo {
                flex-direction: column;
                align-items: flex-start;
                gap: 15px;
            }

            table {
                font-size: 13px;
            }
        }

    </style>
</head>

<body>

<jsp:include page="/WEB-INF/views/componentes/sidebarAluno.jsp"/>

<div class="topo">
    <h1>Boletim</h1>
    <a class="btn" href="${pageContext.request.contextPath}/aluno/gerarBoletim">Gerar boletim</a>
</div>

<div class="container">

    <p><strong>Disciplina:</strong> <%= disciplinasList.get(0).getNome() %></p>
    <p><strong>Professor:</strong></p>

    <table>
        <thead>
        <tr>
            <th>Disciplina</th>
            <th>N1</th>
            <th>N2</th>
            <th>Média Final</th>
            <th>Situação</th>
        </tr>
        </thead>
        <tbody>

        <%
            if (boletimList != null) {
                for (int i = 0; i < boletimList.size(); i++) {
                    Boletim b = boletimList.get(i);
                    String nomeDisciplina = disciplinasList.get(i).getNome();

                    double mediaFinal = b.getMediaFinal();
                    boolean aprovado = mediaFinal >= 7;
        %>

        <tr>
            <td><%= nomeDisciplina %></td>

            <td class="azul"><%= b.getMedia1() %></td>

            <td class="vermelho"><%= b.getMedia2() %></td>

            <td class="<%= aprovado ? "azul" : "vermelho" %>">
                <%= mediaFinal %>
            </td>

<%--            <td class="<%= aprovado ? "aprovado" : "reprovado" %>">--%>
<%--                <%= aprovado ? "Aprovado" : "Reprovado" %>--%>
<%--            </td>--%>

            <td class="<%= aprovado ? "azul" : "vermelho" %>">
                <%= b.getSituacao() %>
            </td>
        </tr>

        <%
                }
            }
        %>

        </tbody>
    </table>

</div>

</body>
</html>