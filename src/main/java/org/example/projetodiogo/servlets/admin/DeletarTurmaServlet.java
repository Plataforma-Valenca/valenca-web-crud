package org.example.projetodiogo.servlets.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import org.example.projetodiogo.dao.TurmasDAO;

import java.io.IOException;

@WebServlet("/admin/deletarTurma")
public class DeletarTurmaServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        TurmasDAO dao = new TurmasDAO();
        dao.deletarTurma(id);

        response.sendRedirect(request.getContextPath() + "/admin/verTurmas");
    }
}