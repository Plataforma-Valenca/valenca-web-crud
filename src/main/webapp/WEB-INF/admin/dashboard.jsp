<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.example.projetodiogo.model.Usuario" %>
<%@ page import="java.util.*" %>

<%
    Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
    String nomeUsuario = (usuario != null) ? usuario.getNome() : "Admin";

    // Dados vindos do Servlet
    int totalAlunos      = (Integer) request.getAttribute("totalAlunos");
    int totalTurmas      = (Integer) request.getAttribute("totalTurmas");
    int totalProfessores = (Integer) request.getAttribute("totalProfessores");
    int filtroTurma      = (Integer) request.getAttribute("filtroTurma");
    int filtroSemestre   = (Integer) request.getAttribute("filtroSemestre");

    List<Map<String,Object>> listaTurmas        = (List<Map<String,Object>>) request.getAttribute("listaTurmas");
    List<Map<String,Object>> alunosPorTurma     = (List<Map<String,Object>>) request.getAttribute("alunosPorTurma");
    List<Map<String,Object>> mediaPorTurma      = (List<Map<String,Object>>) request.getAttribute("mediaPorTurma");
    List<Map<String,Object>> mediaPorDisciplina = (List<Map<String,Object>>) request.getAttribute("mediaPorDisciplina");
    List<Map<String,Object>> aprovados          = (List<Map<String,Object>>) request.getAttribute("aprovadosPorTurma");
    List<Map<String,Object>> reprovados         = (List<Map<String,Object>>) request.getAttribute("reprovadosPorTurma");
    List<Map<String,Object>> criticos           = (List<Map<String,Object>>) request.getAttribute("listaCriticos");

    // Monta JSON para os gráficos
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
    <title>Colégio Valença</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/home.css">
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600;700&display=swap" rel="stylesheet">
</head>
<body>

<jsp:include page="/WEB-INF/views/componentes/sidebarAdm.jsp">
    <jsp:param name="activePage" value="home"/>
</jsp:include>

<div class="page-content">

    <header class="header-home">
        <div class="header-profile" onclick="togglePopup()" style="position:relative;">
            <img src="${pageContext.request.contextPath}/assets/img/personagem.png" alt="Perfil">
            <div class="header-profile-infos">
                <b><%= nomeUsuario %></b>
                <p>Administrador</p>
            </div>
            <div id="popupMenu" class="popup-menu">
                <a href="${pageContext.request.contextPath}/logout">
                    Sair da conta
                    <img src="${pageContext.request.contextPath}/assets/img/icon-logout.svg" class="btn-logout">
                </a>
            </div>
        </div>
    </header>

    <main style="padding: 30px 40px; gap: 3%" class="main-home">

        <% if (erro != null) { %>
            <div style="background:#fee2e2;color:#dc2626;padding:14px;border-radius:10px;margin-bottom:20px;">
                <%= erro %>
            </div>
        <% } %>

        <div class="main-home-title">
            <h1>Olá, o que você procura?</h1>
        </div>

        <div class="main-home-grade">
            <h1 style="color:#7b5cff;margin-bottom:6px;">Dashboard's</h1>
            <p style="color:#666;margin-bottom:24px;">Visão geral de todas as turmas e desempenhos</p>

        <!-- FILTROS -->
        <form method="get" action="${pageContext.request.contextPath}/admin/dashboard"
              style="display:flex;align-items:center;gap:15px;background:#fff;padding:16px 20px;border-radius:14px;border:1px solid #e6e6e6;margin-bottom:28px;flex-wrap:wrap;">

            <label style="font-weight:600;font-size:14px;">Turma:</label>
            <select name="turma" style="padding:8px 12px;border-radius:8px;border:1px solid #ccc;">
                <option value="0" <%= filtroTurma == 0 ? "selected" : "" %>>Todas</option>
                <% if (listaTurmas != null) for (Map<String,Object> t : listaTurmas) { %>
                    <option value="<%= t.get("id_turma") %>"
                        <%= String.valueOf(t.get("id_turma")).equals(String.valueOf(filtroTurma)) ? "selected" : "" %>>
                        <%= t.get("nome") %>
                    </option>
                <% } %>
            </select>

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

        <!-- CARDS -->
        <div style="display:grid;grid-template-columns:repeat(3,1fr);gap:20px;margin-bottom:28px;">
            <div style="background:#fff;padding:22px;border-radius:16px;border:1px solid #e6e6e6;text-align:center;">
                <p style="color:#666;font-size:14px;">Nº de Alunos</p>
                <h2 style="color:#7b5cff;font-size:36px;margin-top:8px;"><%= totalAlunos %></h2>
            </div>
            <div style="background:#fff;padding:22px;border-radius:16px;border:1px solid #e6e6e6;text-align:center;">
                <p style="color:#666;font-size:14px;">Nº de Professores</p>
                <h2 style="color:#7b5cff;font-size:36px;margin-top:8px;"><%= totalProfessores %></h2>
            </div>
            <div style="background:#fff;padding:22px;border-radius:16px;border:1px solid #e6e6e6;text-align:center;">
                <p style="color:#666;font-size:14px;">Qtd de Turmas</p>
                <h2 style="color:#7b5cff;font-size:36px;margin-top:8px;"><%= totalTurmas %></h2>
            </div>
        </div>

        <!-- GRÁFICOS -->
        <div style="display:grid;grid-template-columns:1fr 1fr;gap:24px;margin-bottom:28px;">

            <div style="background:#fff;padding:22px;border-radius:16px;border:1px solid #e6e6e6;">
                <h3 style="margin-bottom:14px;font-size:15px;">Aprovados por turma</h3>
                <canvas id="chartAprovados"></canvas>
            </div>

            <div style="background:#fff;padding:22px;border-radius:16px;border:1px solid #e6e6e6;">
                <h3 style="margin-bottom:14px;font-size:15px;">Reprovados por turma</h3>
                <canvas id="chartReprovados"></canvas>
            </div>

            <div style="background:#fff;padding:22px;border-radius:16px;border:1px solid #e6e6e6;">
                <h3 style="margin-bottom:14px;font-size:15px;">Média por turma</h3>
                <canvas id="chartMediaTurma"></canvas>
            </div>

            <div style="background:#fff;padding:22px;border-radius:16px;border:1px solid #e6e6e6;">
                <h3 style="margin-bottom:14px;font-size:15px;">Média por disciplina</h3>
                <canvas id="chartMediaDisc"></canvas>
            </div>

            <div style="background:#fff;padding:22px;border-radius:16px;border:1px solid #e6e6e6;grid-column:1/-1;max-width:400px;margin:0 auto;width:100%;">
                <h3 style="margin-bottom:14px;font-size:15px;text-align:center;">Alunos por turma</h3>
                <canvas id="chartPizza"></canvas>
            </div>

        </div>

        <!-- TABELA CRÍTICOS -->
        <h2 style="margin-bottom:14px;">Alunos com mais reprovações</h2>
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
        </div>
    </main>
