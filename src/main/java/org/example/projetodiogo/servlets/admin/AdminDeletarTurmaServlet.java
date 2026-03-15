package org.example.projetodiogo.servlets.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import org.example.projetodiogo.dao.TurmasDAO;

import java.io.IOException;

@WebServlet("/admin/deletarTurma")
public class AdminDeletarTurmaServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idString = request.getParameter("idTurma");

        if (idString != null && !idString.isEmpty()) {
            try {
                int id = Integer.parseInt(idString);
                TurmasDAO dao = new TurmasDAO();
                dao.deletarTurma(id);

                request.getSession().setAttribute("mensagemSucesso", "Turma deletada com sucesso!");

            } catch (NumberFormatException e) {
                e.printStackTrace();
                request.getSession().setAttribute("mensagemErro", "Erro ao deletar turma.");
            }
        }

        response.sendRedirect(request.getContextPath() + "/admin/verTurmas");
    }
}