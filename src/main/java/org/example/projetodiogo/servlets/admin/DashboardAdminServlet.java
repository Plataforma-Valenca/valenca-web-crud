package org.example.projetodiogo.servlets.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.projetodiogo.util.ConnectionFactory;

import java.io.IOException;
import java.sql.*;
import java.util.*;

@WebServlet("/admin/dashboard")
public class DashboardAdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String turmaParam    = request.getParameter("turma");
        String semestreParam = request.getParameter("semestre");

        int turmaId   = (turmaParam    != null && !turmaParam.equals("0"))    ? Integer.parseInt(turmaParam)    : 0;
        int semestreId = (semestreParam != null && !semestreParam.equals("0")) ? Integer.parseInt(semestreParam) : 0;

        try (Connection conn = ConnectionFactory.conectar()) {

            int totalAlunos = queryInt(conn, "SELECT COUNT(DISTINCT id_aluno) FROM alunos");
            int totalTurmas = queryInt(conn, "SELECT COUNT(DISTINCT id_turma) FROM turmas");
            int totalProfessores = queryInt(conn, "SELECT COUNT(DISTINCT id_professor) FROM professores");

            request.setAttribute("totalAlunos", totalAlunos);
            request.setAttribute("totalTurmas", totalTurmas);
            request.setAttribute("totalProfessores", totalProfessores);

            request.setAttribute("listaTurmas", queryList(conn,
                    "SELECT id_turma, nome FROM turmas ORDER BY nome", "id_turma", "nome"));

            String sqlAlunosTurma = """
                SELECT t.nome AS label, COUNT(DISTINCT at2.id_aluno) AS valor
                FROM turmas t
                JOIN aluno_turma at2 ON at2.id_turma = t.id_turma
                """ + (turmaId > 0 ? "WHERE t.id_turma = " + turmaId : "") + """
                GROUP BY t.nome ORDER BY t.nome
                """;
            request.setAttribute("alunosPorTurma", queryLabelValor(conn, sqlAlunosTurma));

            StringBuilder sqlMedia = new StringBuilder("""
                SELECT t.nome AS label, ROUND(AVG(av.valor)::numeric, 2) AS valor
                FROM turmas t
                JOIN aluno_turma at2 ON at2.id_turma = t.id_turma
                JOIN notas n         ON n.id_aluno   = at2.id_aluno
                JOIN avaliacoes av   ON av.id_nota   = n.id_nota
                WHERE 1=1
                """);
            if (turmaId   > 0) sqlMedia.append(" AND t.id_turma = ").append(turmaId);
            if (semestreId > 0) sqlMedia.append(" AND av.semestre = ").append(semestreId);
            sqlMedia.append(" GROUP BY t.nome ORDER BY t.nome");
            request.setAttribute("mediaPorTurma", queryLabelValor(conn, sqlMedia.toString()));

            StringBuilder sqlMediaDisc = new StringBuilder("""
                SELECT d.nome AS label, ROUND(AVG(av.valor)::numeric, 2) AS valor
                FROM disciplinas d
                JOIN notas n         ON n.id_disciplina = d.id_disciplina
                JOIN avaliacoes av   ON av.id_nota       = n.id_nota
                JOIN aluno_turma at2 ON at2.id_aluno     = n.id_aluno
                WHERE 1=1
                """);
            if (turmaId   > 0) sqlMediaDisc.append(" AND at2.id_turma = ").append(turmaId);
            if (semestreId > 0) sqlMediaDisc.append(" AND av.semestre = ").append(semestreId);
            sqlMediaDisc.append(" GROUP BY d.nome ORDER BY d.nome");
            request.setAttribute("mediaPorDisciplina", queryLabelValor(conn, sqlMediaDisc.toString()));

            StringBuilder sqlAprov = new StringBuilder("""
                SELECT t.nome AS label, COUNT(DISTINCT sub.id_aluno) AS valor
                FROM turmas t
                JOIN aluno_turma at2 ON at2.id_turma = t.id_turma
                JOIN (
                    SELECT n.id_aluno, at3.id_turma
                    FROM notas n
                    JOIN avaliacoes av   ON av.id_nota    = n.id_nota
                    JOIN aluno_turma at3 ON at3.id_aluno  = n.id_aluno
                    WHERE 1=1
                """);
            if (semestreId > 0) sqlAprov.append(" AND av.semestre = ").append(semestreId);
            sqlAprov.append("""
                    GROUP BY n.id_aluno, at3.id_turma
                    HAVING AVG(av.valor) >= 7
                ) sub ON sub.id_aluno = at2.id_aluno AND sub.id_turma = t.id_turma
                WHERE 1=1
                """);
            if (turmaId > 0) sqlAprov.append(" AND t.id_turma = ").append(turmaId);
            sqlAprov.append(" GROUP BY t.nome ORDER BY t.nome");
            request.setAttribute("aprovadosPorTurma", queryLabelValor(conn, sqlAprov.toString()));

            StringBuilder sqlReprov = new StringBuilder("""
                SELECT t.nome AS label, COUNT(DISTINCT sub.id_aluno) AS valor
                FROM turmas t
                JOIN aluno_turma at2 ON at2.id_turma = t.id_turma
                JOIN (
                    SELECT n.id_aluno, at3.id_turma
                    FROM notas n
                    JOIN avaliacoes av   ON av.id_nota    = n.id_nota
                    JOIN aluno_turma at3 ON at3.id_aluno  = n.id_aluno
                    WHERE 1=1
                """);
            if (semestreId > 0) sqlReprov.append(" AND av.semestre = ").append(semestreId);
            sqlReprov.append("""
                    GROUP BY n.id_aluno, at3.id_turma
                    HAVING AVG(av.valor) < 7
                ) sub ON sub.id_aluno = at2.id_aluno AND sub.id_turma = t.id_turma
                WHERE 1=1
                """);
            if (turmaId > 0) sqlReprov.append(" AND t.id_turma = ").append(turmaId);
            sqlReprov.append(" GROUP BY t.nome ORDER BY t.nome");
            request.setAttribute("reprovadosPorTurma", queryLabelValor(conn, sqlReprov.toString()));

            StringBuilder sqlCriticos = new StringBuilder("""
                SELECT t.nome AS turma, a.matricula, u.nome,
                       COUNT(DISTINCT n.id_disciplina) AS qtd_reprovadas
                FROM alunos a
                JOIN usuarios u      ON u.id_usuario  = a.id_usuario
                JOIN aluno_turma at2 ON at2.id_aluno  = a.id_aluno
                JOIN turmas t        ON t.id_turma    = at2.id_turma
                JOIN notas n         ON n.id_aluno    = a.id_aluno
                JOIN avaliacoes av   ON av.id_nota    = n.id_nota
                WHERE av.valor < 7
                """);
            if (turmaId   > 0) sqlCriticos.append(" AND at2.id_turma = ").append(turmaId);
            if (semestreId > 0) sqlCriticos.append(" AND av.semestre = ").append(semestreId);
            sqlCriticos.append(" GROUP BY t.nome, a.matricula, u.nome ORDER BY qtd_reprovadas DESC LIMIT 5");

            List<Map<String, Object>> criticos = new ArrayList<>();
            try (Statement st = conn.createStatement();
                 ResultSet rs = st.executeQuery(sqlCriticos.toString())) {
                while (rs.next()) {
                    Map<String, Object> row = new LinkedHashMap<>();
                    row.put("turma",         rs.getString("turma"));
                    row.put("matricula",     rs.getString("matricula"));
                    row.put("nome",          rs.getString("nome"));
                    row.put("qtdReprovadas", rs.getInt("qtd_reprovadas"));
                    criticos.add(row);
                }
            }
            request.setAttribute("listaCriticos", criticos);

            request.setAttribute("filtroTurma",    turmaId);
            request.setAttribute("filtroSemestre", semestreId);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("erro", "Erro ao carregar dados: " + e.getMessage());
        }

        request.getRequestDispatcher("/WEB-INF/admin/dashboard.jsp")
                .forward(request, response);
    }


    private int queryInt(Connection conn, String sql) throws SQLException {
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            return rs.next() ? rs.getInt(1) : 0;
        }
    }

    /** Retorna lista de mapas com duas colunas: usada para o select de turmas */
    private List<Map<String, Object>> queryList(Connection conn, String sql,
                                                String col1, String col2) throws SQLException {
        List<Map<String, Object>> list = new ArrayList<>();
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Map<String, Object> row = new LinkedHashMap<>();
                row.put(col1, rs.getObject(col1));
                row.put(col2, rs.getString(col2));
                list.add(row);
            }
        }
        return list;
    }

    /** Retorna lista de mapas {label, valor} — usada para os gráficos */
    private List<Map<String, Object>> queryLabelValor(Connection conn, String sql) throws SQLException {
        List<Map<String, Object>> list = new ArrayList<>();
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Map<String, Object> row = new LinkedHashMap<>();
                row.put("label", rs.getString("label"));
                row.put("valor", rs.getDouble("valor"));
                list.add(row);
            }
        }
        return list;
    }
}