</div>

<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script>
    // Dados passados do Servlet via JSP
    const aprovadosLabels  = <%= aprovados  != null ? toLabels.apply(aprovados)  : "[]" %>;
    const aprovadosValues  = <%= aprovados  != null ? toValues.apply(aprovados)  : "[]" %>;
    const reprovadosLabels = <%= reprovados != null ? toLabels.apply(reprovados) : "[]" %>;
    const reprovadosValues = <%= reprovados != null ? toValues.apply(reprovados) : "[]" %>;
    const mediaTurmaLabels = <%= mediaPorTurma != null ? toLabels.apply(mediaPorTurma) : "[]" %>;
    const mediaTurmaValues = <%= mediaPorTurma != null ? toValues.apply(mediaPorTurma) : "[]" %>;
    const mediaDiscLabels  = <%= mediaPorDisciplina != null ? toLabels.apply(mediaPorDisciplina) : "[]" %>;
    const mediaDiscValues  = <%= mediaPorDisciplina != null ? toValues.apply(mediaPorDisciplina) : "[]" %>;
    const pizzaLabels      = <%= alunosPorTurma != null ? toLabels.apply(alunosPorTurma) : "[]" %>;
    const pizzaValues      = <%= alunosPorTurma != null ? toValues.apply(alunosPorTurma) : "[]" %>;

    const barOpts = { responsive: true, scales: { y: { beginAtZero: true } } };
    const mediaOpts = { responsive: true, scales: { y: { min: 0, max: 10 } } };

    new Chart(document.getElementById("chartAprovados"), {
        type: "bar",
        data: { labels: aprovadosLabels, datasets: [{ label: "Aprovados", data: aprovadosValues, backgroundColor: "#7b5cff", borderRadius: 6 }] },
        options: barOpts
    });
    new Chart(document.getElementById("chartReprovados"), {
        type: "bar",
        data: { labels: reprovadosLabels, datasets: [{ label: "Reprovados", data: reprovadosValues, backgroundColor: "#7fd8d4", borderRadius: 6 }] },
        options: barOpts
    });
    new Chart(document.getElementById("chartMediaTurma"), {
        type: "bar",
        data: { labels: mediaTurmaLabels, datasets: [{ label: "Média", data: mediaTurmaValues, backgroundColor: "#a66bff", borderRadius: 6 }] },
        options: mediaOpts
    });
    new Chart(document.getElementById("chartMediaDisc"), {
        type: "bar",
        data: { labels: mediaDiscLabels, datasets: [{ label: "Média", data: mediaDiscValues, backgroundColor: "#ffb347", borderRadius: 6 }] },
        options: mediaOpts
    });
    new Chart(document.getElementById("chartPizza"), {
        type: "pie",
        data: { labels: pizzaLabels, datasets: [{ data: pizzaValues, backgroundColor: ["#7b5cff","#a66bff","#7fd8d4","#ffb347","#cfcfcf"] }] },
        options: { responsive: true }
    });

    // Popup de perfil
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
