package org.example.projetodiogo.servlets.professor;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.projetodiogo.model.Usuario;
import org.example.projetodiogo.util.ConnectionFactory; // ⚠️ ajuste para o nome real da sua classe

import java.io.IOException;
import java.sql.*;
import java.util.*;

@WebServlet("/professor/dashboard")
public class ProfessorDashboardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");

        int idUsuario   = usuario.getId();
        int idProfessor = 0;

        String semestreParam = request.getParameter("semestre");
        int semestreId = (semestreParam != null && !semestreParam.equals("0"))
                ? Integer.parseInt(semestreParam) : 0;

        try (Connection conn = ConnectionFactory.conectar()) {

            try (PreparedStatement ps = conn.prepareStatement(
                    "SELECT id_professor FROM professores WHERE id_usuario = ?")) {
                ps.setInt(1, idUsuario);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) idProfessor = rs.getInt("id_professor");
                }
            }

            if (idProfessor == 0) {
                request.setAttribute("erro", "Erro ao encontrar professor por id usuário.");
                request.getRequestDispatcher("/WEB-INF/professor/admin-professor-dashboard.jsp")
                        .forward(request, response);
                return;
            }

            List<String> disciplinas = new ArrayList<>();
            try (PreparedStatement ps = conn.prepareStatement(
                    "SELECT nome FROM disciplinas WHERE id_professor = ? ORDER BY nome")) {
                ps.setInt(1, idProfessor);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) disciplinas.add(rs.getString("nome"));
                }
            }
            request.setAttribute("disciplinas", disciplinas);

            StringBuilder sqlMediaDisc = new StringBuilder("""
                SELECT d.nome AS label, ROUND(AVG(av.valor)::numeric, 2) AS valor
                FROM disciplinas d
                JOIN notas n       ON n.id_disciplina = d.id_disciplina
                JOIN avaliacoes av ON av.id_nota       = n.id_nota
                WHERE d.id_professor = ?
                """);
            if (semestreId > 0) sqlMediaDisc.append(" AND av.semestre = ").append(semestreId);
            sqlMediaDisc.append(" GROUP BY d.nome ORDER BY d.nome");
            request.setAttribute("mediaPorDisciplina",
                    queryLabelValorPS(conn, sqlMediaDisc.toString(), idProfessor));

            StringBuilder sqlAprov = new StringBuilder("""
                SELECT t.nome AS label, COUNT(DISTINCT sub.id_aluno) AS valor
                FROM turmas t
                JOIN aluno_turma at2 ON at2.id_turma = t.id_turma
                JOIN (
                    SELECT n.id_aluno, at3.id_turma
                    FROM notas n
                    JOIN disciplinas d   ON d.id_disciplina = n.id_disciplina
                    JOIN avaliacoes av   ON av.id_nota       = n.id_nota
                    JOIN aluno_turma at3 ON at3.id_aluno     = n.id_aluno
                    WHERE d.id_professor = ?
                """);
            if (semestreId > 0) sqlAprov.append(" AND av.semestre = ").append(semestreId);
            sqlAprov.append("""
                    GROUP BY n.id_aluno, at3.id_turma
                    HAVING AVG(av.valor) >= 7
                ) sub ON sub.id_aluno = at2.id_aluno AND sub.id_turma = t.id_turma
                GROUP BY t.nome ORDER BY t.nome
                """);
            request.setAttribute("aprovadosPorTurma",
                    queryLabelValorPS(conn, sqlAprov.toString(), idProfessor));

            StringBuilder sqlReprov = new StringBuilder("""
                SELECT t.nome AS label, COUNT(DISTINCT sub.id_aluno) AS valor
                FROM turmas t
                JOIN aluno_turma at2 ON at2.id_turma = t.id_turma
                JOIN (
                    SELECT n.id_aluno, at3.id_turma
                    FROM notas n
                    JOIN disciplinas d   ON d.id_disciplina = n.id_disciplina
                    JOIN avaliacoes av   ON av.id_nota       = n.id_nota
                    JOIN aluno_turma at3 ON at3.id_aluno     = n.id_aluno
                    WHERE d.id_professor = ?
                """);
            if (semestreId > 0) sqlReprov.append(" AND av.semestre = ").append(semestreId);
            sqlReprov.append("""
                    GROUP BY n.id_aluno, at3.id_turma
                    HAVING AVG(av.valor) < 7
                ) sub ON sub.id_aluno = at2.id_aluno AND sub.id_turma = t.id_turma
                GROUP BY t.nome ORDER BY t.nome
                """);
            request.setAttribute("reprovadosPorTurma",
                    queryLabelValorPS(conn, sqlReprov.toString(), idProfessor));

            StringBuilder sqlMediaTurma = new StringBuilder("""
                SELECT t.nome AS label, ROUND(AVG(av.valor)::numeric, 2) AS valor
                FROM turmas t
                JOIN aluno_turma at2 ON at2.id_turma    = t.id_turma
                JOIN notas n         ON n.id_aluno       = at2.id_aluno
                JOIN disciplinas d   ON d.id_disciplina  = n.id_disciplina
                JOIN avaliacoes av   ON av.id_nota        = n.id_nota
                WHERE d.id_professor = ?
                """);
            if (semestreId > 0) sqlMediaTurma.append(" AND av.semestre = ").append(semestreId);
            sqlMediaTurma.append(" GROUP BY t.nome ORDER BY t.nome");
            request.setAttribute("mediaPorTurma",
                    queryLabelValorPS(conn, sqlMediaTurma.toString(), idProfessor));

            StringBuilder sqlCriticos = new StringBuilder("""
                SELECT t.nome AS turma, a.matricula, u.nome,
                       COUNT(DISTINCT n.id_disciplina) AS qtd_reprovadas
                FROM alunos a
                JOIN usuarios u      ON u.id_usuario    = a.id_usuario
                JOIN aluno_turma at2 ON at2.id_aluno    = a.id_aluno
                JOIN turmas t        ON t.id_turma      = at2.id_turma
                JOIN notas n         ON n.id_aluno      = a.id_aluno
                JOIN disciplinas d   ON d.id_disciplina = n.id_disciplina
                JOIN avaliacoes av   ON av.id_nota      = n.id_nota
                WHERE d.id_professor = ? AND av.valor < 7
                """);
            if (semestreId > 0) sqlCriticos.append(" AND av.semestre = ").append(semestreId);
            sqlCriticos.append(" GROUP BY t.nome, a.matricula, u.nome ORDER BY qtd_reprovadas DESC LIMIT 5");

            List<Map<String, Object>> criticos = new ArrayList<>();
            try (PreparedStatement ps = conn.prepareStatement(sqlCriticos.toString())) {
                ps.setInt(1, idProfessor);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Map<String, Object> row = new LinkedHashMap<>();
                        row.put("turma",         rs.getString("turma"));
                        row.put("matricula",     rs.getString("matricula"));
                        row.put("nome",          rs.getString("nome"));
                        row.put("qtdReprovadas", rs.getInt("qtd_reprovadas"));
                        criticos.add(row);
                    }
                }
            }
            request.setAttribute("listaCriticos", criticos);

            request.setAttribute("filtroSemestre", semestreId);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("erro", "Erro ao carregar dados: " + e.getMessage());
        }

        request.getRequestDispatcher("/WEB-INF/professor/admin-professor-dashboard.jsp")
                .forward(request, response);
    }

    private List<Map<String, Object>> queryLabelValorPS(Connection conn, String sql, int param)
            throws SQLException {
        List<Map<String, Object>> list = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, param);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Map<String, Object> row = new LinkedHashMap<>();
                    row.put("label", rs.getString("label"));
                    row.put("valor", rs.getDouble("valor"));
                    list.add(row);
                }
            }
        }
        return list;
    }
}