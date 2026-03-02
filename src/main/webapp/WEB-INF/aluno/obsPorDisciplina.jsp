<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.example.projetodiogo.model.Observacao" %>
<%@ page import="java.text.SimpleDateFormat" %>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Observações</title>
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
            padding: 32px 40px;
            margin-left: 22vw;
            color: #3a3a3a;
        }

        .header {
            display: flex;
            align-items: flex-start;
            gap: 14px;
            margin-bottom: 52px;
        }

        .btn-voltar {
            text-decoration: none;
            color: #4a8c87;
            font-size: 1.3rem;
            margin-top: 2px;
            display: flex;
            align-items: center;
            transition: color 0.2s;
        }

        .btn-voltar:hover {
            color: #2e6b66;
        }

        .header-info .disciplina-nome {
            font-size: 1rem;
            font-weight: 700;
            color: #3a3a3a;
        }

        .header-info .professor-nome {
            font-size: 0.82rem;
            color: #888;
            margin-top: 2px;
        }

        .secoes {
            display: flex;
            justify-content: center;
            gap: 120px;
            margin-bottom: 20px;
        }

        .secao-link {
            font-size: 1rem;
            text-decoration: underline;
            text-underline-offset: 4px;
            cursor: pointer;
        }

        .secao-link.inativo {
            font-weight: 400;
            color: #aaa;
        }

        .secao-link.ativo {
            font-weight: 700;
            color: #3a9088;
        }

        .tabela-wrapper {
            background: white;
            border-radius: 10px;
            overflow: hidden;
            box-shadow: 0 2px 10px rgba(0,0,0,0.06);
            max-width: 860px;
            margin: 0 auto;
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        thead tr th {
            padding: 14px 20px;
            font-size: 0.82rem;
            font-weight: 600;
            color: #555;
            text-align: left;
            background: white;
            border-bottom: 1px solid #eee;
        }

        tbody tr td {
            padding: 14px 20px;
            font-size: 0.85rem;
            color: #444;
            border-bottom: 1px solid #f5f5f5;
        }

        tbody tr:last-child td {
            border-bottom: none;
        }

        .nota-vazia {
            color: #aaa;
            text-align: center;
            padding: 32px;
        }

        @media (max-width: 992px) {
            body {
                margin-left: 0;
            }

            .secoes {
                gap: 60px;
            }
        }

        @media (max-width: 480px) {
            body {
                padding: 20px;
            }

            .header {
                margin-bottom: 32px;
            }

            .tabela-wrapper {
                max-width: 100%;
            }
        }
    </style>
</head>
<body>

<jsp:include page="/WEB-INF/views/componentes/sidebarAluno.jsp"/>

<%
    ArrayList<Observacao> obsList = (ArrayList<Observacao>) request.getAttribute("obsList");
    String nomeDisciplina = request.getAttribute("nomeDisciplina") != null
            ? (String) request.getAttribute("nomeDisciplina") : "";
    String nomeProfessor = (String) request.getAttribute("nomeProfessor");
    int idDisciplina = request.getAttribute("idDisciplina") != null
            ? (int) request.getAttribute("idDisciplina") : 0;
%>

<!-- HEADER -->
<div class="header">
    <a href="javascript:history.back()" class="btn-voltar">&#x2BA8;</a>
    <div class="header-info">
        <div class="disciplina-nome"><%= nomeDisciplina %></div>
        <div class="professor-nome">Prof. <%= nomeProfessor %></div>
    </div>
</div>

<!-- ABAS -->
<div class="secoes">
    <a href="${pageContext.request.contextPath}/aluno/notasPorDisciplina?idDisciplina=<%= idDisciplina %>"
       class="secao-link inativo">Avaliações</a>
    <span class="secao-link ativo">Observações</span>
</div>

<!-- TABELA -->
<div class="tabela-wrapper">
    <table>
        <thead>
            <tr>
                <th>Data</th>
                <th>Observação</th>
            </tr>
        </thead>
        <tbody>
        <%
            if (obsList != null && !obsList.isEmpty()) {
                for (Observacao obs : obsList) {
        %>
        <tr>
            <td><%= new SimpleDateFormat("dd/MM/yyyy").format(obs.getDataEnvio()) %></td>
            <td><%= obs.getDescricao() %></td>
        </tr>
        <%
                }
            } else {
        %>
        <tr>
            <td colspan="2" class="nota-vazia">Nenhuma observação encontrada.</td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
</div>

</body>
</html>
