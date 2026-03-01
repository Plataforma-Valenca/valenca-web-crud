<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.projetodiogo.model.Boletim" %>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Notas - <%= request.getAttribute("nomeDisciplina") %></title>
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
            color: #3a3a3a;
        }

        /* ===== VOLTAR + TÍTULO ===== */
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

        /* ===== SEÇÕES ===== */
        .secoes {
            display: flex;
            justify-content: center;
            gap: 180px;
            margin-bottom: 20px;
        }

        .secao-titulo {
            font-size: 1rem;
            font-weight: 700;
            color: #3a9088;
            text-decoration: underline;
            text-underline-offset: 4px;
        }

        .secao-titulo.inativo {
            font-weight: 400;
            color: #aaa;
            text-decoration: underline;
            text-underline-offset: 4px;
        }

        /* ===== TABELA ===== */
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
            padding: 16px 20px;
            font-size: 0.8rem;
            font-weight: 600;
            color: #555;
            text-align: left;
            background: white;
            border-bottom: 1px solid #eee;
        }

        tbody tr td {
            padding: 16px 20px;
            font-size: 0.85rem;
            color: #333;
            border-bottom: 1px solid #f5f5f5;
        }

        tbody tr:last-child td {
            border-bottom: none;
        }

        /* Cores das notas */
        .nota-azul  { color: #3a9dd4; font-weight: 600; }
        .nota-vermelho { color: #e05c5c; font-weight: 600; }
        .nota-media { color: #3a9088; font-weight: 600; }
        .nota-vazia { color: #aaa; }
    </style>
</head>
<body>

<!-- HEADER -->
<div class="header">
    <a href="javascript:history.back()" class="btn-voltar">&#x2BA8;</a>
    <div class="header-info">
        <div class="disciplina-nome"><%= request.getAttribute("nomeDisciplina") %></div>
        <%
            int idDisciplina = (int) request.getAttribute("idDisciplina");
            List<Boletim> boletimList = (List<Boletim>) request.getAttribute("boletimList");
            String nomeProfessor = "";
            if (request.getAttribute("nomeProfessor") != null) {
                nomeProfessor = (String) request.getAttribute("nomeProfessor");
            }
        %>
        <div class="professor-nome">Prof. <%= nomeProfessor %></div>
    </div>
</div>

<!-- SEÇÕES -->
<div class="secoes">
    <span class="secao-titulo">Avaliações</span>
    <a class="secao-titulo inativo" href="${pageContext.request.contextPath}/aluno/verObservacoes?idDisciplina=<%= idDisciplina %>">Observações</a>
</div>

<!-- TABELA -->
<div class="tabela-wrapper">
    <table>
        <thead>
            <tr>
                <!-- Avaliações -->
                <th>Disciplinas</th>
                <th>Média 1° SEM</th>
                <th>Média 2° SEM</th>
                <th>Média Final</th>
            </tr>
        </thead>
        <tbody>
        <%
            if (boletimList != null && !boletimList.isEmpty()) {
                for (Boletim b : boletimList) {
                    // Notas avaliações
                    String n1 = b.getMedia1() != null ? String.valueOf(b.getMedia1()) : "--";
                    String n2 = b.getMedia2() != null ? String.valueOf(b.getMedia2()) : "--";
                    String mf = b.getMediaFinal() != null ? String.valueOf(b.getMediaFinal()) : "--";
        %>
        <tr>
            <td class=""><%= request.getAttribute("nomeDisciplina")%></td>
            <td class="<%= !n2.equals("--") ? "nota-vermelho" : "nota-vazia" %>"><%= n1 %></td>
            <td class="<%= !n1.equals("--") ? "nota-media" : "nota-vazia" %>"><%= n2 %></td>
            <td class="<%= !mf.equals("--") ? "nota-azul" : "nota-vazia" %>"><%= mf %></td>
        </tr>
        <%
                }
            } else {
        %>
        <tr>
            <td colspan="7" style="text-align:center; color:#aaa; padding:32px;">
                Nenhuma nota encontrada.
            </td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
</div>

</body>
</html>
