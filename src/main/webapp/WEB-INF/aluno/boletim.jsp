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
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 40px;
        }

        h1 {
            font-size: 28px;
            margin-bottom: 20px;
        }

        .container {
            background: white;
            padding: 25px;
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        th {
            text-align: left;
            padding: 12px;
            border-bottom: 1px solid #ddd;
            color: #555;
        }

        td {
            padding: 12px;
        }

        .azul {
            color: #2563eb;
            font-weight: bold;
        }

        .vermelho {
            color: #dc2626;
            font-weight: bold;
        }

        .aprovado {
            color: #16a34a;
            font-weight: bold;
        }

        .reprovado {
            color: #dc2626;
            font-weight: bold;
        }

        .btn {
            float: right;
            background-color: #1f6f5c;
            color: white;
            padding: 8px 18px;
            border: none;
            border-radius: 6px;
            cursor: pointer;
        }

        .btn:hover {
            opacity: 0.9;
        }

        .topo {
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

    </style>
</head>

<body>

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