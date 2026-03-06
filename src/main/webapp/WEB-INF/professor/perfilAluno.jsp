<%--
  Created by IntelliJ IDEA.
  User: emanuellymeso-ieg
  Date: 03/03/2026
  Time: 10:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.projetodiogo.model.*" %>
<%@ page import="org.example.projetodiogo.model.DTO.AlunoConsultaDTO" %>

<%
    AlunoConsultaDTO aluno = (AlunoConsultaDTO) request.getAttribute("alunoConsulta");
    String turma = (String) request.getAttribute("turma");
    List<Boletim> boletimList = (List<Boletim>) request.getAttribute("boletimList");
    List<Observacao> obsList = (List<Observacao>) request.getAttribute("obsList");
%>

<!doctype html>
<html lang="pt-br">
<head>

    <meta charset="UTF-8" />
    <title>Detalhes do Aluno - Colégio Barão</title>
    <style>
        /* =========================
     BASE DA PÁGINA
  ========================= */

        body {
            background-color: #eef2f6;
        }

        /* Respeita a largura da sidebar */
        .main-content {
            margin-left: 22vw; /* igual ao #aside */
            padding: 50px 60px;
            min-height: 100vh;
        }

        /* =========================
           TÍTULO DO ALUNO
        ========================= */

        .main-content h2 {
            font-size: 30px;
            font-weight: 600;
            color: #2c3e50;
            margin-bottom: 40px;
        }

        /* =========================
           CARDS INFORMAÇÕES
        ========================= */

        .student-info-grid {
            display: flex;
            gap: 30px;
            margin-bottom: 50px;
            flex-wrap: wrap;
        }

        .info-item {
            background: white;
            padding: 25px 30px;
            border-radius: 16px;
            min-width: 230px;
            box-shadow: 0 6px 20px rgba(0,0,0,0.05);
            transition: 0.2s ease;
        }

        .info-item:hover {
            transform: translateY(-4px);
        }

        .info-item label {
            display: block;
            font-size: 13px;
            color: #7a7a7a;
            margin-bottom: 8px;
        }

        .info-item span {
            font-size: 18px;
            font-weight: 600;
            color: #34495e;
        }

        /* =========================
           TÍTULOS DE SEÇÃO
        ========================= */

        .section-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 20px;
        }

        .section-title {
            font-size: 22px;
            font-weight: 600;
            color: #2c3e50;
        }

        /* =========================
           BOTÃO ADICIONAR
        ========================= */

        .action-button {
            background-color: #346A63;
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 8px;
            font-size: 14px;
            cursor: pointer;
            transition: 0.2s ease;
        }

        .action-button:hover {
            background-color: #28544e;
            transform: translateY(-2px);
        }

        /* =========================
           CONTAINER TABELA
        ========================= */

        .data-table-container {
            background: white;
            padding: 30px;
            border-radius: 16px;
            box-shadow: 0 6px 20px rgba(0,0,0,0.05);
            margin-bottom: 50px;
        }

        /* =========================
           TABELAS
        ========================= */

        .data-table {
            width: 100%;
            border-collapse: collapse;
        }

        .data-table thead {
            background-color: #f3f6f9;
        }

        .data-table th {
            padding: 16px;
            font-size: 13px;
            text-transform: uppercase;
            color: #666;
            letter-spacing: 0.5px;
        }

        .data-table td {
            padding: 16px;
            font-size: 15px;
            border-top: 1px solid #eee;
        }

        /* Centraliza notas */
        .data-table th:not(:first-child),
        .data-table td:not(:first-child) {
            text-align: center;
        }

        .data-table tbody tr:hover {
            background-color: #f9fbfc;
        }

        /* Linha vazia */
        .data-table td[colspan] {
            text-align: center;
            color: #888;
            padding: 30px;
        }

        /* =========================
           RESPONSIVO
        ========================= */

        @media (max-width: 992px) {

            #aside {
                position: relative;
                width: 100%;
                height: auto;
            }

            .main-content {
                margin-left: 0;
                padding: 30px;
            }

            .student-info-grid {
                flex-direction: column;
            }
        }
    </style>
    <link rel="stylesheet"
          href="<%= request.getContextPath() %>/css/style.css">
</head>
<body>
<jsp:include page="/WEB-INF/views/componentes/sidebarProfessor.jsp"/>

<div class="main-content">

    <h2><%= aluno.getNome() %></h2>

    <div class="student-info-grid">
        <div class="info-item">
            <label>Matrícula</label>
            <span><%= aluno.getMatricula() %></span>
        </div>

        <div class="info-item">
            <label>Turma</label>
            <span><%= turma %></span>
        </div>
    </div>

    <!-- NOTAS -->
    <div class="section-header">
        <h3 class="section-title">Notas</h3>
    </div>

    <div class="data-table-container">
        <table class="data-table">
            <thead>
            <tr>
                <th>Média 1º SEM</th>
                <th>Média 2º SEM</th>
                <th>Média Final</th>
            </tr>
            </thead>

            <tbody>
            <%
                if (boletimList != null && !boletimList.isEmpty()) {
                    for (Boletim b : boletimList) {
            %>
            <tr>

                <td><%= b.getMedia1()%></td>
                <td><%= b.getMedia2()%></td>
                <td><%= b.getMediaFinal() %></td>
            </tr>
            <%
                }
            } else {
            %>
            <tr>
                <td colspan="7">Nenhuma nota encontrada.</td>
            </tr>
            <%
                }
            %>
            </tbody>
        </table>
    </div>


    <!-- OBSERVAÇÕES -->
    <div class="section-header">
        <h3 class="section-title">Observações</h3>
        <button class="action-button" onclick="abrirModalObs()">
            + Adicionar
        </button>
    </div>

    <div class="data-table-container">
        <table class="data-table">
            <thead>
            <tr>
                <th style="text-align: left; width: 150px;">Data</th>
                <th style="text-align: left;">Observação</th>
            </tr>
            </thead>

            <tbody>
            <%
                if (obsList != null && !obsList.isEmpty()) {
                    for (Observacao obs : obsList) {
            %>
            <tr>
                <td style="text-align: left;">
                    <%= obs.getDataEnvio() %>
                </td>
                <td style="text-align: left;">
                    <%= obs.getDescricao() %>
                </td>
            </tr>
            <%
                }
            } else {
            %>
            <tr>
                <td colspan="2">Nenhuma observação encontrada.</td>
            </tr>
            <%
                }
            %>
            </tbody>
        </table>
    </div>

</div>

<jsp:include page="/WEB-INF/professor/inserirObservacaoProfessor.jsp">
    <jsp:param name="idAluno" value="<%= aluno.getIdAluno() %>"/>
</jsp:include>

<script>

    function abrirModalObs(){
        document.getElementById("modal-obs-bg").style.display = "flex";
    }

    function fecharModalObs(){
        document.getElementById("modal-obs-bg").style.display = "none";
    }

</script>

</body>
</html>