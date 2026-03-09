package org.example.projetodiogo.servlets.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import org.example.projetodiogo.dao.AvaliacaoDAO;

import java.io.IOException;

@WebServlet("/admin/lancarNotas")
public class LancarNotasServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        int idAluno = Integer.parseInt(req.getParameter("idAluno"));

        AvaliacaoDAO avaliacaoDAO = new AvaliacaoDAO();

        try {

            for (String key : req.getParameterMap().keySet()) {

                if (key.startsWith("n1_")) {

                    int idDisciplina = Integer.parseInt(key.split("_")[1]);

                    double n1 = Double.parseDouble(req.getParameter("n1_" + idDisciplina));
                    double n2 = Double.parseDouble(req.getParameter("n2_" + idDisciplina));

                    avaliacaoDAO.inserirAvaliacao(idAluno, idDisciplina, "N1", n1, 1);
                    avaliacaoDAO.inserirAvaliacao(idAluno, idDisciplina, "N2", n2, 2);
                }
            }

            resp.sendRedirect(
                    req.getContextPath() +
                            "/admin/detalhesAluno?idAluno=" + idAluno
            );

        } catch (Exception e) {

            throw new ServletException("Erro ao lançar notas", e);
        }
    }
}