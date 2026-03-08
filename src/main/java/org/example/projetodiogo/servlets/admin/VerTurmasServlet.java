package org.example.projetodiogo.servlets.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.projetodiogo.dao.TurmasDAO;
import org.example.projetodiogo.model.Turma;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/verTurmas")
public class VerTurmasServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        TurmasDAO turmaDAO = new TurmasDAO();

        try {

            List<Turma> turmas = turmaDAO.buscarTurmas();
            System.out.println(turmas);

            request.setAttribute("turmas", turmas);

            request.getRequestDispatcher("/WEB-INF/admin/turmasAdmin.jsp")
                    .forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
