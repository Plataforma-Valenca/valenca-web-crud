<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.example.projetodiogo.model.Usuario" %>
<%@ page import="java.util.*" %>

<%
    Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
    String nomeUsuario = (usuario != null) ? usuario.getNome() : "Professor";

    int filtroSemestre = (Integer) request.getAttribute("filtroSemestre");

    List<String>            disciplinas      = (List<String>)            request.getAttribute("disciplinas");
    List<Map<String,Object>> mediaDisciplina = (List<Map<String,Object>>) request.getAttribute("mediaPorDisciplina");
    List<Map<String,Object>> aprovados       = (List<Map<String,Object>>) request.getAttribute("aprovadosPorTurma");
    List<Map<String,Object>> reprovados      = (List<Map<String,Object>>) request.getAttribute("reprovadosPorTurma");
    List<Map<String,Object>> mediaTurma      = (List<Map<String,Object>>) request.getAttribute("mediaPorTurma");
    List<Map<String,Object>> criticos        = (List<Map<String,Object>>) request.getAttribute("listaCriticos");

    java.util.function.Function<List<Map<String,Object>>, String> toLabels = list -> {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < list.size(); i++) {
            if (i > 0) sb.append(",");
            sb.append("\"").append(list.get(i).get("label")).append("\"");
        }
        return sb.append("]").toString();
    };
    java.util.function.Function<List<Map<String,Object>>, String> toValues = list -> {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < list.size(); i++) {
            if (i > 0) sb.append(",");
            sb.append(list.get(i).get("valor"));
        }
        return sb.append("]").toString();
    };

    String erro = (String) request.getAttribute("erro");
%>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Dashboard - Professor</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/dashboard.css">
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600;700&display=swap" rel="stylesheet">
</head>
<body>

<jsp:include page="/WEB-INF/views/componentes/sidebarProfessor.jsp"/>

<div class="page-content">

    <header class="header-home">
        <div class="header-profile" onclick="togglePopup()" style="position:relative;">
            <img src="${pageContext.request.contextPath}/assets/img/personagem.png" alt="Perfil">
            <div class="header-profile-infos">
                <b><%= nomeUsuario %></b>
                <p>Professor</p>
            </div>
            <div id="popupMenu" class="popup-menu">
                <a href="${pageContext.request.contextPath}/logout">
                    Sair da conta
                    <img src="${pageContext.request.contextPath}/assets/img/icon-logout.svg" height="18">
                </a>
            </div>
        </div>
    </header>

    <main style="padding: 30px 40px;">

        <% if (erro != null) { %>
            <div style="background:#fee2e2;color:#dc2626;padding:14px;border-radius:10px;margin-bottom:20px;">
                <%= erro %>
            </div>
        <% } %>

        <h1 style="color:#7b5cff;margin-bottom:6px;">Dashboard</h1>
        <p style="color:#666;margin-bottom:20px;">Desempenho dos alunos nas suas disciplinas</p>

        <!-- TAGS DAS DISCIPLINAS -->
        <div style="display:flex;flex-wrap:wrap;gap:10px;margin-bottom:24px;">
            <% if (disciplinas != null) for (String d : disciplinas) { %>
                <span style="background:#f0ecff;color:#7b5cff;padding:6px 16px;border-radius:20px;font-size:13px;font-weight:600;">
                    <%= d %>
                </span>
            <% } %>
        </div>

        <!-- FILTRO SEMESTRE -->
        <form method="get" action="${pageContext.request.contextPath}/professor/dashboard"
              style="display:flex;align-items:center;gap:15px;background:#fff;padding:16px 20px;border-radius:14px;border:1px solid #e6e6e6;margin-bottom:28px;flex-wrap:wrap;">
            <label style="font-weight:600;font-size:14px;">Semestre:</label>
            <select name="semestre" style="padding:8px 12px;border-radius:8px;border:1px solid #ccc;">
                <option value="0" <%= filtroSemestre == 0 ? "selected" : "" %>>Todos</option>
                <option value="1" <%= filtroSemestre == 1 ? "selected" : "" %>>1° Semestre</option>
                <option value="2" <%= filtroSemestre == 2 ? "selected" : "" %>>2° Semestre</option>
            </select>
            <button type="submit"
                style="padding:9px 20px;background:#7b5cff;color:#fff;border:none;border-radius:8px;font-weight:600;cursor:pointer;">
                Aplicar
            </button>
        </form>

        <!-- GRÁFICOS -->
        <div style="display:grid;grid-template-columns:1fr 1fr;gap:24px;margin-bottom:28px;">

            <div style="background:#fff;padding:22px;border-radius:16px;border:1px solid #e6e6e6;">
                <h3 style="margin-bottom:14px;font-size:15px;">Aprovados por turma</h3>
                <canvas id="chartAprovados"></canvas>
            </div>

            <div style="background:#fff;padding:22px;border-radius:16px;border:1px solid #e6e6e6;">
                <h3 style="margin-bottom:14px;font-size:15px;">Média por disciplina</h3>
                <canvas id="chartMediaDisc"></canvas>
            </div>

            <div style="background:#fff;padding:22px;border-radius:16px;border:1px solid #e6e6e6;">
                <h3 style="margin-bottom:14px;font-size:15px;">Reprovados por turma</h3>
                <canvas id="chartReprovados"></canvas>
            </div>

            <div style="background:#fff;padding:22px;border-radius:16px;border:1px solid #e6e6e6;">
                <h3 style="margin-bottom:14px;font-size:15px;">Média por turma</h3>
                <canvas id="chartMediaTurma"></canvas>
            </div>

        </div>

        <!-- TABELA CRÍTICOS -->
        <h2 style="margin-bottom:14px;">Alunos críticos nas suas disciplinas</h2>
        <table style="width:100%;border-collapse:collapse;background:#fff;border-radius:14px;overflow:hidden;border:1px solid #e6e6e6;">
            <thead style="background:#7b5cff;color:#fff;">
                <tr>
                    <th style="padding:12px;text-align:left;font-size:14px;">Turma</th>
                    <th style="padding:12px;text-align:left;font-size:14px;">Matrícula</th>
                    <th style="padding:12px;text-align:left;font-size:14px;">Nome</th>
                    <th style="padding:12px;text-align:left;font-size:14px;">Disciplinas abaixo de 7</th>
                </tr>
            </thead>
            <tbody>
                <% if (criticos == null || criticos.isEmpty()) { %>
                    <tr><td colspan="4" style="padding:14px;text-align:center;color:#999;">Nenhum aluno crítico encontrado</td></tr>
                <% } else { for (Map<String,Object> c : criticos) { %>
                    <tr style="border-top:1px solid #eee;">
                        <td style="padding:12px;font-size:14px;"><%= c.get("turma") %></td>
                        <td style="padding:12px;font-size:14px;"><%= c.get("matricula") %></td>
                        <td style="padding:12px;font-size:14px;"><%= c.get("nome") %></td>
                        <td style="padding:12px;font-size:14px;"><%= c.get("qtdReprovadas") %></td>
                    </tr>
                <% } } %>
            </tbody>
        </table>

    </main>
