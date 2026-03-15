package org.example.projetodiogo.servlets.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.projetodiogo.dao.AvaliacaoDAO;

import java.io.IOException;
import java.util.Map;

@WebServlet("/professor/lancarNotas")
public class LancarNotasServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Pega o id do aluno do formulário
        String idAlunoStr = req.getParameter("idAluno");

        if (idAlunoStr == null || idAlunoStr.isEmpty()) {
            // Se não vier idAluno, redireciona de volta com erro
            resp.sendRedirect(req.getContextPath() + "/professor/turmasProfessor");
            return;
        }

        int idAluno = Integer.parseInt(idAlunoStr);
        AvaliacaoDAO avaliacaoDAO = new AvaliacaoDAO();

        try {
            // Percorre todos os parâmetros para lançar notas
            Map<String, String[]> parametros = req.getParameterMap();

            for (String key : parametros.keySet()) {

                if (key.startsWith("n1_")) {
                    // Exemplo: n1_3 -> nota N1 da disciplina com id 3
                    int idDisciplina = Integer.parseInt(key.split("_")[1]);

                    double n1 = Double.parseDouble(req.getParameter("n1_" + idDisciplina));
                    double n2 = Double.parseDouble(req.getParameter("n2_" + idDisciplina));

                    avaliacaoDAO.inserirAvaliacao(idAluno, idDisciplina, "N1", n1, 1);
                    avaliacaoDAO.inserirAvaliacao(idAluno, idDisciplina, "N2", n2, 2);
                }
            }

            // Redireciona de volta para a página de detalhes do aluno
            resp.sendRedirect(req.getContextPath() + "/professor/detalhesAluno?idAluno=" + idAluno);

        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Erro ao lançar notas", e);
        }
    }
}