</div>

<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script>
    const barOpts   = { responsive: true, scales: { y: { beginAtZero: true } } };
    const mediaOpts = { responsive: true, scales: { y: { min: 0, max: 10 } } };

    new Chart(document.getElementById("chartAprovados"), {
        type: "bar",
        data: { labels: <%= aprovados != null ? toLabels.apply(aprovados) : "[]" %>, datasets: [{ label: "Aprovados", data: <%= aprovados != null ? toValues.apply(aprovados) : "[]" %>, backgroundColor: "#7b5cff", borderRadius: 6 }] },
        options: barOpts
    });
    new Chart(document.getElementById("chartReprovados"), {
        type: "bar",
        data: { labels: <%= reprovados != null ? toLabels.apply(reprovados) : "[]" %>, datasets: [{ label: "Reprovados", data: <%= reprovados != null ? toValues.apply(reprovados) : "[]" %>, backgroundColor: "#7fd8d4", borderRadius: 6 }] },
        options: barOpts
    });
    new Chart(document.getElementById("chartMediaDisc"), {
        type: "bar",
        data: { labels: <%= mediaDisciplina != null ? toLabels.apply(mediaDisciplina) : "[]" %>, datasets: [{ label: "Média", data: <%= mediaDisciplina != null ? toValues.apply(mediaDisciplina) : "[]" %>, backgroundColor: "#ffb347", borderRadius: 6 }] },
        options: mediaOpts
    });
    new Chart(document.getElementById("chartMediaTurma"), {
        type: "bar",
        data: { labels: <%= mediaTurma != null ? toLabels.apply(mediaTurma) : "[]" %>, datasets: [{ label: "Média", data: <%= mediaTurma != null ? toValues.apply(mediaTurma) : "[]" %>, backgroundColor: "#a66bff", borderRadius: 6 }] },
        options: mediaOpts
    });

    function togglePopup() {
        const p = document.getElementById("popupMenu");
        p.style.display = (p.style.display === "block") ? "none" : "block";
    }
    window.onclick = e => {
        if (!e.target.closest(".header-profile"))
            document.getElementById("popupMenu").style.display = "none";
    };
</script>

</body>
</html>
